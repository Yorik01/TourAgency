package ua.nure.miroshnichenko.summarytask4.myorm;

/**
 * The super class of all exceptions in this framework.
 * 
 * @author Miroshnichenko Y. D
 */
public class MyOrmException extends Exception {
	private static final long serialVersionUID = 1682672015485565337L;

	public MyOrmException(String message) {
		super(message);
	}

	public MyOrmException(String message, Throwable cause) {
		super(message, cause);
	}

	public MyOrmException(Throwable cause) {
		super(cause);
	}
}
