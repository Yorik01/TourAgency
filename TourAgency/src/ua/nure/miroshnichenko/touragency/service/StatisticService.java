package ua.nure.miroshnichenko.touragency.service;

import java.util.List;

import ua.nure.miroshnichenko.touragency.db.statistic.AverageMarkTourStatistic;
import ua.nure.miroshnichenko.touragency.db.statistic.ManagerRevenueStatistic;
import ua.nure.miroshnichenko.touragency.db.statistic.TourReservationsStatistic;
import ua.nure.miroshnichenko.touragency.db.statistic.UserReservationsStatistic;
import ua.nure.miroshnichenko.touragency.service.exception.ServiceException;

/**
 * Service allows to get statistics.
 * 
 * @author Miroshnichenko Y. D.
 */
public interface StatisticService {
	
	/**
	 * Get a statistic about average marks of tours.
	 * 
	 * @return statistic about average marks of tours.
	 * 
	 * @throws ServiceException
	 */
	List<AverageMarkTourStatistic> getAverageMarkTourStatistics() throws ServiceException;

	/**
	 * Get a statistic about revenues of managers.
	 * 
	 * @return statistic about revenues of managers.
	 * 
	 * @throws ServiceException
	 */
	List<ManagerRevenueStatistic> getManagerRevenueStatistics() throws ServiceException;

	/**
	 * Get a statistic about counts of tours reservations.
	 * 
	 * @return statistic about counts of tours reservations.
	 * 
	 * @throws ServiceException
	 */
	List<TourReservationsStatistic> geTourReservationsStatistics() throws ServiceException;
	
	/**
	 * Get a statistic about counts of users reservations.
	 * 
	 * @return statistic about counts of users reservations.
	 * 
	 * @throws ServiceException
	 */
	List<UserReservationsStatistic> getUserReservationsStatistics() throws ServiceException;
}
