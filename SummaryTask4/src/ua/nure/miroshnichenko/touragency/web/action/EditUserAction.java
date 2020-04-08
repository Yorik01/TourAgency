package ua.nure.miroshnichenko.touragency.web.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.miroshnichenko.touragency.db.entity.Role;
import ua.nure.miroshnichenko.touragency.db.entity.User;
import ua.nure.miroshnichenko.touragency.service.AuthentificationService;
import ua.nure.miroshnichenko.touragency.service.ServiceException;
import ua.nure.miroshnichenko.touragency.web.Path;

public class EditUserAction extends Action {

	private static final long serialVersionUID = -2507178419753890281L;

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException, ActionException {
		
		AuthentificationService authentificationService = 
				serviceFactory.getAuthentificationService();
		
		int id = Integer.parseInt(req.getParameter("id"));
		String email = req.getParameter("emal");
		String password = req.getParameter("password");
		Role role = Role.valueOf(req.getParameter("role"));
		
		User user = new User();
		
		user.setId(id);
		user.setEmail(email);
		user.setPassword(password);
		user.setRole(role);
		
		try {
			authentificationService.editUser(user);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ActionException(e);
		}
		
		HttpSession session = req.getSession();
		session.setAttribute("user", user);
		
		return Path.PROFILE_PAGE;
	}
}
