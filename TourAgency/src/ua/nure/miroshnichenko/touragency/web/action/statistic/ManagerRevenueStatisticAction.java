package ua.nure.miroshnichenko.touragency.web.action.statistic;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.miroshnichenko.touragency.db.statistic.ManagerRevenueStatistic;
import ua.nure.miroshnichenko.touragency.service.StatisticService;
import ua.nure.miroshnichenko.touragency.service.exception.ServiceException;
import ua.nure.miroshnichenko.touragency.web.Path;
import ua.nure.miroshnichenko.touragency.web.action.Action;
import ua.nure.miroshnichenko.touragency.web.action.ActionException;

/**
 * The action to get statistic about managers total revenues.
 * 
 * @author Miroshnichenko Y. D.
 */
public class ManagerRevenueStatisticAction extends Action {

	private static final long serialVersionUID = 3622392677618254043L;

	private final Logger LOG = Logger.getLogger(ManagerRevenueStatisticAction.class);
	
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException, ActionException {
		
		StatisticService statisticService = serviceFactory.getStatisticService();
		
		List<ManagerRevenueStatistic> managerRevenueStatistics;
		try {
			managerRevenueStatistics = statisticService.getManagerRevenueStatistics();
		} catch (ServiceException e) {
			LOG.error(e);
			throw new ActionException(e);
		}
		
		req.setAttribute("statistics", managerRevenueStatistics);
		req.setAttribute("form", Path.MANAGER_REVENUE_STATISTIC);
		
		return Path.ADMIN_PAGE;
	}
}
