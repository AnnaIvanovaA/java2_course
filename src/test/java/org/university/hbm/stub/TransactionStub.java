package org.university.hbm.stub;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import javax.transaction.Synchronization;

public class TransactionStub implements Transaction {
    @Override
    public TransactionStatus getStatus() {
        return null;
    }

    @Override
    public void registerSynchronization(Synchronization synchronization) throws HibernateException {

    }

    @Override
    public void setTimeout(int i) {

    }

    @Override
    public int getTimeout() {
        return 0;
    }

    @Override
    public void begin() {

    }

    @Override
    public void commit() {

    }

    @Override
    public void rollback() {

    }

    @Override
    public void setRollbackOnly() {

    }

    @Override
    public boolean getRollbackOnly() {
        return false;
    }

    @Override
    public boolean isActive() {
        return false;
    }
}
