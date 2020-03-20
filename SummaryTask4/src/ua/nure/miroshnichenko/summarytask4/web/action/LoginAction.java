package ua.nure.miroshnichenko.summarytask4.web.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.miroshnichenko.summarytask4.db.entity.User;
import ua.nure.miroshnichenko.summarytask4.service.AuthentificationService;
import ua.nure.miroshnichenko.summarytask4.service.ServiceException;
import ua.nure.miroshnichenko.summarytask4.web.Path;

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
			
			res.setStatus(HttpServletResponse.SC_OK);
			
			return Path.INDEX_PAGE;
		} catch (ServiceException e) {
			e.printStackTrace();
			
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			throw new ActionException(e);
		}
	}
}
