package ua.nure.miroshnichenko.touragency.web.action.route;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.miroshnichenko.touragency.db.entity.Place;
import ua.nure.miroshnichenko.touragency.db.entity.Route;
import ua.nure.miroshnichenko.touragency.service.RouteService;
import ua.nure.miroshnichenko.touragency.service.exception.ServiceException;
import ua.nure.miroshnichenko.touragency.web.Path;
import ua.nure.miroshnichenko.touragency.web.action.Action;
import ua.nure.miroshnichenko.touragency.web.action.ActionException;

/**
 * The action to save a route.
 * 
 * @author Miroshnichenko Y. D.
 */
public class SaveRouteAction extends Action {

	private static final long serialVersionUID = -7635684849165292423L;

	private final Logger LOG = Logger.getLogger(SaveRouteAction.class);
	
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException, ActionException {
		
		RouteService routeService = serviceFactory.getRouteService();
		
		Route route = initRoute(req, routeService);

		if(Boolean.parseBoolean(req.getParameter("edit"))) {
			int id = Integer.parseInt(req.getParameter("id"));
			
			route.setId(id);
			try {
				routeService.update(route);
			} catch (ServiceException e) {
				LOG.error(e);
				throw new ActionException(e);
			}

			return "redirect:" + Path.getControllerPath("allRoutes");
		}
		
		try {
			routeService.save(route);
		} catch (ServiceException e) {
			LOG.error(e);
			throw new ActionException(e);
		}
		
		return "redirect:" + Path.getControllerPath("allRoutes");
	}
	
	private Route initRoute(HttpServletRequest req, RouteService routeService) throws ActionException {
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
			LOG.error(e);
			throw new ActionException(e);
		}
		Route route = new Route();
		
		route.setRouteFromId(fromPlace.getId());
		route.setRouteToId(toPlace.getId());
		route.setFrom(fromPlace);
		route.setTo(toPlace);
		
		return route;
	}
}
