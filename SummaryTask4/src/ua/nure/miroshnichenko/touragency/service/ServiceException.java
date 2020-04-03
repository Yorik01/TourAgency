package ua.nure.miroshnichenko.touragency.service;

public class ServiceException extends Exception {

	private static final long serialVersionUID = -5848988892582404238L;

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}
}