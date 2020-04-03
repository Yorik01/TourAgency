package ua.nure.miroshnichenko.touragency.web.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.miroshnichenko.touragency.db.entity.User;
import ua.nure.miroshnichenko.touragency.service.ServiceException;
import ua.nure.miroshnichenko.touragency.service.TourService;
import ua.nure.miroshnichenko.touragency.web.Path;

public class ReserveTourAction extends Action {

	private static final long serialVersionUID = -2053383474777835419L;

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException, ActionException {

		HttpSession session = req.getSession();
		
		TourService tourService = serviceFactory.getTourService();
		
		User user = (User) session.getAttribute("user");
		
		Integer tourId = Integer.parseInt(req.getParameter("id"));
		Integer peopleCount = Integer.parseInt(req.getParameter("peopleCount"));
		
		try {
			tourService.reserve(tourId, user.getId(), peopleCount);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ActionException(e);
		}
		
		return Path.INDEX_PAGE;
	}
}