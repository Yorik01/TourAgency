package ua.nure.miroshnichenko.touragency.web.action.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.miroshnichenko.touragency.db.entity.User;
import ua.nure.miroshnichenko.touragency.service.AuthentificationService;
import ua.nure.miroshnichenko.touragency.service.DiscountService;
import ua.nure.miroshnichenko.touragency.service.TourService;
import ua.nure.miroshnichenko.touragency.service.exception.ServiceException;
import ua.nure.miroshnichenko.touragency.web.Path;
import ua.nure.miroshnichenko.touragency.web.action.Action;
import ua.nure.miroshnichenko.touragency.web.action.ActionException;
import ua.nure.miroshnichenko.touragency.web.action.ActionUtil;

/**
 * The action to get all users.
 * 
 * @author Miroshnichenko Y. D.
 */
public class AllUsersAction extends Action {

	private static final long serialVersionUID = 1944187012230686145L;

	private final Logger LOG = Logger.getLogger(AllUsersAction.class);
	
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException, ActionException {

		AuthentificationService authentificationService = 
				serviceFactory.getAuthentificationService();
		
		List<User> users;
		try {
			users = authentificationService.getAllUsers();
		} catch (ServiceException e) {
			LOG.error(e);
			throw new ActionException(e);
		}
		
		TourService tourService = serviceFactory.getTourService();
		DiscountService discountService = serviceFactory.getDiscountService();
		
		ActionUtil.setAllEntitiesJsonInAttribute(req, tourService, "toursJSON");
		ActionUtil.setDiscountStepInAttribute(discountService, req);
		
		req.setAttribute("users", users);
		req.setAttribute("form", Path.USERS_LIST);
		
		return Path.ADMIN_PAGE;
	}
}
