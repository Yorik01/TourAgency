package ua.nure.miroshnichenko.summarytask4.web.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.miroshnichenko.summarytask4.db.entity.Tour;
import ua.nure.miroshnichenko.summarytask4.db.entity.User;
import ua.nure.miroshnichenko.summarytask4.service.DiscountService;
import ua.nure.miroshnichenko.summarytask4.service.ServiceException;
import ua.nure.miroshnichenko.summarytask4.service.TourService;
import ua.nure.miroshnichenko.summarytask4.web.Path;

public class ReserveTourAction extends Action {

	private static final long serialVersionUID = -2053383474777835419L;

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException, ActionException {

		HttpSession session = req.getSession();
		
		TourService tourService = serviceFactory.getTourService();
		DiscountService discountService = serviceFactory.getDiscountService();
		
		User user = (User) session.getAttribute("user");
		
		Integer tourId = Integer.parseInt(req.getParameter("id"));
		Integer peopleCount = Integer.parseInt(req.getParameter("peopleCount"));
		
		try {
			if (tourService.reserve(tourId, user.getId(), peopleCount)) {
				discountService.riseDiscount(user.getId(), tourId);
			}
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ActionException(e);
		}
		
		return Path.INDEX_PAGE;
	}
}
