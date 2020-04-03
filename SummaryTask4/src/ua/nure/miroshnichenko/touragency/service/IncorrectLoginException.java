package ua.nure.miroshnichenko.touragency.service;

public class IncorrectLoginException extends ServiceException {

	private static final long serialVersionUID = -4084740110606911860L;

	public IncorrectLoginException(String message, Throwable cause) {
		super(message, cause);
	}

	public IncorrectLoginException(String message) {
		super(message);
	}

	public IncorrectLoginException(Throwable cause) {
		super(cause);
	}
}
