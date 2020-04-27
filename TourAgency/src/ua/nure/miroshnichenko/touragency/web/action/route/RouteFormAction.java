package ua.nure.miroshnichenko.touragency.web.action.route;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.miroshnichenko.touragency.db.entity.Place;
import ua.nure.miroshnichenko.touragency.db.entity.Route;
import ua.nure.miroshnichenko.touragency.service.DiscountService;
import ua.nure.miroshnichenko.touragency.service.RouteService;
import ua.nure.miroshnichenko.touragency.service.TourService;
import ua.nure.miroshnichenko.touragency.service.exception.ServiceException;
import ua.nure.miroshnichenko.touragency.web.Path;
import ua.nure.miroshnichenko.touragency.web.action.Action;
import ua.nure.miroshnichenko.touragency.web.action.ActionException;
import ua.nure.miroshnichenko.touragency.web.action.ActionUtil;

/**
 * The action to get the from to fill information about route.
 * 
 * @author Miroshnichenko Y. D.
 */
public class RouteFormAction extends Action {

	private static final long serialVersionUID = 2169010493985383491L;

	private final Logger LOG = Logger.getLogger(RouteFormAction.class);
	
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException, ActionException {

		RouteService routeService = serviceFactory.getRouteService();
		
		List<Place> places = null;
		try {
			places = routeService.getAllPlaces();
		} catch (ServiceException e) {
			LOG.error(e);
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
				LOG.error(e);
				throw new ActionException(e);
			}
			req.setAttribute("route", route);
		}

		TourService tourService = serviceFactory.getTourService();
		DiscountService discountService = serviceFactory.getDiscountService();
		
		ActionUtil.setAllEntitiesJsonInAttribute(req, tourService, "toursJSON");
		ActionUtil.setDiscountStepInAttribute(discountService, req);
		
		return Path.ADMIN_PAGE;
	}
}
