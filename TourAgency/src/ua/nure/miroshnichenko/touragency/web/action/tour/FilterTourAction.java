package ua.nure.miroshnichenko.touragency.web.action.tour;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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

public class FilterTourAction extends Action {

	private static final long serialVersionUID = -3692502748918177449L;

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException, ActionException {

		TourService tourService = serviceFactory.getTourService();

		RouteService routeService = serviceFactory.getRouteService();

		List<Place> places = null;
		List<Tour> tours = null;
		Map<Tour, String> toursWithPhotos = null;

		Map<String, String[]> params = req.getParameterMap();
		
		try {
			places = routeService.getAllPlaces();
			tours = tourService.filter(params);
			
			toursWithPhotos = ActionUtil.getTourWithPhoto(tours, req);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ActionException(e);
		}
		
		String jsonPlaces = ActionUtil.PlacesToJson(places);

		Map<String, List<String>> filterParams = new HashMap<>();
		for (Entry<String, String[]> entry : params.entrySet()) {
			filterParams.put(entry.getKey(), Arrays.asList(entry.getValue()));
		}
		
		req.setAttribute("tours", toursWithPhotos);
		req.setAttribute("places", jsonPlaces);
		req.setAttribute("filterParams", filterParams);
		
		return Path.INDEX_PAGE;
	}
}
