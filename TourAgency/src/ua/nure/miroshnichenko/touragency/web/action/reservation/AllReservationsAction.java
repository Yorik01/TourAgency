package ua.nure.miroshnichenko.touragency.web.action.reservation;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.miroshnichenko.touragency.db.entity.Reservation;
import ua.nure.miroshnichenko.touragency.service.DiscountService;
import ua.nure.miroshnichenko.touragency.service.TourService;
import ua.nure.miroshnichenko.touragency.service.exception.ServiceException;
import ua.nure.miroshnichenko.touragency.web.Path;
import ua.nure.miroshnichenko.touragency.web.action.Action;
import ua.nure.miroshnichenko.touragency.web.action.ActionException;
import ua.nure.miroshnichenko.touragency.web.action.ActionUtil;

/**
 * The action to get all reservations.
 * 
 * @author Miroshnichenko Y. D.
 */
public class AllReservationsAction extends Action {

	private static final long serialVersionUID = -477977382607193784L;

	private final Logger LOG = Logger.getLogger(AllReservationsAction.class);
	
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException, ActionException {
	
		TourService tourService = serviceFactory.getTourService();
		
		List<Reservation> reservations;
		try {
			reservations = tourService.getAllReserved();
		} catch (ServiceException e) {
			LOG.error(e);
			throw new ActionException(e);
		}
		
		DiscountService discountService = serviceFactory.getDiscountService();
		
		ActionUtil.setAllEntitiesJsonInAttribute(req, tourService, "toursJSON");
		ActionUtil.setDiscountStepInAttribute(discountService, req);		

		req.setAttribute("reservations", reservations);
		req.setAttribute("form", Path.RESERVATIONS_LIST);
		
		return Path.ADMIN_PAGE;
	}
}
