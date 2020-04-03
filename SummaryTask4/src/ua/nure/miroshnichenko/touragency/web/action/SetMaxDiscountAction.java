package ua.nure.miroshnichenko.touragency.web.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.miroshnichenko.touragency.service.DiscountService;
import ua.nure.miroshnichenko.touragency.service.ServiceException;
import ua.nure.miroshnichenko.touragency.web.Path;

public class SetMaxDiscountAction extends Action {
	
	private static final long serialVersionUID = 705696500222209047L;

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException, ActionException {
		
		DiscountService discountService = serviceFactory.getDiscountService();
		
		Integer tourId = Integer.parseInt(req.getParameter("tourId"));
		Double maxDiscount = Double.parseDouble(req.getParameter("maxDiscount"));
		
		try {
			discountService.setMaxDiscount(tourId, maxDiscount);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ActionException(e);
		}
		
		return Path.NO_PATH;
	}
}