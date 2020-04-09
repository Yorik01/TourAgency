package ua.nure.miroshnichenko.touragency.web.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.miroshnichenko.touragency.db.entity.Place;
import ua.nure.miroshnichenko.touragency.db.entity.Route;
import ua.nure.miroshnichenko.touragency.service.RouteService;
import ua.nure.miroshnichenko.touragency.service.ServiceException;
import ua.nure.miroshnichenko.touragency.service.TourService;
import ua.nure.miroshnichenko.touragency.web.Path;

public class RouteFormAction extends Action {

	private static final long serialVersionUID = 2169010493985383491L;

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException, ActionException {

		RouteService routeService = serviceFactory.getRouteService();
		
		List<Place> places = null;
		try {
			places = routeService.getAllPlaces();
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ActionException(e);
		}
		
		String jsonPlaces = ActionUtil.PlacesToJson(places);
		
		req.setAttribute("form", Path.ROUTE_FORM);
		req.setAttribute("places", jsonPlaces);
		
		if(Boolean.parseBoolean(req.getParameter("edit"))) {
			int id = Integer.parseInt(req.getParameter("id"));
			Route route;
			
			try {
				route = routeService.get(id);
			} catch (ServiceException e) {
				e.printStackTrace();
				throw new ActionException(e);
			}
			req.setAttribute("route", route);
		}
		
		TourService tourService = serviceFactory.getTourService();
		ActionUtil.setAllEntitiesJsonInAttribute(req, tourService, "toursJSON");
		
		return Path.ADMIN_PAGE;
	}
}
