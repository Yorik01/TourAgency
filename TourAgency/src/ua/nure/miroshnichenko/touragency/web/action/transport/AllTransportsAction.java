package ua.nure.miroshnichenko.touragency.web.action.transport;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.miroshnichenko.touragency.db.entity.Transport;
import ua.nure.miroshnichenko.touragency.service.DiscountService;
import ua.nure.miroshnichenko.touragency.service.TourService;
import ua.nure.miroshnichenko.touragency.service.TransportService;
import ua.nure.miroshnichenko.touragency.service.exception.ServiceException;
import ua.nure.miroshnichenko.touragency.web.Path;
import ua.nure.miroshnichenko.touragency.web.action.Action;
import ua.nure.miroshnichenko.touragency.web.action.ActionException;
import ua.nure.miroshnichenko.touragency.web.action.ActionUtil;

public class AllTransportsAction extends Action {

	private static final long serialVersionUID = -68790491746311415L;

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException, ActionException {
		
		TransportService transportService = serviceFactory.getTransportService();
		
		String keyword = req.getParameter("keyword");
		List<Transport> transports;
		if (keyword != null && !keyword.isEmpty()) {
			try {
				transports = transportService.getTransportsByCode(keyword);
			} catch (ServiceException e) {
				e.printStackTrace();
				throw new ActionException(e);
			}
		} else {
			transports = ActionUtil.getAllEntities(transportService);
		}

		TourService tourService = serviceFactory.getTourService();
		DiscountService discountService = serviceFactory.getDiscountService();
		
		ActionUtil.setAllEntitiesJsonInAttribute(req, tourService, "toursJSON");
		ActionUtil.setDiscountStepInAttribute(discountService, req);
		
		req.setAttribute("transports", transports);
		req.setAttribute("form", Path.TRANSPORTS_LIST);
		
		return Path.ADMIN_PAGE;
	}
}