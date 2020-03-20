package ua.nure.miroshnichenko.summarytask4.web.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.miroshnichenko.summarytask4.db.entity.Tour;
import ua.nure.miroshnichenko.summarytask4.service.ServiceException;
import ua.nure.miroshnichenko.summarytask4.service.TourService;
import ua.nure.miroshnichenko.summarytask4.web.Path;

public class FilterTourAction extends Action {

	private static final long serialVersionUID = -3692502748918177449L;

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException, ActionException {
		
		TourService tourService = serviceFactory.getTourService();

		List<Tour> tours = null;
		try {
			tours = tourService.filter(req.getParameterMap());
			tours.sort((x, y) -> x.isFired().compareTo(y.isFired()));
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ActionException(e);
		}
		req.setAttribute("tours", tours);
		
		return Path.INDEX_PAGE;
	}
}
