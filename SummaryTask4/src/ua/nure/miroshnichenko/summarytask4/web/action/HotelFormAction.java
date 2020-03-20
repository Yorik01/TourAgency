package ua.nure.miroshnichenko.summarytask4.web.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.miroshnichenko.summarytask4.db.entity.Hotel;
import ua.nure.miroshnichenko.summarytask4.service.HotelService;
import ua.nure.miroshnichenko.summarytask4.service.ServiceException;
import ua.nure.miroshnichenko.summarytask4.web.Path;

public class HotelFormAction extends Action {

	private static final long serialVersionUID = -286717308487609514L;

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException, ActionException {
		
		HotelService hotelService = serviceFactory.getHotelService();
		
		req.setAttribute("form", Path.HOTEL_FORM);
		
		if(Boolean.parseBoolean(req.getParameter("edit"))) {
			Integer id = Integer.parseInt(req.getParameter("id"));
			Hotel hotel;
			
			try {
				hotel = hotelService.get(id);
			} catch (ServiceException e) {
				e.printStackTrace();
				throw new ActionException(e);
			}
			req.setAttribute("hotel", hotel);
		}
		
		return Path.ADMIN_PAGE;
	}
}
