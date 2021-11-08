package org.database.university.jdbc;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;

public class ConnectionInvocationHandler implements InvocationHandler {

    private final Connection originalConnection;
    private final ConnectionPool connectionPool;

    public ConnectionInvocationHandler(Connection originalConnection, ConnectionPool connectionPool) {
        this.originalConnection = originalConnection;
        this.connectionPool = connectionPool;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Catch method invocation");
        String methodName = method.getName();
        //void close();
        if (methodName.equals("close")) {
            connectionPool.returnConnection(originalConnection);
            return null;  //void --> won't be returned anywhere, java will ignore this
        }

        //reflection -- call a method with original connection
        return method.invoke(originalConnection, args);

    }
}
