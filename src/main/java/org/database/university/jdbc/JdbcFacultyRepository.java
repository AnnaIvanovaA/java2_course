package org.database.university.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

public class JdbcFacultyRepository {

    private final DatabaseService dbService;

    public JdbcFacultyRepository(DatabaseService dbService) {
        this.dbService = dbService;
    }

    public void getAllFaculties() {
        Connection connection = dbService.openConnection(); //now connection is taken from the pool

    }

}
