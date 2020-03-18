package ua.nure.miroshnichenko.summarytask4.web.action;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.miroshnichenko.summarytask4.db.entity.Hotel;
import ua.nure.miroshnichenko.summarytask4.db.entity.Tour;
import ua.nure.miroshnichenko.summarytask4.db.entity.TourType;
import ua.nure.miroshnichenko.summarytask4.db.entity.Transport;
import ua.nure.miroshnichenko.summarytask4.service.HotelService;
import ua.nure.miroshnichenko.summarytask4.service.ServiceException;
import ua.nure.miroshnichenko.summarytask4.service.TourService;
import ua.nure.miroshnichenko.summarytask4.service.TransportService;
import ua.nure.miroshnichenko.summarytask4.web.Path;

public class AddTourAction extends Action {

	private static final long serialVersionUID = -1268399077232461229L;

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException, ActionException {
		
		TourService tourService = serviceFactory.geTourService();
		HotelService hotelService = serviceFactory.getHotelService();
		TransportService transportService = serviceFactory.getTransportService();
		
		String[] startDate = req.getParameter("startDate").split("/");
		String[] endDate = req.getParameter("endDate").split("/");
		Double agencyProcent = Double.parseDouble(req.getParameter("agencyProcent"));
		Integer isFired = req.getParameterValues("isFired").length > 0 ? 1 : 0;
		Double maxDiscount = Double.parseDouble(req.getParameter("maxDiscount"));
		
		TourType type = TourType.valueOf(req.getParameter("type"));
		
		Hotel hotel = null;
		Transport transportTo = null;
		Transport transportBack = null;
		
		Date start = Date.valueOf(startDate[2] + "-" + startDate[0] + "-" + startDate[1]);
		Date end = Date.valueOf(endDate[2] + "-" + endDate[0] + "-" + endDate[1]);

		try {
			hotel = hotelService.get(Integer.parseInt(req.getParameter("hotel")));
			transportTo = transportService.get(Integer.parseInt(req.getParameter("transportTo")));
			transportBack = transportService.get(Integer.parseInt(req.getParameter("transportBack")));
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ActionException(e);
		}
		
		
		Tour tour = new Tour();
		
		tour.setType(type);
		tour.setStartDate(start);
		tour.setEndDate(end);
		tour.setAgencyProcent(agencyProcent);
		tour.setFired(isFired);
		tour.setMaxDiscount(maxDiscount);
		tour.setHotelId(hotel.getId());
		tour.setTransportToId(transportTo.getId());
		tour.setTransportBackId(transportBack.getId());
		
		try {	
			tourService.save(tour);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ActionException(e);
		}
		
		return Path.ADMIN_PAGE;
	}
}