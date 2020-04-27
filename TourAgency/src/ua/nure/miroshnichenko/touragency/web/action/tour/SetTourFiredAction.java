package ua.nure.miroshnichenko.touragency.web.action.tour;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.miroshnichenko.touragency.service.TourService;
import ua.nure.miroshnichenko.touragency.service.exception.ServiceException;
import ua.nure.miroshnichenko.touragency.web.Path;
import ua.nure.miroshnichenko.touragency.web.action.Action;
import ua.nure.miroshnichenko.touragency.web.action.ActionException;

/**
 * The action to set a status of a specific tour.
 * 
 * @author Miroshnichenko Y. D.
 */
public class SetTourFiredAction extends Action {

	private static final long serialVersionUID = 5218346625153926977L;

	private final Logger LOG = Logger.getLogger(SetTourFiredAction.class);
	
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException, ActionException {

		TourService tourService = serviceFactory.getTourService();
		
		int tourId = Integer.parseInt(req.getParameter("id"));
		int status = Integer.parseInt(req.getParameter("status"));
		
		try {
			tourService.setTourStatus(tourId, status);
		} catch (ServiceException e) {
			LOG.error(e);
			throw new ActionException(e);
		}
		
		return "redirect:" + Path.getControllerPath("allTours");
	}
}