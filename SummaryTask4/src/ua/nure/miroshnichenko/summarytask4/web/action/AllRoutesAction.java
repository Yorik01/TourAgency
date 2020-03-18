package ua.nure.miroshnichenko.summarytask4.web.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.miroshnichenko.summarytask4.db.entity.Route;
import ua.nure.miroshnichenko.summarytask4.service.RouteService;
import ua.nure.miroshnichenko.summarytask4.service.ServiceException;
import ua.nure.miroshnichenko.summarytask4.web.Path;

public class AllRoutesAction extends Action {

	private static final long serialVersionUID = -6981977979437519739L;

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException, ActionException {
		
		RouteService routeService = serviceFactory.getRouteService();
		
		List<Route> routes = null;
		
		try {
			routes = routeService.getAll();
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ActionException(e);
		}
		req.setAttribute("routes", routes);
		req.setAttribute("form", Path.ROUTES_LIST);
		
		return Path.ADMIN_PAGE;
	}
}
