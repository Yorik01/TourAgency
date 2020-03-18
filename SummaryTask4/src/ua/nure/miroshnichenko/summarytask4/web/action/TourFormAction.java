package ua.nure.miroshnichenko.summarytask4.web.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.miroshnichenko.summarytask4.db.entity.Hotel;
import ua.nure.miroshnichenko.summarytask4.db.entity.Transport;
import ua.nure.miroshnichenko.summarytask4.service.HotelService;
import ua.nure.miroshnichenko.summarytask4.service.ServiceException;
import ua.nure.miroshnichenko.summarytask4.service.TransportService;
import ua.nure.miroshnichenko.summarytask4.web.Path;

public class TourFormAction extends Action {

	private static final long serialVersionUID = 200523579087008829L;

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException, ActionException {
		
		TransportService transportService = serviceFactory.getTransportService();
		HotelService hotelService = serviceFactory.getHotelService();
		
		
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
		
		return Path.ADMIN_PAGE;
	}
}
