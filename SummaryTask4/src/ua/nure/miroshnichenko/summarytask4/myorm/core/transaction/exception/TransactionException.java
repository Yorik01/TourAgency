package ua.nure.miroshnichenko.summarytask4.myorm.core.transaction.exception;

import ua.nure.miroshnichenko.summarytask4.myorm.MyOrmException;

public class TransactionException extends MyOrmException {
	private static final long serialVersionUID = 1453642881410340991L;

	public TransactionException(String message, Throwable cause) {
		super(message, cause);
	}

	public TransactionException(Throwable cause) {
		super(cause);
	}

	public TransactionException(String message) {
		super(message);
	}
}
