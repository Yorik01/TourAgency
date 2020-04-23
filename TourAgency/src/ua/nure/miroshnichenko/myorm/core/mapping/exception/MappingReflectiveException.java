package ua.nure.miroshnichenko.myorm.core.mapping.exception;

import ua.nure.miroshnichenko.myorm.MyOrmException;

public class MappingReflectiveException extends MyOrmException {
	private static final long serialVersionUID = -3609667321846064300L;

	public MappingReflectiveException(String message, Throwable cause) {
		super(message, cause);
	}

	public MappingReflectiveException(String message) {
		super(message);
	}

	public MappingReflectiveException(Throwable cause) {
		super(cause);
	}
}
