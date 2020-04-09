package ua.nure.miroshnichenko.touragency.web.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.miroshnichenko.touragency.service.TourService;
import ua.nure.miroshnichenko.touragency.web.Path;

public class AdminPageAction extends Action {

	private static final long serialVersionUID = 1034429626091756877L;
	
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException, ActionException {
	
		TourService tourService = serviceFactory.getTourService();
		ActionUtil.setAllEntitiesJsonInAttribute(req, tourService, "toursJSON");
		
		return Path.ADMIN_PAGE;
	}
}
