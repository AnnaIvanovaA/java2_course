package org.university.jdbc;

import org.database.university.jdbc.ConnectionPool;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.university.jdbc.stub.ConnectionStub;

import java.sql.Connection;

public class ConectionPoolTest {

    @Test
    public void shouldOfferConnectionToPool(){
        ConnectionPool pool = new ConnectionPool();
        Connection connection = Mockito.mock(Connection.class); //new instance of Proxy (Connection.class)

        pool.returnConnection(connection);

        Connection result = pool.getConnection();
        Assertions.assertSame(connection, result);
    }
}
