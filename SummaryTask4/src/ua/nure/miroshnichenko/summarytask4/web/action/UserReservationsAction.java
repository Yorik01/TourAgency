package ua.nure.miroshnichenko.summarytask4.web.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.miroshnichenko.summarytask4.db.entity.Reservation;
import ua.nure.miroshnichenko.summarytask4.db.entity.User;
import ua.nure.miroshnichenko.summarytask4.service.ServiceException;
import ua.nure.miroshnichenko.summarytask4.service.TourService;
import ua.nure.miroshnichenko.summarytask4.web.Path;

public class UserReservationsAction extends Action {

	private static final long serialVersionUID = 478021022284155296L;

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
			e.printStackTrace();
			throw new ActionException(e);
		}
		
		req.setAttribute("reservations", reservations);
		
		return Path.PROFILE_PAGE;
	}
}