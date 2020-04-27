package ua.nure.miroshnichenko.touragency.web.action;

/**
 * The exception for all actions.
 * 
 * @author Miroshnichenko Y. D.
 */
public class ActionException extends Exception {

	private static final long serialVersionUID = -8511087099529837398L;

	public ActionException(String message, Throwable cause) {
		super(message, cause);
	}

	public ActionException(String message) {
		super(message);
	}

	public ActionException(Throwable cause) {
		super(cause);
	}
}
