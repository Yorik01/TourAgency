package ua.nure.miroshnichenko.touragency.service.impl;

import org.apache.log4j.Logger;

import ua.nure.miroshnichenko.touragency.db.dao.DAOException;
import ua.nure.miroshnichenko.touragency.db.dao.DAOFactory;
import ua.nure.miroshnichenko.touragency.db.dao.ReservationDAO;
import ua.nure.miroshnichenko.touragency.db.dao.TourDAO;
import ua.nure.miroshnichenko.touragency.db.dao.UserDAO;
import ua.nure.miroshnichenko.touragency.db.entity.Tour;
import ua.nure.miroshnichenko.touragency.service.DiscountService;
import ua.nure.miroshnichenko.touragency.service.exception.ServiceException;
import ua.nure.miroshnichenko.touragency.service.impl.constants.ExceptionMessages;
import ua.nure.miroshnichenko.touragency.service.impl.constants.LogginMessages;

class DiscountServiceImpl implements DiscountService {

	private final Logger LOG = Logger.getLogger(DiscountServiceImpl.class);
	
	private DAOFactory factoryDAO = DAOFactory.getInstance();
	
	private static DiscountServiceImpl instance;
	
	private DiscountServiceImpl () {
		factoryDAO = DAOFactory.getInstance();
	}
	
	public static synchronized DiscountServiceImpl getInstance() {
		if(instance == null) {
			instance = new DiscountServiceImpl();
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
	public boolean setMaxDiscount(int tourId, double value) throws ServiceException {
		try {
			TourDAO tourDao = factoryDAO.geTourDAO();
			Tour tour = tourDao.find(tourId);

			if (tour != null) {
				tour.setMaxDiscount(value);
			}
			
			boolean res = tourDao.update(tour);
			
			if (res) {
				LOG.trace(String.format(
						LogginMessages.MAX_DISCOUNT_CHANGED,
						value));
			}
			
			return res;
		} catch (DAOException e) {
			LOG.error(e);
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean setDiscountStep(double step) throws ServiceException {
		try {
			ReservationDAO reservationDAO = factoryDAO.getReservationDAO();
			boolean res = reservationDAO.setDiscountStepForAllUsers(step);
			
			if (res) {
				LOG.trace(String.format(
						LogginMessages.DISCOUNT_STEP_CHANGED,
						step));
			}
			
			return res;
		} catch (DAOException e) {
			LOG.error(e);
			throw new ServiceException(e);
		}
	}
	
	@Override
	public double getDiscountStep() throws ServiceException {
		try {
			UserDAO userDAO = factoryDAO.getUserDAO();
			Double discountStep = userDAO.getDiscountStep();
			
			if (discountStep == null) {
				throw new ServiceException(ExceptionMessages.ERROR_DISCOUNT_STEP_SETTING);
			}
			
			return discountStep;
		} catch (DAOException e) {
			LOG.error(e);
			throw new ServiceException(e);
		}
	}
}
