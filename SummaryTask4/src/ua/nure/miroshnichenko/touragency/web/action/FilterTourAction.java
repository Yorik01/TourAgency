package ua.nure.miroshnichenko.touragency.web.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.miroshnichenko.touragency.db.entity.Place;
import ua.nure.miroshnichenko.touragency.db.entity.Tour;
import ua.nure.miroshnichenko.touragency.service.RouteService;
import ua.nure.miroshnichenko.touragency.service.ServiceException;
import ua.nure.miroshnichenko.touragency.service.TourService;
import ua.nure.miroshnichenko.touragency.web.Path;

public class FilterTourAction extends Action {

	private static final long serialVersionUID = -3692502748918177449L;

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException, ActionException {

		TourService tourService = serviceFactory.getTourService();

		RouteService routeService = serviceFactory.getRouteService();

		List<Place> places = null;
		List<Tour> tours = null;
		try {
			places = routeService.getAllPlaces();
			tours = tourService.filter(req.getParameterMap());
			
			tours.sort((x, y) -> x.isFired().compareTo(y.isFired()));
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
