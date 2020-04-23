package ua.nure.miroshnichenko.touragency.web.action.tour;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.miroshnichenko.touragency.db.entity.Hotel;
import ua.nure.miroshnichenko.touragency.db.entity.Tour;
import ua.nure.miroshnichenko.touragency.db.entity.TourType;
import ua.nure.miroshnichenko.touragency.db.entity.Transport;
import ua.nure.miroshnichenko.touragency.service.HotelService;
import ua.nure.miroshnichenko.touragency.service.TourService;
import ua.nure.miroshnichenko.touragency.service.TransportService;
import ua.nure.miroshnichenko.touragency.service.exception.ServiceException;
import ua.nure.miroshnichenko.touragency.web.Path;
import ua.nure.miroshnichenko.touragency.web.action.Action;
import ua.nure.miroshnichenko.touragency.web.action.ActionException;

public class SaveTourAction extends Action {

	private static final long serialVersionUID = -1268399077232461229L;

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException, ActionException {
		
		TourService tourService = serviceFactory.getTourService();
		HotelService hotelService = serviceFactory.getHotelService();
		TransportService transportService = serviceFactory.getTransportService();
		
		String[] startDate = req.getParameter("startDate").split("/");
		String[] endDate = req.getParameter("endDate").split("/");
		double agencyProcent = Integer.parseInt(req.getParameter("agencyProcent"));
		int isFired = req.getParameterValues("isFired") != null ? 1 : 0;
		double maxDiscount = Double.parseDouble(req.getParameter("maxDiscount"));
		
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
		
		if (Boolean.parseBoolean(req.getParameter("edit"))) {
			int id = Integer.parseInt(req.getParameter("id"));

			tour.setId(id);
			try {
				tourService.update(tour);
			} catch (ServiceException e) {
				e.printStackTrace();
				throw new ActionException(e);
			}
			res.sendRedirect(Path.getControllerPath("allTours"));
			
			return Path.NO_PATH;
		}
		
		try {	
			tourService.save(tour);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ActionException(e);
		}
		
		return "redirect:" + Path.getControllerPath("allTours");
	}
}