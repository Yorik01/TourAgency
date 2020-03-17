package ua.nure.miroshnichenko.summarytask4.web.action;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.miroshnichenko.summarytask4.db.entity.Route;
import ua.nure.miroshnichenko.summarytask4.db.entity.Transport;
import ua.nure.miroshnichenko.summarytask4.db.entity.TransportType;
import ua.nure.miroshnichenko.summarytask4.service.RouteService;
import ua.nure.miroshnichenko.summarytask4.service.ServiceException;
import ua.nure.miroshnichenko.summarytask4.service.TransportService;
import ua.nure.miroshnichenko.summarytask4.web.Path;

public class AddTransportAction extends Action {

	private static final long serialVersionUID = -8768622351135882140L;

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException, ActionException {

		RouteService routeService = serviceFactory.getRouteService();
		TransportService transportService = serviceFactory.getTransportService();
		
		String code = req.getParameter("code");
		Date takeoffTime = Date.valueOf(req.getParameter("takeoffTime"));
		Date arrivingTime = Date.valueOf(req.getParameter("arrivingTime"));
		Integer maxPlaces = Integer.valueOf(req.getParameter("maxPlaces"));
		Double price = Double.valueOf(req.getParameter("price"));
		TransportType type = TransportType.valueOf(req.getParameter("type"));
		
		Route route = null;
		try {
			route = routeService.get(Integer.valueOf(req.getParameter("routeId")));
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ActionException(e);
		}
		
		Transport transport = new Transport();
		
		transport.setCode(code);
		transport.setTakeoff(takeoffTime);
		transport.setArrive(arrivingTime);
		transport.setMaxPlaces(maxPlaces);
		transport.setPrice(price);
		transport.setType(type);
		transport.setRoute(route);
		
		try {
			transportService.save(transport);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ActionException(e);
		}
		
		return Path.ADMIN_PAGE;
	}
}
