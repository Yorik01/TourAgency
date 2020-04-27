package ua.nure.miroshnichenko.touragency.web.action.statistic;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.miroshnichenko.touragency.db.statistic.TourReservationsStatistic;
import ua.nure.miroshnichenko.touragency.service.StatisticService;
import ua.nure.miroshnichenko.touragency.service.exception.ServiceException;
import ua.nure.miroshnichenko.touragency.web.Path;
import ua.nure.miroshnichenko.touragency.web.action.Action;
import ua.nure.miroshnichenko.touragency.web.action.ActionException;

/**
 * The action to get statistic about counts of tours reservations.
 * 
 * @author Miroshnichenko Y. D.
 */
public class TourReservationsStatisticAction extends Action {

	private static final long serialVersionUID = -2370422910640687748L;

	private final Logger LOG = Logger.getLogger(TourReservationsStatisticAction.class);
	
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException, ActionException {
		
		StatisticService statisticService = serviceFactory.getStatisticService();
		
		List<TourReservationsStatistic> tourReservationsStatistics;
		try {
			tourReservationsStatistics = statisticService.geTourReservationsStatistics();
		} catch (ServiceException e) {
			LOG.error(e);
			throw new ActionException(e);
		}
		
		req.setAttribute("statistics", tourReservationsStatistics);
		req.setAttribute("form", Path.TOUR_RESERVAION_STATISTIC);
		
		return Path.ADMIN_PAGE;
	}
}
