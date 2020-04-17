package ua.nure.miroshnichenko.touragency.web.action.route;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.miroshnichenko.touragency.db.entity.Place;
import ua.nure.miroshnichenko.touragency.db.entity.Route;
import ua.nure.miroshnichenko.touragency.service.RouteService;
import ua.nure.miroshnichenko.touragency.service.exception.ServiceException;
import ua.nure.miroshnichenko.touragency.web.Path;
import ua.nure.miroshnichenko.touragency.web.action.Action;
import ua.nure.miroshnichenko.touragency.web.action.ActionException;

public class SaveRouteAction extends Action {

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
		route.setRouteToId(toPlace.getId());
		route.setFrom(fromPlace);
		route.setTo(toPlace);

		if(Boolean.parseBoolean(req.getParameter("edit"))) {
			int id = Integer.parseInt(req.getParameter("id"));
			
			route.setId(id);
			try {
				routeService.update(route);
			} catch (ServiceException e) {
				e.printStackTrace();
				throw new ActionException(e);
			}
			res.sendRedirect(Path.getControllerPath("allRoutes"));
			
			return Path.NO_PATH;
		}
		
		try {
			routeService.save(route);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ActionException(e);
		}
		
		res.sendRedirect(Path.getControllerPath("allRoutes"));
		
		return Path.NO_PATH;
	}
}
