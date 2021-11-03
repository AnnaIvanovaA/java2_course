package org.database.university.configuration;

import org.database.university.reflect.Property;

//Singleton - design pattern
public class DatabaseConfiguration {

    @Property(key = "database.url")
    private String url;
    @Property(key = "database.login")
    private String login;
    @Property(key = "database.password")
    private String password;

    @Property(key = "database.min.pool.size")
    private int minPoolSize;                // min amount of opened DB connections
    @Property(key = "database.connection.timeout")
    private long connectionTimeout;
    @Property(key = "database.read.timeout")
    private long readTimeout;

    @Override
    public String toString() {
        return "DatabaseConfiguration{" +
                "url='" + url + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", minPoolSize=" + minPoolSize +
                ", connectionTimeout=" + connectionTimeout +
                ", readTimeout=" + readTimeout +
                '}';
    }

    //empty constructor
    public DatabaseConfiguration() {}

    //creation of the only one object
    private static final DatabaseConfiguration INSTANCE = new DatabaseConfiguration();

    //getting of the only one created object
    public static DatabaseConfiguration getInstance(){
        return INSTANCE;
    }

    public String getUrl() {
        return url;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public int getMinPoolSize() {
        return minPoolSize;
    }

    public long getConnectionTimeout() {
        return connectionTimeout;
    }

}
