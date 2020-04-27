package ua.nure.miroshnichenko.touragency.web.action.reservation;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.miroshnichenko.touragency.db.entity.User;
import ua.nure.miroshnichenko.touragency.service.TourService;
import ua.nure.miroshnichenko.touragency.service.exception.ServiceException;
import ua.nure.miroshnichenko.touragency.web.Path;
import ua.nure.miroshnichenko.touragency.web.action.Action;
import ua.nure.miroshnichenko.touragency.web.action.ActionException;

/**
 * The action to revoke a specific reservation.
 * 
 * @author Miroshnichenko Y. D.
 */
public class RevokeReservationAction extends Action {

	private static final long serialVersionUID = 516852755858658688L;

	private final Logger LOG = Logger.getLogger(RevokeReservationAction.class);
	
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException, ActionException {

		HttpSession session = req.getSession();

		TourService tourService = serviceFactory.getTourService();

		int id = Integer.parseInt(req.getParameter("id"));

		try {
			tourService.revoke(id);
		} catch (ServiceException e) {
			LOG.error(e);
			throw new ActionException(e);
		}

		User user = (User) session.getAttribute("user");

		if (user != null) {
			Map<String, Object> params = new HashMap<>();
			params.put("id", user.getId());
			
			return "redirect:" + Path.getControllerPath("allReservations", params);
		} 
		return "redirect:" + Path.LOGIN_PAGE;
	}
}
