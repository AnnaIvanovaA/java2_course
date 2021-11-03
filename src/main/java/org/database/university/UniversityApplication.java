package org.database.university;

import org.database.university.configuration.DatabaseConfiguration;
import org.database.university.domain.University;
import org.database.university.jdbc.DatabaseService;
import org.database.university.jdbc.JdbcUniversityRepository;
import org.database.university.reflect.AnnotationConfigurationPropertiesProcessor;
import org.database.university.reflect.ConfigurationPropertiesProcessor;
import org.database.university.repository.UniversityRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UniversityApplication {

    public static void main(String[] args) throws SQLException {
        String configurationFilename = "database.properties";
        //ConfigurationPropertiesProcessor.processConfigurationFile(configurationFilename);
        AnnotationConfigurationPropertiesProcessor.processConfigurationFile(configurationFilename);
        System.out.println("Application loaded all configuration files");
        System.out.println(DatabaseConfiguration.getInstance().toString());
        System.out.println("University application has been started");



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
