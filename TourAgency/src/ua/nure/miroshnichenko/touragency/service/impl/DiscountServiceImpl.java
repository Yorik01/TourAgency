package ua.nure.miroshnichenko.touragency.service.impl;

import ua.nure.miroshnichenko.touragency.db.dao.DAOException;
import ua.nure.miroshnichenko.touragency.db.dao.DAOFactory;
import ua.nure.miroshnichenko.touragency.db.dao.ReservationDAO;
import ua.nure.miroshnichenko.touragency.db.dao.TourDAO;
import ua.nure.miroshnichenko.touragency.db.entity.Tour;
import ua.nure.miroshnichenko.touragency.service.DiscountService;
import ua.nure.miroshnichenko.touragency.service.exception.ServiceException;

class DiscountServiceImpl implements DiscountService {

	private DAOFactory factoryDAO = DAOFactory.getInstance();

	@Override
	public boolean setMaxDiscount(int tourId, double value) throws ServiceException {
		try {
			TourDAO tourDao = factoryDAO.geTourDAO();
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
	public boolean setDiscountStep(double step) throws ServiceException {
		try {
			ReservationDAO reservationDAO = factoryDAO.getReservationDAO();
			return reservationDAO.setDiscountStepForAllUsers(step);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}
}
