package ua.nure.miroshnichenko.summarytask4.web.action;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import ua.nure.miroshnichenko.summarytask4.db.entity.Beach;
import ua.nure.miroshnichenko.summarytask4.db.entity.Facility;
import ua.nure.miroshnichenko.summarytask4.db.entity.Food;
import ua.nure.miroshnichenko.summarytask4.db.entity.Hotel;
import ua.nure.miroshnichenko.summarytask4.db.entity.HotelType;
import ua.nure.miroshnichenko.summarytask4.db.entity.Servicing;
import ua.nure.miroshnichenko.summarytask4.service.HotelService;
import ua.nure.miroshnichenko.summarytask4.service.ServiceException;
import ua.nure.miroshnichenko.summarytask4.web.Path;

public class SaveHotelAction extends Action {

	private static final long serialVersionUID = -8849483309313250899L;

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException, ActionException {

		HotelService hotelService = serviceFactory.getHotelService();

		Map<String, String[]> parametrs = req.getParameterMap();

		String name = req.getParameter("name");
		String info = req.getParameter("info");
		String address = req.getParameter("address");
		String tel = req.getParameter("tel");
		String site = req.getParameter("site");
		Integer stars = Integer.parseInt(req.getParameter("stars"));
		Integer maxRooms = Integer.parseInt(req.getParameter("maxRooms"));
		Double price = Double.parseDouble(req.getParameter("price"));
		HotelType type = HotelType.valueOf(req.getParameter("type"));
		Food food = Food.valueOf(req.getParameter("food"));
		Beach beach = Beach.valueOf(req.getParameter("beach"));

		Set<Facility> facilities = new HashSet<>();

		for (String facility : parametrs.get("facilities")) {
			facilities.add(new Facility(facility));
		}

		Set<Servicing> servicings = new HashSet<>();

		for (String servicing : parametrs.get("servicings")) {
			servicings.add(new Servicing(servicing));
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

		if (Boolean.parseBoolean(req.getParameter("edit"))) {
			Integer id = Integer.parseInt(req.getParameter("id"));

			hotel.setId(id);
			try {
				hotelService.update(hotel);
			} catch (ServiceException e) {
				e.printStackTrace();
				throw new ActionException(e);
			}
			return Path.ADMIN_PAGE;
		}

		try {
			hotelService.save(hotel);
		} catch (ServiceException e) {
			e.printStackTrace();
		}

		uploadImgs(req, res, hotel.getId());

		return Path.ADMIN_PAGE;
	}

	private void uploadImgs(HttpServletRequest req, HttpServletResponse res, int hotelId)
			throws IOException, ServletException, ActionException {
		List<Part> fileParts = req.getParts().stream().filter(part -> "photo".equals(part.getName()))
				.collect(Collectors.toList());

		for (Part part : fileParts) {
			UUID uuid = UUID.randomUUID();
			String fileName = "hotelPhoto_" + hotelId + "_" + uuid;

			OutputStream out = null;
			InputStream fileContent = null;
			final File file = new File("photo" + File.separator + fileName);
			try {
				if (file.createNewFile()) {
					out = new FileOutputStream(file);
					fileContent = part.getInputStream();

					int read = 0;
					final byte[] bytes = new byte[1024];

					while ((read = fileContent.read(bytes)) != -1) {
						out.write(bytes, 0, read);
					}
				} else {
					throw new ActionException("Cannot upload file!");
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				throw new ActionException(e);
			} finally {
				if (out != null) {
					out.close();
				}

				if (fileContent != null) {
					fileContent.close();
				}
			}
		}
	}
}