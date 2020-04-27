package ua.nure.miroshnichenko.touragency.web.action.transport;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.miroshnichenko.touragency.db.entity.Transport;
import ua.nure.miroshnichenko.touragency.db.entity.TransportType;
import ua.nure.miroshnichenko.touragency.service.TransportService;
import ua.nure.miroshnichenko.touragency.service.exception.ServiceException;
import ua.nure.miroshnichenko.touragency.web.Path;
import ua.nure.miroshnichenko.touragency.web.action.Action;
import ua.nure.miroshnichenko.touragency.web.action.ActionException;

/**
 * The action to save a transport.
 * 
 * @author Miroshnichenko Y. D.
 */
public class SaveTransportAction extends Action {

	private static final long serialVersionUID = -8768622351135882140L;

	private final Logger LOG = Logger.getLogger(SaveTransportAction.class);
	
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException, ActionException {

		TransportService transportService = serviceFactory.getTransportService();
		
		Transport transport = initTour(req);
		
		if(Boolean.parseBoolean(req.getParameter("edit"))) {
			Integer id = Integer.parseInt(req.getParameter("id"));
			
			transport.setId(id);
			try {
				transportService.update(transport);
			} catch (ServiceException e) {
				LOG.error(e);
				throw new ActionException(e);
			}

			return "redirect:" + Path.getControllerPath("allTransports");
		}
		
		try {
			transportService.save(transport);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ActionException(e);
		}
		
		return "redirect:" + Path.getControllerPath("allTransports");
	}
	
	private Transport initTour(HttpServletRequest req) {
		String code = req.getParameter("code");
		String takeoffTime = req.getParameter("takeoffTime");
		String arrivingTime = req.getParameter("arrivingTime");
		String takeoffDate = req.getParameter("takeoffDate");
		String arrivingDate = req.getParameter("arrivingDate");
		Integer maxPlaces = Integer.valueOf(req.getParameter("maxPlaces"));
		double price = Double.valueOf(req.getParameter("price"));
		TransportType type = TransportType.valueOf(req.getParameter("type"));
		
		Transport transport = new Transport();
		
		String takeoffStr =  takeoffDate + " " + takeoffTime;
		String arriveStr = arrivingDate + " " + arrivingTime;
		
		if (!takeoffStr.matches(".+:\\d\\d:\\d\\d")) {
			takeoffStr += ":00";
		}
		
		if (!arriveStr.matches(".+:\\d\\d:\\d\\d")) {
			arriveStr += ":00";
		}
		
		transport.setCode(code);
		transport.setTakeoff(Timestamp.valueOf(takeoffStr));
		transport.setArrive(Timestamp.valueOf(arriveStr));
		transport.setMaxPlaces(maxPlaces);
		transport.setPrice(price);
		transport.setType(type);
		transport.setRouteId(Integer.valueOf(req.getParameter("routeId")));
		
		return transport;
	}
}
