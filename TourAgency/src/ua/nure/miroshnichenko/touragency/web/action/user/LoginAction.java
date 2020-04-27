package ua.nure.miroshnichenko.touragency.web.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.miroshnichenko.touragency.db.entity.User;
import ua.nure.miroshnichenko.touragency.service.AuthentificationService;
import ua.nure.miroshnichenko.touragency.service.exception.ServiceException;
import ua.nure.miroshnichenko.touragency.web.Path;
import ua.nure.miroshnichenko.touragency.web.action.Action;
import ua.nure.miroshnichenko.touragency.web.action.ActionException;

/**
 * The action to log in.
 * 
 * @author Miroshnichenko Y. D.
 */
public class LoginAction extends Action {

	private static final long serialVersionUID = -8527970378246991282L;

	private final Logger LOG = Logger.getLogger(LoginAction.class);
	
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException, ActionException {

		String email = req.getParameter("email");
		String password = req.getParameter("password");

		AuthentificationService authentificationService = 
				serviceFactory.getAuthentificationService();
		
		try {
			User user = authentificationService.login(email, password);
			
			HttpSession session = req.getSession();
			session.setAttribute("user", user);
			
			return "redirect:" + Path.getControllerPath("filterForm");
		} catch (ServiceException e) {
			LOG.error(e);
			throw new ActionException(e);
		}
	}
}
