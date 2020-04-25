package ua.nure.miroshnichenko.touragency.web.action.tour;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.miroshnichenko.touragency.db.entity.Place;
import ua.nure.miroshnichenko.touragency.db.entity.Tour;
import ua.nure.miroshnichenko.touragency.service.RouteService;
import ua.nure.miroshnichenko.touragency.service.TourService;
import ua.nure.miroshnichenko.touragency.service.exception.ServiceException;
import ua.nure.miroshnichenko.touragency.web.Path;
import ua.nure.miroshnichenko.touragency.web.action.Action;
import ua.nure.miroshnichenko.touragency.web.action.ActionException;
import ua.nure.miroshnichenko.touragency.web.action.ActionUtil;

public class FilterToursFormAction extends Action {

	private static final long serialVersionUID = 142736073248551283L;

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException, ActionException {
		
		TourService tourService = serviceFactory.getTourService();

		RouteService routeService = serviceFactory.getRouteService();
		
		List<Tour> tours = ActionUtil.getAllEntities(tourService);

		List<Place> places = null;
		try {
			places = routeService.getAllPlaces();
			tours = tourService.getAll();
			ActionUtil.setToursPhotos(tours, req);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ActionException(e);
		}
		String jsonPlaces = ActionUtil.PlacesToJson(places);

		req.setAttribute("tours", tours);
		req.setAttribute("places", jsonPlaces);
		
		return Path.INDEX_PAGE;
	}
}
