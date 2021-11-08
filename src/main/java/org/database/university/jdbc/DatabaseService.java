package org.database.university.jdbc;

import org.database.university.configuration.DatabaseConfiguration;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseService {

    private DatabaseConfiguration dbConfiguration;
    private ConnectionPool connectionPool;

    public DatabaseService(DatabaseConfiguration dbConfiguration) {
        this.dbConfiguration = dbConfiguration;
        this.connectionPool = new ConnectionPool();
    }

    public void fillPool() {
        for (int i = 0; i < dbConfiguration.getMinPoolSize(); i++) {
            Connection connection = createConnection();
            connectionPool.returnConnection(connection);
        }
    }

    private Connection createConnection() {
        try {
            return DriverManager.getConnection(
                    dbConfiguration.getUrl(),
                    dbConfiguration.getLogin(),
                    dbConfiguration.getPassword()
            );
        } catch (SQLException exc) {
            throw new RuntimeException(exc);
        }
    }

    public Connection openConnection() {

        Connection connection = connectionPool.getConnection();  //connection.getClass() -> PgConnection.class;
        return (Connection) Proxy.newProxyInstance(
                connection.getClass().getClassLoader(),
                connection.getClass().getInterfaces(),
                new ConnectionInvocationHandler(connection, connectionPool)
        );

    }
//    public Connection openConnection(){
//        try {
//            // jdbc url
//            // jdbc:<vendor_name>://<host-dns/ip>:<port>/<database_name>?<key1>=<val1>&<key2>=<val2>...
//            return DriverManager.getConnection(
//                    // "jdbc:postgresql://localhost:5432/university", // connection url
//                    "jdbc:postgresql://127.0.0.1:5432/university", // connection url
//                    "postgres", // database login
//                    "root"      // database password
//            );
//        } catch (SQLException exc) {
//            System.out.println("Couldn't connect to database");
//            throw new RuntimeException(exc);
//        }
//    }
}
