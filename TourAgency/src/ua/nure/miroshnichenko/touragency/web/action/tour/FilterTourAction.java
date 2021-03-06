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

import org.apache.log4j.Logger;

import ua.nure.miroshnichenko.touragency.db.entity.Place;
import ua.nure.miroshnichenko.touragency.db.entity.Tour;
import ua.nure.miroshnichenko.touragency.service.RouteService;
import ua.nure.miroshnichenko.touragency.service.TourService;
import ua.nure.miroshnichenko.touragency.service.exception.ServiceException;
import ua.nure.miroshnichenko.touragency.web.Path;
import ua.nure.miroshnichenko.touragency.web.action.Action;
import ua.nure.miroshnichenko.touragency.web.action.ActionException;
import ua.nure.miroshnichenko.touragency.web.action.ActionUtil;

/**
 * The action to filter all tours.
 * 
 * @author Miroshnichenko Y. D.
 */
public class FilterTourAction extends Action {

	private static final long serialVersionUID = -3692502748918177449L;

	private final Logger LOG = Logger.getLogger(FilterTourAction.class);
	
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException, ActionException {

		TourService tourService = serviceFactory.getTourService();

		RouteService routeService = serviceFactory.getRouteService();

		List<Place> places = null;
		List<Tour> tours = null;

		Map<String, String[]> params = req.getParameterMap();
		
		try {
			places = routeService.getAllPlaces();
			tours = tourService.filter(params);
			ActionUtil.setToursPhotos(tours, req);
		} catch (ServiceException e) {
			LOG.error(e);
			throw new ActionException(e);
		}
		
		String jsonPlaces = ActionUtil.PlacesToJson(places);

		Map<String, List<String>> filterParams = new HashMap<>();
		for (Entry<String, String[]> entry : params.entrySet()) {
			filterParams.put(entry.getKey(), Arrays.asList(entry.getValue()));
		}
		
		req.setAttribute("tours", tours);
		req.setAttribute("places", jsonPlaces);
		req.setAttribute("filterParams", filterParams);
		
		return Path.INDEX_PAGE;
	}
}
