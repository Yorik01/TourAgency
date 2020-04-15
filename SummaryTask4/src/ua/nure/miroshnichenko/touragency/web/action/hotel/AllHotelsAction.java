package ua.nure.miroshnichenko.touragency.web.action.hotel;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.miroshnichenko.touragency.db.entity.Hotel;
import ua.nure.miroshnichenko.touragency.service.HotelService;
import ua.nure.miroshnichenko.touragency.service.TourService;
import ua.nure.miroshnichenko.touragency.web.Path;
import ua.nure.miroshnichenko.touragency.web.action.Action;
import ua.nure.miroshnichenko.touragency.web.action.ActionException;
import ua.nure.miroshnichenko.touragency.web.action.ActionUtil;

public class AllHotelsAction extends Action {

	private static final long serialVersionUID = 1716324192606085168L;

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException, ActionException {
	
		HotelService hotelService = serviceFactory.getHotelService();
		
		List<Hotel> hotels = ActionUtil.getAllEntities(hotelService);

		TourService tourService = serviceFactory.getTourService();
		ActionUtil.setAllEntitiesJsonInAttribute(req, tourService, "toursJSON");
		
		req.setAttribute("hotels", hotels);
		req.setAttribute("form", Path.HOTELS_LIST);
		
		return Path.ADMIN_PAGE;
	}
}