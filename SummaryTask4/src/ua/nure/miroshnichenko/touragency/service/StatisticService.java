package ua.nure.miroshnichenko.touragency.service;

import java.util.List;

import ua.nure.miroshnichenko.touragency.db.statistic.AverageMarkTourStatistic;
import ua.nure.miroshnichenko.touragency.db.statistic.ManagerRevenueStatistic;
import ua.nure.miroshnichenko.touragency.db.statistic.TourReservationsStatistic;
import ua.nure.miroshnichenko.touragency.db.statistic.UserReservationsStatistic;
import ua.nure.miroshnichenko.touragency.service.exception.ServiceException;

public interface StatisticService {
	
	List<AverageMarkTourStatistic> getAverageMarkTourStatistics() throws ServiceException;
	
	List<ManagerRevenueStatistic> getManagerRevenueStatistics() throws ServiceException;

	List<TourReservationsStatistic> geTourReservationsStatistics() throws ServiceException;
	
	List<UserReservationsStatistic> getUserReservationsStatistics() throws ServiceException;
}
