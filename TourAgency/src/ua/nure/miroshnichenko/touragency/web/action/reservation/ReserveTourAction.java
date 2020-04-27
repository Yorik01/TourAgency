package ua.nure.miroshnichenko.touragency.web.action.reservation;

import java.io.IOException;

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
 * The action to reserve a specific tour.
 * 
 * @author Miroshnichenko Y. D.
 */
public class ReserveTourAction extends Action {

	private static final long serialVersionUID = -2053383474777835419L;

	private final Logger LOG = Logger.getLogger(ReserveTourAction.class);
	
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException, ActionException {

		HttpSession session = req.getSession();
		
		TourService tourService = serviceFactory.getTourService();
		
		User user = (User) session.getAttribute("user");
		
		int tourId = Integer.parseInt(req.getParameter("id"));
		int peopleCount = Integer.parseInt(req.getParameter("peopleCount"));
		
		try {
			tourService.reserve(tourId, user.getId(), peopleCount);
		} catch (ServiceException e) {
			LOG.error(e);
			throw new ActionException(e);
		}
		
		return "redirect:" + Path.getControllerPath("filterForm");
	}
}
