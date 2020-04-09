package ua.nure.miroshnichenko.touragency.web.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.miroshnichenko.touragency.db.entity.Tour;
import ua.nure.miroshnichenko.touragency.service.TourService;
import ua.nure.miroshnichenko.touragency.web.Path;

public class AllToursAction extends Action {

	private static final long serialVersionUID = -5757678081770546971L;

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException, ActionException {
		
		TourService tourService = serviceFactory.getTourService();
		List<Tour> tours = ActionUtil.getAllEntities(tourService);

		ActionUtil.setAllEntitiesJsonInAttribute(req, tourService, "toursJSON");
		
		req.setAttribute("tours", tours);
		req.setAttribute("form", Path.TOURS_LIST);
		
		return Path.ADMIN_PAGE;
	}
}
