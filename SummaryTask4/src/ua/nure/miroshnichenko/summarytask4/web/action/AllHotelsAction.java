package ua.nure.miroshnichenko.summarytask4.web.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.miroshnichenko.summarytask4.db.entity.Hotel;
import ua.nure.miroshnichenko.summarytask4.service.HotelService;
import ua.nure.miroshnichenko.summarytask4.service.ServiceException;
import ua.nure.miroshnichenko.summarytask4.web.Path;

public class AllHotelsAction extends Action {

	private static final long serialVersionUID = 1716324192606085168L;

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException, ActionException {
	
		HotelService hotelService = serviceFactory.getHotelService();
		
		List<Hotel> hotels;
		try {
			hotels = hotelService.getAll();
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ActionException(e);
		}
		System.out.println(hotels.get(0).getStars());
		req.setAttribute("hotels", hotels);
		req.setAttribute("form", Path.HOTELS_LIST);
		
		return Path.ADMIN_PAGE;
	}
}
