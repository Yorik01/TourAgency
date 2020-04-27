package ua.nure.miroshnichenko.touragency.web.action.statistic;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.miroshnichenko.touragency.db.statistic.UserReservationsStatistic;
import ua.nure.miroshnichenko.touragency.service.StatisticService;
import ua.nure.miroshnichenko.touragency.service.exception.ServiceException;
import ua.nure.miroshnichenko.touragency.web.Path;
import ua.nure.miroshnichenko.touragency.web.action.Action;
import ua.nure.miroshnichenko.touragency.web.action.ActionException;

/**
 * The action to get statistic about counts of users reservations.
 * 
 * @author Miroshnichenko Y. D.
 */
public class UserReservationsStatisticAction extends Action {

	private static final long serialVersionUID = -7216143638735473241L;

	private final Logger LOG = Logger.getLogger(UserReservationsStatisticAction.class);
	
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException, ActionException {
		
		StatisticService statisticService = serviceFactory.getStatisticService();
		
		List<UserReservationsStatistic> userReservationsStatistics;
		try {
			userReservationsStatistics = statisticService.getUserReservationsStatistics();
		} catch (ServiceException e) {
			LOG.error(e);
			throw new ActionException(e);
		}
		
		req.setAttribute("statistics", userReservationsStatistics);
		req.setAttribute("form", Path.USER_RESERVAION_STATISTIC);
		
		return Path.ADMIN_PAGE;
	}
}