package ua.nure.miroshnichenko.touragency.web.action.tour;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.miroshnichenko.touragency.db.entity.Hotel;
import ua.nure.miroshnichenko.touragency.db.entity.Tour;
import ua.nure.miroshnichenko.touragency.db.entity.Transport;
import ua.nure.miroshnichenko.touragency.service.DiscountService;
import ua.nure.miroshnichenko.touragency.service.HotelService;
import ua.nure.miroshnichenko.touragency.service.TourService;
import ua.nure.miroshnichenko.touragency.service.TransportService;
import ua.nure.miroshnichenko.touragency.service.exception.ServiceException;
import ua.nure.miroshnichenko.touragency.web.Path;
import ua.nure.miroshnichenko.touragency.web.action.Action;
import ua.nure.miroshnichenko.touragency.web.action.ActionException;
import ua.nure.miroshnichenko.touragency.web.action.ActionUtil;

public class TourFormAction extends Action {

	private static final long serialVersionUID = 200523579087008829L;

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException, ActionException {
		
		TransportService transportService = serviceFactory.getTransportService();
		HotelService hotelService = serviceFactory.getHotelService();
		TourService tourService = serviceFactory.getTourService();
		
		List<Hotel> hotels = null;
		List<Transport> transports = null;
		
		try {
			hotels = hotelService.getAll();
			transports = transportService.getAll();
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ActionException(e);
		}
		
		req.setAttribute("form", Path.TOUR_FORM);
		req.setAttribute("hotels", hotels);
		req.setAttribute("transports", transports);
		
		if(Boolean.parseBoolean(req.getParameter("edit"))) {
			int id = Integer.parseInt(req.getParameter("id"));
			Tour tour;
			
			try {
				tour = tourService.get(id);
			} catch (ServiceException e) {
				e.printStackTrace();
				throw new ActionException(e);
			}
			
			req.setAttribute("tour", tour);
		}

		DiscountService discountService = serviceFactory.getDiscountService();
		
		ActionUtil.setAllEntitiesJsonInAttribute(req, tourService, "toursJSON");
		ActionUtil.setDiscountStepInAttribute(discountService, req);
		
		return Path.ADMIN_PAGE;
	}
}
