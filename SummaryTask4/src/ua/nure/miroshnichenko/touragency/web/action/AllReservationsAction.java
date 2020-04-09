package ua.nure.miroshnichenko.touragency.web.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.miroshnichenko.touragency.db.entity.Reservation;
import ua.nure.miroshnichenko.touragency.service.ServiceException;
import ua.nure.miroshnichenko.touragency.service.TourService;
import ua.nure.miroshnichenko.touragency.web.Path;

public class AllReservationsAction extends Action {

	private static final long serialVersionUID = -477977382607193784L;

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException, ActionException {
	
		TourService tourService = serviceFactory.getTourService();
		
		List<Reservation> reservations;
		try {
			reservations = tourService.getAllReserved();
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ActionException(e);
		}
		
		ActionUtil.setAllEntitiesJsonInAttribute(req, tourService, "toursJSON");
		
		req.setAttribute("reservations", reservations);
		req.setAttribute("form", Path.RESERVATIONS_LIST);
		
		return Path.ADMIN_PAGE;
	}
}
