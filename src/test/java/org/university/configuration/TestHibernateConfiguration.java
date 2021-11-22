package org.university.configuration;

import org.database.university.domain.University;
import org.database.university.reflection.Subject;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.postgresql.Driver;

import java.util.HashMap;
import java.util.Map;

public class TestHibernateConfiguration {

    private static SessionFactory factory;

    public static SessionFactory getFactory(){
        return factory;
    }

    static {
        Map<String, String> properties = new HashMap<>();

        properties.put("hibernate.connection.driver_class", Driver.class.getName());

        properties.put("hibernate.connection.url", "jdbc:postgresql://localhost:5432/university-it");
        properties.put("hibernate.connection.username", "postgres");
        properties.put("hibernate.connection.password", "root");

        properties.put("show_sql", "true");         //hibernate requests in Console
        properties.put("format_sql", "true");       //make hibernate requests readable

        // validate update create create-drop
        properties.put("hibernate.hbm2ddl.auto", "create");     //schemas generation

        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder()
                .applySettings(properties)
                .build();

        Configuration cfg = new Configuration()
                .addAnnotatedClass(University.class)
                .addAnnotatedClass(Subject.class);

        factory = cfg.buildSessionFactory(ssr);

    }
}
