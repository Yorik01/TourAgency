package ua.nure.miroshnichenko.summarytask4.web.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.miroshnichenko.summarytask4.db.entity.Place;
import ua.nure.miroshnichenko.summarytask4.db.entity.Route;
import ua.nure.miroshnichenko.summarytask4.service.RouteService;
import ua.nure.miroshnichenko.summarytask4.service.ServiceException;
import ua.nure.miroshnichenko.summarytask4.web.Path;

public class AddRouteAction extends Action {

	private static final long serialVersionUID = -7635684849165292423L;

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException, ActionException {
		
		RouteService routeService = serviceFactory.getRouteService();
		
		String countryFrom = req.getParameter("countryFrom");
		String cityFrom = req.getParameter("cityFrom");
		
		String countryTo = req.getParameter("countryTo");
		String cityTo = req.getParameter("cityTo");

		Place fromPlace = null;
		Place toPlace = null;
		try {
			fromPlace = routeService.getPlaceByCountryAndCity(countryFrom, cityFrom);
			toPlace = routeService.getPlaceByCountryAndCity(countryTo, cityTo);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ActionException(e);
		}

		Route route = new Route();
		
		route.setRouteFromId(fromPlace.getId());
		route.setRouteToId(toPlace.getId());;
		
		try {
			routeService.save(route);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ActionException(e);
		}
		
		return Path.ADMIN_PAGE;
	}
}
