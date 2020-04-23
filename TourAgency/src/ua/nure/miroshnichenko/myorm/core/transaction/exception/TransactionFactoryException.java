package ua.nure.miroshnichenko.myorm.core.transaction.exception;

import ua.nure.miroshnichenko.myorm.MyOrmException;

public class TransactionFactoryException extends MyOrmException{
	private static final long serialVersionUID = -9192835379413659424L;

	public TransactionFactoryException(String message, Throwable cause) {
		super(message, cause);
	}

	public TransactionFactoryException(String message) {
		super(message);
	}

	public TransactionFactoryException(Throwable cause) {
		super(cause);
	}	
}
