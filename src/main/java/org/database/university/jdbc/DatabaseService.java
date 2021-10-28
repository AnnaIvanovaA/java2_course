package org.database.university.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseService {

    public Connection openConnection(){
        try {
            // jdbc url
            // jdbc:<vendor_name>://<host-dns/ip>:<port>/<database_name>?<key1>=<val1>&<key2>=<val2>...
            return DriverManager.getConnection(
                    // "jdbc:postgresql://localhost:5432/university", // connection url
                    "jdbc:postgresql://127.0.0.1:5432/university", // connection url
                    "postgres", // database login
                    "root"      // database password
            );
        } catch (SQLException exc) {
            System.out.println("Couldn't connect to database");
            throw new RuntimeException(exc);
        }
    }
}
