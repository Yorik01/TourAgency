package ua.nure.miroshnichenko.touragency.web.action.reservation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.miroshnichenko.touragency.db.entity.User;
import ua.nure.miroshnichenko.touragency.service.TourService;
import ua.nure.miroshnichenko.touragency.service.exception.ServiceException;
import ua.nure.miroshnichenko.touragency.web.Path;
import ua.nure.miroshnichenko.touragency.web.action.Action;
import ua.nure.miroshnichenko.touragency.web.action.ActionException;

/**
 * The action to pay a specific reservation.
 * 
 * @author Miroshnichenko Y. D.
 */
public class PayReservationAction extends Action {

	private static final long serialVersionUID = -490607545901059328L;

	private final Logger LOG = Logger.getLogger(PayReservationAction.class);
	
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException, ActionException {
		
		HttpSession session = req.getSession();
		
		TourService tourService = serviceFactory.getTourService();
		
		int id = Integer.parseInt(req.getParameter("id"));
		int managerId = ((User)session.getAttribute("user")).getId();
		
		try {
			tourService.pay(id, managerId);
		} catch (ServiceException e) {
			LOG.error(e);
			throw new ActionException(e);
		}
		
		return "redirect:" + (Path.getControllerPath("allReservations"));
	}
}
