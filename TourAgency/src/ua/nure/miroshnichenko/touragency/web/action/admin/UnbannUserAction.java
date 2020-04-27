package ua.nure.miroshnichenko.touragency.web.action.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.miroshnichenko.touragency.service.BanningService;
import ua.nure.miroshnichenko.touragency.service.exception.ServiceException;
import ua.nure.miroshnichenko.touragency.web.Path;
import ua.nure.miroshnichenko.touragency.web.action.Action;
import ua.nure.miroshnichenko.touragency.web.action.ActionException;

/**
 * The action to unban a specific user.
 * 
 * @author Miroshnichenko Y. D.
 */
public class UnbannUserAction extends Action {

	private static final long serialVersionUID = 7213128851568366466L;

	private final Logger LOG = Logger.getLogger(UnbannUserAction.class);
	
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException, ActionException {

		BanningService banningService = serviceFactory.getBanningService();

		int userId = Integer.parseInt(req.getParameter("id"));
		try {
			banningService.unbanUser(userId);
		} catch (ServiceException e) {
			LOG.error(e);
			throw new ActionException(e);
		}

		return "redirect:" + Path.getControllerPath("adminPage");
	}
}
