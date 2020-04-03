package ua.nure.miroshnichenko.myorm.core;

import ua.nure.miroshnichenko.myorm.MyOrmException;

public class ConnectionsPoolException extends MyOrmException {
	private static final long serialVersionUID = -5719999661385786313L;

	public ConnectionsPoolException(String message, Throwable cause) {
		super(message, cause);
	}

	public ConnectionsPoolException(String message) {
		super(message);
	}

	public ConnectionsPoolException(Throwable cause) {
		super(cause);
	}
}
