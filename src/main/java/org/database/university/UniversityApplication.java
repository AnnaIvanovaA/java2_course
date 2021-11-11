package org.database.university;

import org.database.university.configuration.DatabaseConfiguration;
import org.database.university.configuration.HibernateConfiguration;
import org.database.university.domain.University;
import org.database.university.reflect.AnnotationConfigurationPropertiesProcessor;
import org.database.university.repository.UniversityRepository;
import org.database.university.repository.hbm.HibernateUniversityRepository;
import org.hibernate.SessionFactory;

import java.sql.SQLException;

public class UniversityApplication {

    public static void main(String[] args) throws SQLException {
        String configurationFilename = "database.properties";
        //ConfigurationPropertiesProcessor.processConfigurationFile(configurationFilename);
        AnnotationConfigurationPropertiesProcessor.processConfigurationFile(configurationFilename);
        System.out.println("Application loaded all configuration files");
        HibernateConfiguration.configure(DatabaseConfiguration.getInstance());
        System.out.println("Hibernate has been configured successfully");

        System.out.println(DatabaseConfiguration.getInstance().toString());

        SessionFactory factory = HibernateConfiguration.getSessionFactory();
//        DatabaseService dbService = new DatabaseService(DatabaseConfiguration.getInstance());
//        dbService.fillPool();
//        System.out.println("Connection pool has been initialized");
        System.out.println("University application has been started");


        UniversityRepository universityRepository = new HibernateUniversityRepository(factory);
        University u = universityRepository.createUniversity("fsdf", "VSHE", null);


        factory.close();
//        Connection proxy = dbService.openConnection();
//        proxy.createStatement();
//        proxy.close();
//        proxy.commit();
//        System.out.println();


//        DatabaseService dbService = new DatabaseService();
//        UniversityRepository universityRepository = new JdbcUniversityRepository(dbService);
//
//        University itmo = universityRepository.createUniversity("State University of Information Technologies", "ITMO", 1900);
//        System.out.println("Newly inserted university: " + itmo.getUniversityId());
//
//        List<University> universities = universityRepository.findAll();
//        for (University u: universities) {
//            System.out.println(u.getShortName() + " " + u.getFoundationYear());
//
//        }
//
//        Connection connection = dbService.openConnection();
//        System.out.println("Connection has been opened");
//        connection.close();
//        System.out.println("Connection has been closed");
//
//        DatabaseConfiguration dbConfig = DatabaseConfiguration.getInstance();
    }
}
