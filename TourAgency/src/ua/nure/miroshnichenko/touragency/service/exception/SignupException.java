package ua.nure.miroshnichenko.touragency.service.exception;

public class SignupException extends ServiceException {

	private static final long serialVersionUID = 6658626908587141377L;

	public SignupException(String message, Throwable cause) {
		super(message, cause);
	}

	public SignupException(String message) {
		super(message);
	}

	public SignupException(Throwable cause) {
		super(cause);
	}
}
