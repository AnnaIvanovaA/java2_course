package org.database.university.repository.jdbc;

import org.database.university.domain.Faculty;
import org.database.university.domain.University;
import org.database.university.jdbc.DatabaseService;
import org.database.university.repository.UniversityRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.database.university.configuration.HibernateConfiguration.factory;

public class JdbcUniversityRepository implements UniversityRepository {

    public final DatabaseService dbService;  //url login and connection  dependency class

    public JdbcUniversityRepository(DatabaseService dbService) {
        this.dbService = dbService;
    }

    @Override
    public List<University> findAll(){

        try (Connection connection = dbService.openConnection()){
            //entry point with db
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(JdbcSqls.SELECT_ALL_UNIVERSITIES);

            return retrieveFromResultSet(resultSet);

        } catch (SQLException e) {
            System.out.println("Couldn't get all universities because of an error: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    //Sql injection -- vulnerability
    //input text field e.g. in browser

    @Override
    public University createUniversity(String name, String shortName, Integer foundationYear) {
        try (Connection connection = dbService.openConnection()){
            PreparedStatement stmt = connection
                    .prepareStatement("insert into university (name, short_name, foundation_year) values (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, name); //('name', '', ...)
            stmt.setString(2, shortName);

            if (foundationYear == null){
                stmt.setNull(3, JDBCType.INTEGER.getVendorTypeNumber());
            } else {
                stmt.setInt(3, foundationYear);
            }

            int createdRows = stmt.executeUpdate();  //insert/delete/update
            System.out.println("Count of created rows: " + createdRows);


            ResultSet generatedKeysSet = stmt.getGeneratedKeys();
            generatedKeysSet.next();
            Long universityId = generatedKeysSet.getLong(1);
            return new University(universityId, name, shortName, foundationYear);

//            boolean hasResult = stmt.execute();
//            if (hasResult){
//                ResultSet resultSet = stmt.getResultSet();
//            }


        } catch (SQLException e) {
            System.out.println("Couldn't insert new university because of an error: " + e.getMessage());
        }


        return null;
    }

    @Override
    public University createUniversity(String name, String shortName, Integer foundationYear, List<String> facultyNames) {
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();

            University university = new University(name, shortName, foundationYear);

            for (String facultyName : facultyNames) {
                Faculty faculty = new Faculty();
                faculty.setName(facultyName);
                faculty.setUniversity(university);

                university.getFaculties().add(faculty);
            }
            session.persist(university);

            tx.commit();
            return university;
        }

    }

    @Override
    public University findByUniversityId(Long universityId) {
        return null;
    }

    private List<University> retrieveFromResultSet(ResultSet resultSet) throws SQLException {
        List<University> universities = new ArrayList<>();
        //ResultSet -- iterator
        while (resultSet.next()) {
            Long universityId = resultSet.getLong("id"); //columnName from select, not from db
            String name = resultSet.getString("name");
            String shortName = resultSet.getString("short_name");
            Integer foundationYear = resultSet.getInt("foundation_year");

            University university = new University(universityId, name, shortName, foundationYear);
            universities.add(university);
        }
        return universities;
    }
}
