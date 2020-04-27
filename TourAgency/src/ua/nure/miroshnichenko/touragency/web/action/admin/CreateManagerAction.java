package ua.nure.miroshnichenko.touragency.web.action.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.miroshnichenko.touragency.db.entity.Role;
import ua.nure.miroshnichenko.touragency.db.entity.User;
import ua.nure.miroshnichenko.touragency.service.AuthentificationService;
import ua.nure.miroshnichenko.touragency.service.exception.ServiceException;
import ua.nure.miroshnichenko.touragency.web.Path;
import ua.nure.miroshnichenko.touragency.web.action.Action;
import ua.nure.miroshnichenko.touragency.web.action.ActionException;

/**
 * The action to create a manager.
 * 
 * @author Miroshnichenko Y. D.
 */
public class CreateManagerAction extends Action {

	private static final long serialVersionUID = 1531131313350404858L;

	private final Logger LOG = Logger.getLogger(CreateManagerAction.class);
	
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException, ActionException {
		
		AuthentificationService authentificationService = 
				serviceFactory.getAuthentificationService();
	
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		User user = new User();
	
		user.setEmail(email);
		user.setPassword(password);
		user.setRole(Role.MANAGER);
		
		try {
			authentificationService.signup(user);
		} catch (ServiceException e) {
			LOG.error(e);
			throw new ActionException(e);
		}
		
		return Path.NO_PATH;
	}
}
