package ua.nure.miroshnichenko.summarytask4.web.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.miroshnichenko.summarytask4.db.entity.Role;
import ua.nure.miroshnichenko.summarytask4.db.entity.User;
import ua.nure.miroshnichenko.summarytask4.service.AuthentificationService;
import ua.nure.miroshnichenko.summarytask4.service.ServiceException;
import ua.nure.miroshnichenko.summarytask4.web.Path;

public class SignupAction extends Action {

	private static final long serialVersionUID = 4001087808996248354L;

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException, ActionException {

		String email = req.getParameter("email");
		String password = req.getParameter("password");

		User user = new User();
		
		user.setEmail(email);
		user.setPassword(password);
		user.setRole(Role.CLIENT);
		
		AuthentificationService authentificationService = 
				serviceFactory.getAuthentificationService();
		
		try {
			if(authentificationService.signup(user)) {
				HttpSession session = req.getSession();
				session.setAttribute("user", user);
				
				return Path.INDEX_PAGE;
			} else {
				return Path.ERR_PAGE;
			}
		} catch (ServiceException e) {
			e.printStackTrace();
			
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			throw new ActionException(e);
		}
	}

}
