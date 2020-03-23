package ua.nure.miroshnichenko.summarytask4.web.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.miroshnichenko.summarytask4.db.entity.Place;
import ua.nure.miroshnichenko.summarytask4.db.entity.Tour;
import ua.nure.miroshnichenko.summarytask4.service.RouteService;
import ua.nure.miroshnichenko.summarytask4.service.ServiceException;
import ua.nure.miroshnichenko.summarytask4.service.TourService;
import ua.nure.miroshnichenko.summarytask4.web.Path;

public class FilterToursFormAction extends Action {

	private static final long serialVersionUID = 142736073248551283L;

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException, ActionException {
		
		TourService tourService = serviceFactory.getTourService();

		RouteService routeService = serviceFactory.getRouteService();
		
		List<Place> places = null;
		List<Tour> tours = null;

		try {
			places = routeService.getAllPlaces();
			tours = tourService.getAll();
			
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