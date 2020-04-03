package ua.nure.miroshnichenko.touragency.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.miroshnichenko.touragency.db.entity.Reservation;
import ua.nure.miroshnichenko.touragency.db.entity.User;
import ua.nure.miroshnichenko.touragency.web.Path;
import ua.nure.miroshnichenko.touragency.web.action.Action;
import ua.nure.miroshnichenko.touragency.web.action.ActionException;

public class RevokeReservationAction extends Action {

	private static final long serialVersionUID = -8944092514168747301L;

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException, ActionException {

		HttpSession session = req.getSession();
		
		TourService tourService = serviceFactory.getTourService();
		
		Integer reservationId = Integer.parseInt(req.getParameter("reservationId"));
		
		User user = (User) session.getAttribute("user");
		
		List<Reservation> reservations;
		try {
			tourService.revoke(reservationId);
			reservations = tourService.getUserReservations(user);
		} catch (ServiceException e) {
			throw new ActionException(e);
		}
		
		req.setAttribute("reservations", reservations);
		
		return Path.PROFILE_PAGE;
	}
}
