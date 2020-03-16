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
import ua.nure.miroshnichenko.summarytask4.db.entity.TransportType;
import ua.nure.miroshnichenko.summarytask4.service.HotelService;
import ua.nure.miroshnichenko.summarytask4.service.ServiceException;
import ua.nure.miroshnichenko.summarytask4.service.TourService;
import ua.nure.miroshnichenko.summarytask4.service.TransportService;

public class AddTourAction extends Action {

	private static final long serialVersionUID = -1268399077232461229L;

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException, ActionException {
		
		TourService tourService = serviceFactory.geTourService();
		HotelService hotelService = serviceFactory.getHotelService();
		TransportService transportService = serviceFactory.getTransportService();
		
		Date startDate = Date.valueOf(req.getParameter("startDate"));
		Date endDate = Date.valueOf(req.getParameter("endDate"));
		Double agencyProcent = Double.valueOf(req.getParameter("agencyProcent"));
		Byte isFired = Byte.valueOf(req.getParameter("isFired"));
		Double maxDiscount = Double.valueOf(req.getParameter("maxDiscount"));
		
		TourType type = TourType.valueOf(req.getParameter("type"));
		
		Hotel hotel = null;
		Transport transportTo = null;
		Transport transportBack = null;

		try {
			hotel = hotelService.getHotelByName(req.getParameter("hotel"));
			transportTo = transportService.getTransportByCodeAndType(
					req.getParameter("transportToCode"),
					TransportType.valueOf(req.getParameter("transportToType")));
			transportBack = transportService.getTransportByCodeAndType(
					req.getParameter("transportBackCode"),
					TransportType.valueOf(req.getParameter("transportBackType")));
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ActionException(e);
		}
		
		
		Tour tour = new Tour();
		
		tour.setStartDate(startDate);
		tour.setEndDate(endDate);
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
		
		return "/SummaryTask4/admin.jsp";
	}
}
