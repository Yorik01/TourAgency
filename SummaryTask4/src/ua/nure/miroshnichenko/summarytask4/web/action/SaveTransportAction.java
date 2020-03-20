package ua.nure.miroshnichenko.summarytask4.web.action;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		String takeoffTime = req.getParameter("takeoffTime");
		String arrivingTime = req.getParameter("arrivingTime");
		String[] takeoffDate = req.getParameter("takeoffDate").split("/");
		String[] arrivingDate = req.getParameter("arrivingDate").split("/");
		Integer maxPlaces = Integer.valueOf(req.getParameter("maxPlaces"));
		Double price = Double.valueOf(req.getParameter("price"));
		TransportType type = TransportType.valueOf(req.getParameter("type"));
		
		System.out.println(arrivingDate[2] + "-" + arrivingDate[1] + "-" + arrivingDate[0]
				+ " " + arrivingTime + ":00");
		
		Timestamp arriving = Timestamp.valueOf(
				arrivingDate[2] + "-" + arrivingDate[0] + "-" + arrivingDate[1]
				+ " " + arrivingTime + ":00");
		
		Timestamp takeoff = Timestamp.valueOf(
				takeoffDate[2] + "-" + takeoffDate[0] + "-" + takeoffDate[1]
				+ " " + takeoffTime + ":00");
		
		Transport transport = new Transport();
		
		transport.setCode(code);
		transport.setTakeoff(takeoff);
		transport.setArrive(arriving);
		transport.setMaxPlaces(maxPlaces);
		transport.setPrice(price);
		transport.setType(type);
		transport.setRouteId(Integer.valueOf(req.getParameter("routeId")));;
		
		try {
			transportService.save(transport);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ActionException(e);
		}
		
		return Path.ADMIN_PAGE;
	}
}
