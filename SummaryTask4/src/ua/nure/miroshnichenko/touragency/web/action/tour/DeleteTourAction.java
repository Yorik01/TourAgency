package ua.nure.miroshnichenko.touragency.web.action.tour;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.miroshnichenko.touragency.db.entity.Tour;
import ua.nure.miroshnichenko.touragency.service.TourService;
import ua.nure.miroshnichenko.touragency.service.exception.ServiceException;
import ua.nure.miroshnichenko.touragency.web.Path;
import ua.nure.miroshnichenko.touragency.web.action.Action;
import ua.nure.miroshnichenko.touragency.web.action.ActionException;

public class DeleteTourAction extends Action {

	private static final long serialVersionUID = -7275937969344267240L;

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException, ActionException {

		TourService tourService = serviceFactory.getTourService();
		
		Integer id = Integer.parseInt(req.getParameter("id"));
		
		Tour tour = new Tour();
		tour.setId(id);
		
		try {
			tourService.delete(tour);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ActionException(e);
		}
		
		res.sendRedirect(Path.getControllerPath("allTours"));
		
		return Path.NO_PATH;
	}
}
