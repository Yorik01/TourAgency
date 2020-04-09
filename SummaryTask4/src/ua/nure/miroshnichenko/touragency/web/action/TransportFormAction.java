package ua.nure.miroshnichenko.touragency.web.action;

import java.io.IOException;
import java.sql.Time;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

	import ua.nure.miroshnichenko.touragency.db.entity.Route;
import ua.nure.miroshnichenko.touragency.db.entity.Transport;
import ua.nure.miroshnichenko.touragency.service.RouteService;
import ua.nure.miroshnichenko.touragency.service.ServiceException;
import ua.nure.miroshnichenko.touragency.service.TourService;
import ua.nure.miroshnichenko.touragency.service.TransportService;
import ua.nure.miroshnichenko.touragency.web.Path;

public class TransportFormAction extends Action {

	private static final long serialVersionUID = -3690857473192818168L;

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException, ActionException {
		
		RouteService routeService = serviceFactory.getRouteService();
		TransportService transportService = serviceFactory.getTransportService();
		
		List<Route> routes = null;
		try {
			routes = routeService.getAll();
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ActionException(e);
		}
		
		req.setAttribute("form", Path.TRANSPORT_FORM);
		req.setAttribute("routes", routes);
		
		if(Boolean.parseBoolean(req.getParameter("edit"))) {
			int id = Integer.parseInt(req.getParameter("id"));
			Transport transport;
			
			try {
				transport = transportService.get(id);
			} catch (ServiceException e) {
				e.printStackTrace();
				throw new ActionException(e);
			}
			long takeOffTime = transport.getTakeoff().getTime();
			long arriveTime = transport.getArrive().getTime();
			
			req.setAttribute("transport", transport);
			
			req.setAttribute("takeoffTime", new Time(takeOffTime));
			req.setAttribute("takeoffDate", new Date(takeOffTime));
			req.setAttribute("arriveTime", new Time(arriveTime));
			req.setAttribute("arriveDate", new Date(arriveTime));
		}
		
		TourService tourService = serviceFactory.getTourService();
		ActionUtil.setAllEntitiesJsonInAttribute(req, tourService, "toursJSON");
		
		return Path.ADMIN_PAGE;
	}
}
