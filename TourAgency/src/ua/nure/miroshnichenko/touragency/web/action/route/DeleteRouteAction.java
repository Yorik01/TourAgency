package ua.nure.miroshnichenko.touragency.web.action.route;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.miroshnichenko.touragency.db.entity.Route;
import ua.nure.miroshnichenko.touragency.service.RouteService;
import ua.nure.miroshnichenko.touragency.service.exception.ServiceException;
import ua.nure.miroshnichenko.touragency.web.Path;
import ua.nure.miroshnichenko.touragency.web.action.Action;
import ua.nure.miroshnichenko.touragency.web.action.ActionException;

/**
 * The action to delete a specific route.
 * 
 * @author Miroshnichenko Y. D.
 */
public class DeleteRouteAction extends Action {
	
	private static final long serialVersionUID = 5009440022401551946L;

	private final Logger LOG = Logger.getLogger(DeleteRouteAction.class);
	
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException, ActionException {
		
		RouteService routeService = serviceFactory.getRouteService();
		
		int id = Integer.parseInt(req.getParameter("id"));
		
		Route route = new Route();
		route.setId(id);
		
		try {
			routeService.delete(route);
		} catch (ServiceException e) {
			LOG.error(e);
			throw new ActionException(e);
		}
		
		return "redirect:" + Path.getControllerPath("allRoutes");
	}
}
