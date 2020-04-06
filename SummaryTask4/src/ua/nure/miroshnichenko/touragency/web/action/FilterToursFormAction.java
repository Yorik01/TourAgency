package ua.nure.miroshnichenko.touragency.web.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.miroshnichenko.touragency.db.entity.Place;
import ua.nure.miroshnichenko.touragency.db.entity.Tour;
import ua.nure.miroshnichenko.touragency.service.RouteService;
import ua.nure.miroshnichenko.touragency.service.ServiceException;
import ua.nure.miroshnichenko.touragency.service.TourService;
import ua.nure.miroshnichenko.touragency.web.Path;

public class FilterToursFormAction extends Action {

	private static final long serialVersionUID = 142736073248551283L;

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException, ActionException {
		
		TourService tourService = serviceFactory.getTourService();

		RouteService routeService = serviceFactory.getRouteService();
		
		List<Place> places = null;
		List<Tour> tours = null;

		Map<Tour, String> toursWithPhotos = new HashMap<>();
		try {
			places = routeService.getAllPlaces();
			tours = tourService.getAll();
			
			tours.sort((x, y) -> x.isFired().compareTo(y.isFired()));
			for (Tour tour : tours) {
				String photo = ActionUtil.getFirstPhoto(
						tour.getHotelId(),
						ActionUtil.getPhotosFolderPath(req));
				 
				toursWithPhotos.put(tour, photo);
			}
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ActionException(e);
		}
		String jsonPlaces = ActionUtil.PlacesToJson(places);

		req.setAttribute("tours", toursWithPhotos);
		req.setAttribute("places", jsonPlaces);
		
		return Path.INDEX_PAGE;
	}
}
