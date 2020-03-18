package ua.nure.miroshnichenko.summarytask4.web.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.miroshnichenko.summarytask4.db.entity.Route;
import ua.nure.miroshnichenko.summarytask4.service.RouteService;
import ua.nure.miroshnichenko.summarytask4.service.ServiceException;
import ua.nure.miroshnichenko.summarytask4.web.Path;

public class DeleteRouteAction extends Action {

	private static final long serialVersionUID = 5009440022401551946L;

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException, ActionException {
		
		RouteService routeService = serviceFactory.getRouteService();
		
		Integer id = Integer.parseInt(req.getParameter("id"));
		
		Route route = new Route();
		route.setId(id);
		
		try {
			routeService.delete(route);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ActionException(e);
		}
		
		return Path.ROUTES_LIST;
	}
}
