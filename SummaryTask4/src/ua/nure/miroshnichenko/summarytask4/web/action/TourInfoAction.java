package ua.nure.miroshnichenko.summarytask4.web.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.miroshnichenko.summarytask4.db.entity.Tour;
import ua.nure.miroshnichenko.summarytask4.service.ServiceException;
import ua.nure.miroshnichenko.summarytask4.service.TourService;
import ua.nure.miroshnichenko.summarytask4.web.Path;

public class TourInfoAction extends Action {

	private static final long serialVersionUID = 1L;

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException, ActionException {
		
		TourService tourService = serviceFactory.getTourService();

		Integer id = Integer.parseInt(req.getParameter("id"));
		
		Tour tour;
		try {
			tour = tourService.get(id);
		} catch (ServiceException e) {
			throw new ActionException(e);
		}
		
		req.setAttribute("tour", tour);
		
		return Path.TOUR_INFO;
	}
}
