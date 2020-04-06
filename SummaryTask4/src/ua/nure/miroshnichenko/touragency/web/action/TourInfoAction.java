package ua.nure.miroshnichenko.touragency.web.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.miroshnichenko.touragency.db.entity.Tour;
import ua.nure.miroshnichenko.touragency.service.ServiceException;
import ua.nure.miroshnichenko.touragency.service.TourService;
import ua.nure.miroshnichenko.touragency.web.Path;

public class TourInfoAction extends Action {

	private static final long serialVersionUID = 1L;

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException, ActionException {
		
		TourService tourService = serviceFactory.getTourService();

		Integer id = Integer.parseInt(req.getParameter("id"));
		
		Tour tour;
		List<String> photos;
		try {
			tour = tourService.get(id);
		} catch (ServiceException e) {
			throw new ActionException(e);
		}
		
		photos = ActionUtil.getHotelPhotos(
				tour.getHotelId(),
				ActionUtil.getPhotosFolderPath(req));
		
		req.setAttribute("tour", tour);
		req.setAttribute("photos", photos);
		
		return Path.TOUR_INFO;
	}
}
