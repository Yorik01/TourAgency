package ua.nure.miroshnichenko.touragency.web.action.hotel;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.miroshnichenko.touragency.db.entity.Beach;
import ua.nure.miroshnichenko.touragency.db.entity.Facility;
import ua.nure.miroshnichenko.touragency.db.entity.Food;
import ua.nure.miroshnichenko.touragency.db.entity.Hotel;
import ua.nure.miroshnichenko.touragency.db.entity.HotelType;
import ua.nure.miroshnichenko.touragency.db.entity.Servicing;
import ua.nure.miroshnichenko.touragency.service.HotelService;
import ua.nure.miroshnichenko.touragency.service.exception.ServiceException;
import ua.nure.miroshnichenko.touragency.web.Path;
import ua.nure.miroshnichenko.touragency.web.action.Action;
import ua.nure.miroshnichenko.touragency.web.action.ActionException;
import ua.nure.miroshnichenko.touragency.web.action.ActionUtil;

public class SaveHotelAction extends Action {

	private static final long serialVersionUID = -8849483309313250899L;

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException, ActionException {

		HotelService hotelService = serviceFactory.getHotelService();

		Hotel hotel = initHotel(req);

		if (Boolean.parseBoolean(req.getParameter("edit"))) {
			int id = Integer.parseInt(req.getParameter("id"));

			hotel.setId(id);
			try {
				if (hotelService.update(hotel)) {
					String[] removedPhotos = req.getParameterValues("deletedPhotos");
					
					if (removedPhotos != null) {
						ActionUtil.deleteHotelPhotos(req);
					}
					ActionUtil.uploadImgs(req, res, hotel.getId());
				}
			} catch (ServiceException e) {
				e.printStackTrace();
				throw new ActionException(e);
			}
			
			return "redirect:" + Path.getControllerPath("allHotels");
		}

		try {
			hotelService.save(hotel);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ActionException(e);
		}

		ActionUtil.uploadImgs(req, res, hotel.getId());

		return "redirect:" + Path.getControllerPath("allHotels");
	}
	
	private Hotel initHotel(HttpServletRequest req) {
		Map<String, String[]> parametrs = req.getParameterMap();
		
		String name = req.getParameter("name");
		String info = req.getParameter("info");
		String address = req.getParameter("address");
		String tel = req.getParameter("tel");
		String site = req.getParameter("site");
		
		int stars = Integer.parseInt(req.getParameter("stars"));
		int maxRooms = Integer.parseInt(req.getParameter("maxRooms"));
		double price = Double.parseDouble(req.getParameter("price"));
		HotelType type = HotelType.valueOf(req.getParameter("type"));
		
		Food food = Food.valueOf(req.getParameter("food"));
		Beach beach = Beach.valueOf(req.getParameter("beach"));

		Set<Facility> facilities = new HashSet<>();

		String[] facilitiesStr = parametrs.get("facilities");
		if (facilitiesStr != null) {
			for (String facility : facilitiesStr) {
				facilities.add(new Facility(facility));
			}
		}

		Set<Servicing> servicings = new HashSet<>();

		String[] servicingsStr = parametrs.get("servicings");
		if (servicingsStr != null) {
			for (String servicing : servicingsStr) {
				servicings.add(new Servicing(servicing));
			}
		}
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
		
		return hotel;
	}
}