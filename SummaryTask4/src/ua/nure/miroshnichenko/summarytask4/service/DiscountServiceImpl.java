package ua.nure.miroshnichenko.summarytask4.service;

import ua.nure.miroshnichenko.summarytask4.db.DBUtil;
import ua.nure.miroshnichenko.summarytask4.db.dao.DAO;
import ua.nure.miroshnichenko.summarytask4.db.dao.DAOException;
import ua.nure.miroshnichenko.summarytask4.db.dao.DAOFactory;
import ua.nure.miroshnichenko.summarytask4.db.entity.Tour;
import ua.nure.miroshnichenko.summarytask4.myorm.core.DBConnectionException;
import ua.nure.miroshnichenko.summarytask4.myorm.core.transaction.exception.TransactionException;
import ua.nure.miroshnichenko.summarytask4.myorm.core.transaction.exception.TransactionFactoryException;

class DiscountServiceImpl implements DiscountService {

	private DAOFactory factoryDAO = DAOFactory.getInstance();

	private static DiscountServiceImpl instance;

	public static synchronized DiscountServiceImpl getInstance() {
		if (instance == null) {
			instance = new DiscountServiceImpl();
		}
		return instance;
	}

	@Override
	public boolean setMaxDiscount(int tourId, double value) throws ServiceException {
		try {
			DAO<Tour> tourDao = factoryDAO.geTourDAO();
			Tour tour = tourDao.find(tourId);

			if (tour != null) {
				tour.setMaxDiscount(value);
			}
			boolean result = tourDao.update(tour);

			return result;
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean riseDiscount(int userId, int tourId) throws ServiceException {
		try {
			return DBUtil.callProcedure("riseDiscount", userId, tourId);
		} catch (TransactionFactoryException | TransactionException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}
}
