package ua.nure.miroshnichenko.touragency.web.action.hotel;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.miroshnichenko.touragency.db.entity.Hotel;
import ua.nure.miroshnichenko.touragency.service.HotelService;
import ua.nure.miroshnichenko.touragency.service.exception.ServiceException;
import ua.nure.miroshnichenko.touragency.web.Path;
import ua.nure.miroshnichenko.touragency.web.action.Action;
import ua.nure.miroshnichenko.touragency.web.action.ActionException;
import ua.nure.miroshnichenko.touragency.web.action.ActionUtil;

public class DeleteHotelAction extends Action {

	private static final long serialVersionUID = -3937865183784785335L;

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException, ActionException {
		
		HotelService hotelService = serviceFactory.getHotelService();
		
		int id = Integer.parseInt(req.getParameter("id"));
		
		Hotel hotel = new Hotel();
		hotel.setId(id);
		
		try {
			hotelService.delete(hotel);
			ActionUtil.deleteAllHotelPhotos(id, req);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ActionException(e);
		}
		
		return "redirect:" + Path.getControllerPath("allHotels");
	}
}
