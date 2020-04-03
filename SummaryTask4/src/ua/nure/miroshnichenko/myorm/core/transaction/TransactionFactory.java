package ua.nure.miroshnichenko.myorm.core.transaction;

import ua.nure.miroshnichenko.myorm.core.transaction.exception.TransactionFactoryException;

public interface TransactionFactory extends AutoCloseable {
	Transaction createTransaction() throws TransactionFactoryException;

	void close() throws TransactionFactoryException;
}
