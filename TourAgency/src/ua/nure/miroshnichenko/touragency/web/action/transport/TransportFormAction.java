package ua.nure.miroshnichenko.touragency.web.action.transport;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.miroshnichenko.touragency.db.entity.Route;
import ua.nure.miroshnichenko.touragency.db.entity.Transport;
import ua.nure.miroshnichenko.touragency.service.DiscountService;
import ua.nure.miroshnichenko.touragency.service.RouteService;
import ua.nure.miroshnichenko.touragency.service.TourService;
import ua.nure.miroshnichenko.touragency.service.TransportService;
import ua.nure.miroshnichenko.touragency.service.exception.ServiceException;
import ua.nure.miroshnichenko.touragency.web.Path;
import ua.nure.miroshnichenko.touragency.web.action.Action;
import ua.nure.miroshnichenko.touragency.web.action.ActionException;
import ua.nure.miroshnichenko.touragency.web.action.ActionUtil;

/**
 * The action to get a form to fill information about
 * a specific transport.
 * 
 * @author Miroshnichenko Y. D.
 */
public class TransportFormAction extends Action {

	private static final long serialVersionUID = -3690857473192818168L;

	private final Logger LOG = Logger.getLogger(TransportFormAction.class);
	
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
				LOG.error(e);
				throw new ActionException(e);
			}
			
			String[] takeOffArr = transport.getTakeoff().toString().split("\\s");
			String[] arriveArr = transport.getArrive().toString().split("\\s");

			req.setAttribute("transport", transport);
			
			req.setAttribute("takeoffTime", takeOffArr[1]);
			req.setAttribute("takeoffDate", takeOffArr[0]);
			req.setAttribute("arriveTime", arriveArr[1]);
			req.setAttribute("arriveDate", arriveArr[0]);
		}
		
		TourService tourService = serviceFactory.getTourService();
		DiscountService discountService = serviceFactory.getDiscountService();
		
		ActionUtil.setAllEntitiesJsonInAttribute(req, tourService, "toursJSON");
		ActionUtil.setDiscountStepInAttribute(discountService, req);
		
		return Path.ADMIN_PAGE;
	}
}
