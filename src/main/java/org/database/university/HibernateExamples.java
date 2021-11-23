package org.database.university;

import org.database.university.configuration.DatabaseConfiguration;
import org.database.university.configuration.HibernateConfiguration;
import org.database.university.domain.University;
import org.database.university.reflect.AnnotationConfigurationPropertiesProcessor;
import org.database.university.repository.UniversityRepository;
import org.database.university.repository.hbm.HibernateUniversityRepository;
import org.hibernate.SessionFactory;

import java.util.List;

//TODO
public class HibernateExamples {

    public static void main(String[] args) {
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
        University u = universityRepository.createUniversity(
                "Russian New Universoty",
                "ROSNOY",
                1991,
                List.of("gumanitarnyi institute", "nalogovyi institute")
        );
        System.out.println(u);

        factory.close();
    }
}
