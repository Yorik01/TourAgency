package ua.nure.miroshnichenko.summarytask4.myorm.core.transaction;

import ua.nure.miroshnichenko.summarytask4.myorm.core.transaction.exception.TransactionFactoryException;

public interface TransactionFactory extends AutoCloseable {
	Transaction createTransaction() throws TransactionFactoryException;

	void close() throws TransactionFactoryException;
}
