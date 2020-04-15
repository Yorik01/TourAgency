package ua.nure.miroshnichenko.touragency.web.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.miroshnichenko.touragency.db.entity.User;
import ua.nure.miroshnichenko.touragency.service.AuthentificationService;
import ua.nure.miroshnichenko.touragency.service.exception.ServiceException;
import ua.nure.miroshnichenko.touragency.web.Path;
import ua.nure.miroshnichenko.touragency.web.action.Action;
import ua.nure.miroshnichenko.touragency.web.action.ActionException;

public class LoginAction extends Action {

	private static final long serialVersionUID = -8527970378246991282L;

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException, ActionException {

		String email = req.getParameter("email");
		String password = req.getParameter("password");

		if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
			throw new ActionException("Email/password cannot be empty!!!");
		}

		AuthentificationService authentificationService = 
				serviceFactory.getAuthentificationService();
		
		try {
			User user = authentificationService.login(email, password);
			
			HttpSession session = req.getSession();
			session.setAttribute("user", user);
			
			res.sendRedirect(Path.getControllerPath("filterForm"));
			
			return Path.NO_PATH;
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ActionException(e);
		}
	}
}
