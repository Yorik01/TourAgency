package ua.nure.miroshnichenko.touragency.web.action.statistic;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.miroshnichenko.touragency.db.statistic.AverageMarkTourStatistic;
import ua.nure.miroshnichenko.touragency.service.StatisticService;
import ua.nure.miroshnichenko.touragency.service.exception.ServiceException;
import ua.nure.miroshnichenko.touragency.web.Path;
import ua.nure.miroshnichenko.touragency.web.action.Action;
import ua.nure.miroshnichenko.touragency.web.action.ActionException;

/**
 * The action to get statistic about average marks of tours.
 * 
 * @author Miroshnichenko Y. D.
 */
public class AverageMarkTourStatisticAction extends Action {

	private static final long serialVersionUID = 577306661596836834L;

	private final Logger LOG = Logger.getLogger(AverageMarkTourStatisticAction.class);
	
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException, ActionException {
		
		StatisticService statisticService = serviceFactory.getStatisticService();
		
		List<AverageMarkTourStatistic> averageMarkTourStatistics;
		try {
			averageMarkTourStatistics = statisticService.getAverageMarkTourStatistics();
		} catch (ServiceException e) {
			LOG.error(e);
			throw new ActionException(e);
		}
		
		req.setAttribute("statistics", averageMarkTourStatistics);
		req.setAttribute("form", Path.AVERAGE_TOUR_MARK_STATISTIC);
		
		return Path.ADMIN_PAGE;
	}
}
