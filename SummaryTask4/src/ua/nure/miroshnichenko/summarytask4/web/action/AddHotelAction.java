package ua.nure.miroshnichenko.summarytask4.web.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.miroshnichenko.summarytask4.db.entity.Beach;
import ua.nure.miroshnichenko.summarytask4.db.entity.Facility;
import ua.nure.miroshnichenko.summarytask4.db.entity.Food;
import ua.nure.miroshnichenko.summarytask4.db.entity.Hotel;
import ua.nure.miroshnichenko.summarytask4.db.entity.HotelType;
import ua.nure.miroshnichenko.summarytask4.db.entity.Servicing;
import ua.nure.miroshnichenko.summarytask4.db.entity.TypeServicing;
import ua.nure.miroshnichenko.summarytask4.service.HotelService;
import ua.nure.miroshnichenko.summarytask4.service.ServiceException;

public class AddHotelAction extends Action {

	private static final long serialVersionUID = -8849483309313250899L;

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException, ActionException {
		
		HotelService hotelService = serviceFactory.getHotelService();
		
		String name = req.getParameter("name");
		String info = req.getParameter("info");
		String address = req.getParameter("address");
		String tel = req.getParameter("tel");
		String site = req.getParameter("site");
		Byte stars = Byte.valueOf(req.getParameter("stars"));
		Integer maxRooms = Integer.valueOf(req.getParameter("maxRooms"));
		Double price = Double.valueOf(req.getParameter("price"));
		HotelType type = HotelType.valueOf(req.getParameter("type"));
		Food food = Food.valueOf(req.getParameter("food"));
		Beach beach = Beach.valueOf(req.getParameter("beach"));
		
		Set<Facility> facilities = new HashSet<>();
		 
		facilities.add(new Facility(req.getParameter("wifi")));
		facilities.add(new Facility(req.getParameter("minibar")));
		facilities.add(new Facility(req.getParameter("hairdryer")));
		facilities.add(new Facility(req.getParameter("conditioner")));
		facilities.add(new Facility(req.getParameter("tv")));
		
		Set<Servicing> servicings = new HashSet<>();
		
		servicings.add(new Servicing(req.getParameter("gym")));
		servicings.add(new Servicing(req.getParameter("fitnes")));
		servicings.add(new Servicing(req.getParameter("diving")));
		servicings.add(new Servicing(req.getParameter("yoga")));

		servicings.add(new Servicing(req.getParameter("aquapark")));
		servicings.add(new Servicing(req.getParameter("disco")));
		servicings.add(new Servicing(req.getParameter("restaurant")));

		Hotel hotel = new Hotel();
		
		hotel.setName(name);
		hotel.setInfo(info);
		hotel.setAddress(address);
		hotel.setTel(tel);
		hotel.setSite(site);
		hotel.setStars(stars);
		hotel.setMaxRooms(maxRooms);
		hotel.setPrice(price);
		hotel.setType(type);
		hotel.setFood(food);
		hotel.setBeach(beach);
		hotel.setFacilities(facilities);
		hotel.setServicings(servicings);

		try {
			hotelService.save(hotel);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
		return "/SummaryTask4/admin.jsp";
	}
}