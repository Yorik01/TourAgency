package ua.nure.miroshnichenko.touragency.db.dao.mysql;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.miroshnichenko.myorm.core.transaction.Transaction;
import ua.nure.miroshnichenko.myorm.core.transaction.exception.TransactionException;
import ua.nure.miroshnichenko.myorm.core.transaction.exception.TransactionFactoryException;
import ua.nure.miroshnichenko.touragency.db.DBUtil;
import ua.nure.miroshnichenko.touragency.db.Queries;
import ua.nure.miroshnichenko.touragency.db.dao.DAOException;
import ua.nure.miroshnichenko.touragency.db.dao.StatisticDAO;
import ua.nure.miroshnichenko.touragency.db.statistic.AverageMarkTourStatistic;
import ua.nure.miroshnichenko.touragency.db.statistic.ManagerRevenueStatistic;
import ua.nure.miroshnichenko.touragency.db.statistic.TourReservationsStatistic;
import ua.nure.miroshnichenko.touragency.db.statistic.UserReservationsStatistic;

public class MysqlStatisticDAO implements StatisticDAO {

	private final Logger LOG = Logger.getLogger(MysqlStatisticDAO.class);
	
	private static MysqlStatisticDAO instance;
	
	private MysqlStatisticDAO() {
	}
	
	public static synchronized MysqlStatisticDAO getInstance() {
		if (instance == null) {
			instance = new MysqlStatisticDAO();
		}
		return instance;
	}
	
	@Override
	public List<AverageMarkTourStatistic> getAverageMarkTourStatistics() throws DAOException {
		Transaction transaction = null;
		List<AverageMarkTourStatistic> averageMarkTourStatistics = new ArrayList<>();
		
		try {
			transaction = DBUtil.getTransaction();
			averageMarkTourStatistics = transaction.customQuery(
					Queries.AVERAGE_TOURS_MARKS_STATISTIC, AverageMarkTourStatistic.class);
		} catch (TransactionFactoryException | TransactionException e) {
			LOG.error(e);
			throw new DAOException(e);
		} finally {
			try {
				DBUtil.close(transaction);
			} catch (TransactionException e) {
				LOG.error(e);
				throw new DAOException(e);
			}
		}
		return averageMarkTourStatistics;
	}

	@Override
	public List<ManagerRevenueStatistic> getManagerRevenueStatistics() throws DAOException {
		Transaction transaction = null;
		List<ManagerRevenueStatistic> managerRevenueStatistics = new ArrayList<>();
		
		try {
			transaction = DBUtil.getTransaction();
			managerRevenueStatistics = transaction.customQuery(
					Queries.MANAGERS_REVENUES_STATISTIC, ManagerRevenueStatistic.class);
		} catch (TransactionFactoryException | TransactionException e) {
			LOG.error(e);
			throw new DAOException(e);
		} finally {
			try {
				DBUtil.close(transaction);
			} catch (TransactionException e) {
				LOG.error(e);
				throw new DAOException(e);
			}
		}
		return managerRevenueStatistics;
	}

	@Override
	public List<TourReservationsStatistic> geTourReservationsStatistics() throws DAOException {
		Transaction transaction = null;
		List<TourReservationsStatistic> tourReservationsStatistics = new ArrayList<>();
		
		try {
			transaction = DBUtil.getTransaction();
			tourReservationsStatistics = transaction.customQuery(
					Queries.TOURS_RESERVATIONS_STATISTIC, TourReservationsStatistic.class);
		} catch (TransactionFactoryException | TransactionException e) {
			LOG.error(e);
			throw new DAOException(e);
		} finally {
			try {
				DBUtil.close(transaction);
			} catch (TransactionException e) {
				LOG.error(e);
				throw new DAOException(e);
			}
		}
		return tourReservationsStatistics;
	}

	@Override
	public List<UserReservationsStatistic> getUserReservationsStatistics() throws DAOException {
		Transaction transaction = null;
		List<UserReservationsStatistic> userReservationsStatistics = new ArrayList<>();
		
		try {
			transaction = DBUtil.getTransaction();
			userReservationsStatistics = transaction.customQuery(
					Queries.USER_RESERVATIONS_STATISTIC, UserReservationsStatistic.class);
		} catch (TransactionFactoryException | TransactionException e) {
			LOG.error(e);
			throw new DAOException(e);
		} finally {
			try {
				DBUtil.close(transaction);
			} catch (TransactionException e) {
				LOG.error(e);
				throw new DAOException(e);
			}
		}
		return userReservationsStatistics;
	}
}
