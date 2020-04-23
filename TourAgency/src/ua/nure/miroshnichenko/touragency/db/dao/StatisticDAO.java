package ua.nure.miroshnichenko.touragency.db.dao;

import java.util.List;

import ua.nure.miroshnichenko.touragency.db.statistic.AverageMarkTourStatistic;
import ua.nure.miroshnichenko.touragency.db.statistic.ManagerRevenueStatistic;
import ua.nure.miroshnichenko.touragency.db.statistic.TourReservationsStatistic;
import ua.nure.miroshnichenko.touragency.db.statistic.UserReservationsStatistic;

public interface StatisticDAO {

	List<AverageMarkTourStatistic> getAverageMarkTourStatistics() throws DAOException;
	
	List<ManagerRevenueStatistic> getManagerRevenueStatistics() throws DAOException;

	List<TourReservationsStatistic> geTourReservationsStatistics() throws DAOException;
	
	List<UserReservationsStatistic> getUserReservationsStatistics() throws DAOException;
}
