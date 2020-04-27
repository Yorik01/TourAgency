package ua.nure.miroshnichenko.touragency.web.filter;

/**
 * Enumeration about status of access to system.
 * 
 * @author Miroshnichenko Y. D.
 */
public enum Access {
	ALLOW,
	NO_PERMISSIONS,
	NO_LOGIN,
	EMPTY_REQUEST,
	BANNED
}
