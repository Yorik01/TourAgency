package ua.nure.miroshnichenko.touragency.web.action.discount;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.miroshnichenko.touragency.service.DiscountService;
import ua.nure.miroshnichenko.touragency.service.exception.ServiceException;
import ua.nure.miroshnichenko.touragency.web.Path;
import ua.nure.miroshnichenko.touragency.web.action.Action;
import ua.nure.miroshnichenko.touragency.web.action.ActionException;

public class SetDiscountStepForAllUsers extends Action {

	private static final long serialVersionUID = 4082425319824211455L;

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException, ActionException {
		
		DiscountService discountService = serviceFactory.getDiscountService();
		
		double step = Double.parseDouble(req.getParameter("discountStep"));
		
		try {
			discountService.setDiscountStep(step);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ActionException(e);
		}
		
		return Path.NO_PATH;
	}
}
