package ua.nure.miroshnichenko.touragency.service.impl;

import java.util.List;

import ua.nure.miroshnichenko.touragency.db.dao.DAOException;
import ua.nure.miroshnichenko.touragency.db.dao.DAOFactory;
import ua.nure.miroshnichenko.touragency.db.dao.StatisticDAO;
import ua.nure.miroshnichenko.touragency.db.statistic.AverageMarkTourStatistic;
import ua.nure.miroshnichenko.touragency.db.statistic.ManagerRevenueStatistic;
import ua.nure.miroshnichenko.touragency.db.statistic.TourReservationsStatistic;
import ua.nure.miroshnichenko.touragency.db.statistic.UserReservationsStatistic;
import ua.nure.miroshnichenko.touragency.service.StatisticService;
import ua.nure.miroshnichenko.touragency.service.exception.ServiceException;

class StatisticServiceImpl implements StatisticService {

	private DAOFactory factoryDAO = DAOFactory.getInstance();
	
	@Override
	public List<AverageMarkTourStatistic> getAverageMarkTourStatistics() throws ServiceException {
		try {
			StatisticDAO statisticDAO = factoryDAO.getStatisticDAO();
			return statisticDAO.getAverageMarkTourStatistics();
			
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	@Override
	public List<ManagerRevenueStatistic> getManagerRevenueStatistics() throws ServiceException {
		try {
			StatisticDAO statisticDAO = factoryDAO.getStatisticDAO();
			return statisticDAO.getManagerRevenueStatistics();
			
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	@Override
	public List<TourReservationsStatistic> geTourReservationsStatistics() throws ServiceException {
		try {
			StatisticDAO statisticDAO = factoryDAO.getStatisticDAO();
			return statisticDAO.geTourReservationsStatistics();
			
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	@Override
	public List<UserReservationsStatistic> getUserReservationsStatistics() throws ServiceException {
		try {
			StatisticDAO statisticDAO = factoryDAO.getStatisticDAO();
			return statisticDAO.getUserReservationsStatistics();
			
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}
}