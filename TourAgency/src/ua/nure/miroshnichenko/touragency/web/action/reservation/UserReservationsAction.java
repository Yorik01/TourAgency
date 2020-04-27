package ua.nure.miroshnichenko.touragency.web.action.reservation;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.miroshnichenko.touragency.db.entity.Reservation;
import ua.nure.miroshnichenko.touragency.db.entity.User;
import ua.nure.miroshnichenko.touragency.service.TourService;
import ua.nure.miroshnichenko.touragency.service.exception.ServiceException;
import ua.nure.miroshnichenko.touragency.web.Path;
import ua.nure.miroshnichenko.touragency.web.action.Action;
import ua.nure.miroshnichenko.touragency.web.action.ActionException;

/**
 * The action to get reservations of a specific user.
 * 
 * @author Miroshnichenko Y. D.
 */
public class UserReservationsAction extends Action {

	private static final long serialVersionUID = 478021022284155296L;

	private final Logger LOG = Logger.getLogger(UserReservationsAction.class);
	
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException, ActionException {

		HttpSession session = req.getSession();
		
		TourService tourService = serviceFactory.getTourService();
		
		User user = (User) session.getAttribute("user");
		
		List<Reservation> reservations;
		try {
			reservations = tourService.getUserReservations(user);
		} catch (ServiceException e) {
			LOG.error(e);
			throw new ActionException(e);
		}
		
		req.setAttribute("reservations", reservations);
		req.setAttribute("form", Path.USER_RESERVATIONS_LIST);
		
		return Path.PROFILE_PAGE;
	}
}