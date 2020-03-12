package ua.nure.miroshnichenko.summarytask4.myorm.core;

import ua.nure.miroshnichenko.summarytask4.myorm.MyOrmException;

public class DBConnectionException extends MyOrmException {
	private static final long serialVersionUID = 1672226804927073755L;

	public DBConnectionException(String message, Throwable cause) {
		super(message, cause);
	}

	public DBConnectionException(String message) {
		super(message);
	}

	public DBConnectionException(Throwable cause) {
		super(cause);
	}
}