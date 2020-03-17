package ua.nure.miroshnichenko.summarytask4.web.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.miroshnichenko.summarytask4.db.entity.Tour;
import ua.nure.miroshnichenko.summarytask4.service.ServiceException;
import ua.nure.miroshnichenko.summarytask4.service.TourService;
import ua.nure.miroshnichenko.summarytask4.web.Path;

public class AllToursAction extends Action {

	private static final long serialVersionUID = -5757678081770546971L;

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException, ActionException {
		
		TourService tourService = serviceFactory.geTourService();
		List<Tour> tours = null;
		try {
			tours = tourService.getAll();
			tours.sort((x, y) -> x.isFired().compareTo(y.isFired()));
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
		req.setAttribute("tours", tours);
		return Path.TOURS_PAGE;
	}
}
