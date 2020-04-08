package ua.nure.miroshnichenko.touragency.web.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.miroshnichenko.touragency.service.BanningService;
import ua.nure.miroshnichenko.touragency.service.ServiceException;
import ua.nure.miroshnichenko.touragency.web.Path;

public class BannUserAction extends Action {

	private static final long serialVersionUID = 7322356944138418344L;

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException, ActionException {

		BanningService banningService = serviceFactory.getBanningService();
		int userId = Integer.parseInt(req.getParameter("id"));
		try {
			banningService.banUser(userId);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ActionException(e);
		}
		
		res.sendRedirect(Path.getControllerPath("adminPage"));
		
		return Path.NO_PATH;
	}
}
