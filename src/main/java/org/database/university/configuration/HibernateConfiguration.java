package org.database.university.configuration;

import org.database.university.domain.University;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.postgresql.Driver;

import java.util.HashMap;
import java.util.Map;

public class HibernateConfiguration {
    public static SessionFactory factory;

    //setup Hibernate
    public static void configure(DatabaseConfiguration dbConfiguration) {
        Map<String, String> hibernateProperties = buildHibernateProperties(dbConfiguration);

        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder()
                .applySettings(hibernateProperties)
                .build();

        Configuration cfg = new Configuration()
                .addAnnotatedClass(University.class);

        factory = cfg.buildSessionFactory(ssr);

    }

    public static Map<String, String> buildHibernateProperties(DatabaseConfiguration dbConfiguration) {
        Map<String, String> properties = new HashMap<>();

        properties.put("hibernate.connection.driver_class", Driver.class.getName());

        properties.put("hibernate.connection.url", dbConfiguration.getUrl());
        properties.put("hibernate.connection.username", dbConfiguration.getLogin());
        properties.put("hibernate.connection.password", dbConfiguration.getPassword());

        properties.put("show_sql", "true");         //hibernate requests in Console
        properties.put("format_sql", "true");       //make hibernate requests readable

        // validate update create create-drop
        properties.put("hibernate.hbm2ddl.auto", "validate");     //schemas generation

        return properties;
    }

    public static SessionFactory getSessionFactory() {
        if (factory == null) {
            throw new RuntimeException("SessionFactory isn't configured");
        }
        return factory;
    }
}
