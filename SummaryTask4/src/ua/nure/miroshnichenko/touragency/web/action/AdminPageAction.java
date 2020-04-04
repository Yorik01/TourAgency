package ua.nure.miroshnichenko.touragency.web.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.miroshnichenko.touragency.db.entity.Tour;
import ua.nure.miroshnichenko.touragency.service.ServiceException;
import ua.nure.miroshnichenko.touragency.service.TourService;
import ua.nure.miroshnichenko.touragency.web.Path;

public class AdminPageAction extends Action {

	private static final long serialVersionUID = 1034429626091756877L;
	
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException, ActionException {
	
		TourService tourService = serviceFactory.getTourService();
		
		List<Tour> tours;
		try {
			tours = tourService.getAll();
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ActionException(e);
		}
		
		String toursJson = ActionUtil.entitiesToJson(tours);
		req.setAttribute("tours", toursJson);
		
		return Path.ADMIN_PAGE;
	}
}
