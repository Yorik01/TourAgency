package ua.nure.miroshnichenko.touragency.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

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

	private Logger LOG = Logger.getLogger(StatisticServiceImpl.class);
	
	private DAOFactory factoryDAO = DAOFactory.getInstance();
	
	private static StatisticServiceImpl instance;
	
	private StatisticServiceImpl () {
		factoryDAO = DAOFactory.getInstance();
	}
	
	public static synchronized StatisticServiceImpl getInstance() {
		if(instance == null) {
			instance = new StatisticServiceImpl();
		}
		return instance;
	}
	
	/**
	 * Set the DAOFactory implementation using the setter.
	 * It is used for mock test.
	 */
	public void setFactoryDAO(DAOFactory factoryDAO) {
		this.factoryDAO = factoryDAO;
	}
	
	@Override
	public List<AverageMarkTourStatistic> getAverageMarkTourStatistics() throws ServiceException {
		try {
			StatisticDAO statisticDAO = factoryDAO.getStatisticDAO();
			return statisticDAO.getAverageMarkTourStatistics();
			
		} catch (DAOException e) {
			LOG.error(e);
			throw new ServiceException(e);
		}
	}

	@Override
	public List<ManagerRevenueStatistic> getManagerRevenueStatistics() throws ServiceException {
		try {
			StatisticDAO statisticDAO = factoryDAO.getStatisticDAO();
			return statisticDAO.getManagerRevenueStatistics();
			
		} catch (DAOException e) {
			LOG.error(e);
			throw new ServiceException(e);
		}
	}

	@Override
	public List<TourReservationsStatistic> geTourReservationsStatistics() throws ServiceException {
		try {
			StatisticDAO statisticDAO = factoryDAO.getStatisticDAO();
			return statisticDAO.geTourReservationsStatistics();
			
		} catch (DAOException e) {
			LOG.error(e);
			throw new ServiceException(e);
		}
	}

	@Override
	public List<UserReservationsStatistic> getUserReservationsStatistics() throws ServiceException {
		try {
			StatisticDAO statisticDAO = factoryDAO.getStatisticDAO();
			return statisticDAO.getUserReservationsStatistics();
			
		} catch (DAOException e) {
			LOG.error(e);
			throw new ServiceException(e);
		}
	}
}