package ua.nure.miroshnichenko.touragency.web.action.hotel;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.miroshnichenko.touragency.db.entity.Hotel;
import ua.nure.miroshnichenko.touragency.service.DiscountService;
import ua.nure.miroshnichenko.touragency.service.HotelService;
import ua.nure.miroshnichenko.touragency.service.TourService;
import ua.nure.miroshnichenko.touragency.service.exception.ServiceException;
import ua.nure.miroshnichenko.touragency.web.Path;
import ua.nure.miroshnichenko.touragency.web.action.Action;
import ua.nure.miroshnichenko.touragency.web.action.ActionException;
import ua.nure.miroshnichenko.touragency.web.action.ActionUtil;

/**
 * The action to get the form to fill information about hotel.
 * 
 * @author Miroshnichenko Y. D.
 */
public class HotelFormAction extends Action {

	private static final long serialVersionUID = -286717308487609514L;

	private final Logger LOG = Logger.getLogger(HotelFormAction.class);
	
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException, ActionException {
		
		HotelService hotelService = serviceFactory.getHotelService();
		
		req.setAttribute("form", Path.HOTEL_FORM);
		
		if(Boolean.parseBoolean(req.getParameter("edit"))) {
			int id = Integer.parseInt(req.getParameter("id"));
			
			Hotel hotel;
			List<String> photos;
			try {
				hotel = hotelService.get(id);
				photos = ActionUtil.getHotelPhotos(id, req);
			} catch (ServiceException e) {
				LOG.error(e);
				throw new ActionException(e);
			}
			
			req.setAttribute("hotel", hotel);
			req.setAttribute("photos", photos);
		}
		
		TourService tourService = serviceFactory.getTourService();
		DiscountService discountService = serviceFactory.getDiscountService();
		
		ActionUtil.setAllEntitiesJsonInAttribute(req, tourService, "toursJSON");
		ActionUtil.setDiscountStepInAttribute(discountService, req);
		
		return Path.ADMIN_PAGE;
	}
}
