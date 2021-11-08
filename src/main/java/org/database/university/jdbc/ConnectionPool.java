package org.database.university.jdbc;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.Queue;

public class ConnectionPool {


    private Queue<Connection> queue = new LinkedList<>();

    //call when a connection is needed
    public Connection getConnection() {
        if (queue.isEmpty()) {
            return null;
        }
        return queue.poll();  //take first element (connection) from the queue
    }

    //when we want to release the connection into the pool
    public void returnConnection(Connection connection) {
        queue.offer(connection);  //put connection to the queue end

    }
}
