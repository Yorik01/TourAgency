package ua.nure.miroshnichenko.touragency.web.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.miroshnichenko.touragency.web.Path;

/**
 * The action is called when there aren't appropriate actions.
 * 
 * @author Miroshnichenko Y. D.
 */
public class NoAction extends Action {

	private static final long serialVersionUID = -6707124208737496899L;

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException, ActionException {
		
		return Path.PAGE_404;
	}
}
