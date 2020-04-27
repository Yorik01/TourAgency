package ua.nure.miroshnichenko.touragency.web.action;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.miroshnichenko.touragency.service.impl.ServiceFactory;

/**
 * The super class of all actions.
 * 
 * @author Miroshnichenko Y. D
 */
public abstract class Action implements Serializable {

	private static final long serialVersionUID = -7307441034600093272L;

	protected ServiceFactory serviceFactory = ServiceFactory.getInstance();

	/**
	 * The method execute a specific command and return a path to redirect. There
	 * are two variants of pathes:
	 * <ul>
	 * <li><i>PATH</i> - execute
	 * 
	 * <pre>
	 * {@code request.getRequestDispatcher(PATH).forward(request, response);}
	 * </pre>
	 * 
	 * </li>
	 * <li>"redirect:" + <i>PATH</i> - execute
	 * 
	 * <pre>
	 * {@code response.sendRedirect(PATH);}
	 * </pre>
	 * 
	 * </li>
	 * </ul>
	 * 
	 * @return path to redirect.
	 */
	public abstract String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException, ActionException;

	/**
	 * The string representation of action.
	 * 
	 * @return class name without substring "Action" and with first character in
	 *         lower case.
	 */
	@Override
	public final String toString() {
		String simpleName = getClass().getSimpleName();
		return (Character.toLowerCase(simpleName.charAt(0)) + simpleName.substring(1)).replace("Action", "");
	}
}
