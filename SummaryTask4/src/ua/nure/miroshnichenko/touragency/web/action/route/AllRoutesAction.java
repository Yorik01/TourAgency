package ua.nure.miroshnichenko.touragency.web.action.route;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.miroshnichenko.touragency.db.entity.Route;
import ua.nure.miroshnichenko.touragency.service.RouteService;
import ua.nure.miroshnichenko.touragency.service.TourService;
import ua.nure.miroshnichenko.touragency.web.Path;
import ua.nure.miroshnichenko.touragency.web.action.Action;
import ua.nure.miroshnichenko.touragency.web.action.ActionException;
import ua.nure.miroshnichenko.touragency.web.action.ActionUtil;

public class AllRoutesAction extends Action {

	private static final long serialVersionUID = -6981977979437519739L;

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException, ActionException {
		
		RouteService routeService = serviceFactory.getRouteService();
		
		List<Route> routes = ActionUtil.getAllEntities(routeService);
		
		TourService tourService = serviceFactory.getTourService();
		ActionUtil.setAllEntitiesJsonInAttribute(req, tourService, "toursJSON");
		
		req.setAttribute("routes", routes);
		req.setAttribute("form", Path.ROUTES_LIST);
		
		return Path.ADMIN_PAGE;
	}
}
