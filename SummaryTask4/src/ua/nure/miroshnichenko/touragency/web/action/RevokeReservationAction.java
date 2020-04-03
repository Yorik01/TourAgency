package ua.nure.miroshnichenko.touragency.web.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.miroshnichenko.touragency.service.ServiceException;
import ua.nure.miroshnichenko.touragency.service.TourService;
import ua.nure.miroshnichenko.touragency.web.Path;

public class RevokeReservationAction extends Action {

	private static final long serialVersionUID = 516852755858658688L;

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException, ActionException {

		TourService tourService = serviceFactory.getTourService();
		
		Integer id = Integer.parseInt(req.getParameter("id"));
		
		try {
			tourService.revoke(id);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ActionException(e);
		}
		res.sendRedirect("/TourAgency/controller?action=userReservations&id=2");
		
		return Path.NO_PATH;
	}
}