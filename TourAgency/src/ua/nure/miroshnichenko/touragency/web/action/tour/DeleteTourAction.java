package ua.nure.miroshnichenko.touragency.web.action.tour;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.miroshnichenko.touragency.db.entity.Tour;
import ua.nure.miroshnichenko.touragency.service.TourService;
import ua.nure.miroshnichenko.touragency.service.exception.ServiceException;
import ua.nure.miroshnichenko.touragency.web.Path;
import ua.nure.miroshnichenko.touragency.web.action.Action;
import ua.nure.miroshnichenko.touragency.web.action.ActionException;

/**
 * The action to delete a specific tour.
 * 
 * @author Miroshnichenko Y. D.
 */
public class DeleteTourAction extends Action {

	private static final long serialVersionUID = -7275937969344267240L;

	private final Logger LOG = Logger.getLogger(DeleteTourAction.class);
	
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
			LOG.error(e);
			throw new ActionException(e);
		}
		
		return "redirect:" + Path.getControllerPath("allTours");
	}
}
