package ua.nure.miroshnichenko.summarytask4.web.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import ua.nure.miroshnichenko.summarytask4.db.entity.Place;
import ua.nure.miroshnichenko.summarytask4.db.entity.Route;
import ua.nure.miroshnichenko.summarytask4.service.RouteService;
import ua.nure.miroshnichenko.summarytask4.service.ServiceException;
import ua.nure.miroshnichenko.summarytask4.web.Path;

public class RouteFormAction extends Action {

	private static final long serialVersionUID = 2169010493985383491L;

	private Gson gson = new Gson();
	
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
		
		Map<String, List<String>> mapPlaces = new HashMap<>();
		for(Place place : places) {
			String country = place.getCountry();
			String city = place.getCity();
			
			if(mapPlaces.containsKey(country)) {
				List<String> cities = mapPlaces.get(country);
				cities.add(city);
			} else {
				List<String> cities = new ArrayList<>();
				cities.add(city);
				
				mapPlaces.put(country, cities);
			}
		}
		
		String jsonPlaces = gson.toJson(mapPlaces);
		
		req.setAttribute("form", Path.ROUTE_FORM);
		req.setAttribute("places", jsonPlaces);
		req.setAttribute("op", "add");
		
		if(Boolean.parseBoolean(req.getParameter("edit"))) {
			Integer id = Integer.parseInt(req.getParameter("id"));
			Route route;
			
			try {
				route = routeService.get(id);
			} catch (ServiceException e) {
				e.printStackTrace();
				throw new ActionException(e);
			}
			req.setAttribute("route", route);
			req.setAttribute("op", "edit");
		}
		
		return Path.ADMIN_PAGE;
	}
}
