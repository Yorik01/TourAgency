package ua.nure.miroshnichenko.touragency.web.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.miroshnichenko.touragency.web.Path;
import ua.nure.miroshnichenko.touragency.web.action.Action;
import ua.nure.miroshnichenko.touragency.web.action.ActionException;

/**
 * The action to log out.
 * 
 * @author Miroshnichenko Y. D.
 */
public class LogoutAction extends Action {

	private static final long serialVersionUID = -3037025766317928316L;

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException, ActionException {
		
		HttpSession session = req.getSession();
		session.removeAttribute("user");
		
		return Path.LOGIN_PAGE;
	}
}
