package ua.nure.miroshnichenko.touragency.db.dao.mysql;

import java.util.ArrayList;
import java.util.List;

import ua.nure.miroshnichenko.touragency.db.DBUtil;
import ua.nure.miroshnichenko.touragency.db.Queries;
import ua.nure.miroshnichenko.touragency.db.dao.DAOException;
import ua.nure.miroshnichenko.touragency.db.dao.ReservationDAO;
import ua.nure.miroshnichenko.touragency.db.entity.Reservation;
import ua.nure.miroshnichenko.touragency.db.entity.Tour;
import ua.nure.miroshnichenko.touragency.db.entity.User;
import ua.nure.miroshnichenko.myorm.core.transaction.Transaction;
import ua.nure.miroshnichenko.myorm.core.transaction.exception.TransactionException;
import ua.nure.miroshnichenko.myorm.core.transaction.exception.TransactionFactoryException;

public class MysqlReservationDAO implements ReservationDAO {

	private MysqlTourDAO tourDAO = new MysqlTourDAO();

	private MysqlUserDAO userDAO = new MysqlUserDAO();

	@Override
	public Reservation find(int id) throws DAOException {
		Transaction transaction = null;
		Reservation reservation = null;

		try {
			transaction = DBUtil.getTransaction();
			reservation = transaction.findByPK(Reservation.class, id);

			initReservation(reservation);

		} catch (TransactionFactoryException | TransactionException e) {
			e.printStackTrace();
			throw new DAOException(e);
		} finally {
			try {
				DBUtil.close(transaction);
			} catch (TransactionException e) {
				e.printStackTrace();
				throw new DAOException(e);
			}
		}
		return reservation;
	}

	@Override
	public List<Reservation> findAll() throws DAOException {
		Transaction transaction = null;
		List<Reservation> reservations = new ArrayList<>();

		try {
			transaction = DBUtil.getTransaction();
			reservations = transaction.findAll(Reservation.class);

			for (Reservation reservation : reservations) {
				initReservation(reservation);
			}

		} catch (TransactionFactoryException | TransactionException e) {
			e.printStackTrace();
			throw new DAOException(e);
		} finally {
			try {
				DBUtil.close(transaction);
			} catch (TransactionException e) {
				e.printStackTrace();
				throw new DAOException(e);
			}
		}
		return reservations;
	}

	@Override
	public boolean save(Reservation entity) throws DAOException {
		Transaction transaction = null;
		boolean result = false;

		try {
			transaction = DBUtil.getTransaction();
			result = transaction.insert(entity);
			transaction.commit();
		} catch (TransactionFactoryException | TransactionException e) {
			e.printStackTrace();
			throw new DAOException(e);
		} finally {
			try {
				DBUtil.close(transaction);
			} catch (TransactionException e) {
				e.printStackTrace();
				throw new DAOException(e);
			}
		}
		return result;
	}

	@Override
	public boolean update(Reservation entity) throws DAOException {
		Transaction transaction = null;
		boolean result = false;

		try {
			transaction = DBUtil.getTransaction();
			result = transaction.update(entity);
			transaction.commit();
		} catch (TransactionFactoryException | TransactionException e) {
			e.printStackTrace();
			throw new DAOException(e);
		} finally {
			try {
				DBUtil.close(transaction);
			} catch (TransactionException e) {
				e.printStackTrace();
				throw new DAOException(e);
			}
		}
		return result;
	}

	@Override
	public boolean delete(Reservation entity) throws DAOException {
		Transaction transaction = null;
		boolean result = false;

		try {
			transaction = DBUtil.getTransaction();
			result = transaction.delete(entity);
			transaction.commit();
		} catch (TransactionFactoryException | TransactionException e) {
			e.printStackTrace();
			throw new DAOException(e);
		} finally {
			try {
				DBUtil.close(transaction);
			} catch (TransactionException e) {
				e.printStackTrace();
				throw new DAOException(e);
			}
		}
		return result;
	}

	@Override
	public List<Reservation> getUserReservations(User user) throws DAOException {
		Transaction transaction = null;
		List<Reservation> reservations = new ArrayList<>();

		try {
			transaction = DBUtil.getTransaction();
			reservations = transaction.customQuery(
					Queries.USER_RESERVATIONS, Reservation.class, user.getId());

			for (Reservation reservation : reservations) {
				Tour tour = tourDAO.find(reservation.getTourId());

				reservation.setTour(tour);
				reservation.setUser(user);
			}

		} catch (TransactionFactoryException | TransactionException e) {
			e.printStackTrace();
			throw new DAOException(e);
		} finally {
			try {
				DBUtil.close(transaction);
			} catch (TransactionException e) {
				e.printStackTrace();
				throw new DAOException(e);
			}
		}
		return reservations;
	}

	@Override
	public List<Reservation> getTourReservations(Tour tour) throws DAOException {
		Transaction transaction = null;
		List<Reservation> reservations = new ArrayList<>();

		try {
			transaction = DBUtil.getTransaction();
			reservations = transaction.customQuery(
					Queries.TOUR_RESERVATIONS, Reservation.class, tour.getId().toString());

			for (Reservation reservation : reservations) {
				User user = userDAO.find(reservation.getUserId());

				reservation.setTour(tour);
				reservation.setUser(user);
			}

		} catch (TransactionFactoryException | TransactionException e) {
			e.printStackTrace();
			throw new DAOException(e);
		} finally {
			try {
				DBUtil.close(transaction);
			} catch (TransactionException e) {
				e.printStackTrace();
				throw new DAOException(e);
			}
		}
		return reservations;
	}
	
	@Override
	public boolean setDiscountStepForAllUsers(double discountStep) throws DAOException {
		Transaction transaction = null;
		boolean result = false;

		try {
			transaction = DBUtil.getTransaction();
			result = transaction.customUpdate(
					Queries.SET_DISCOUNT_STEP_FOR_ALL_USERS,
					discountStep);
			transaction.commit();
		} catch (TransactionFactoryException | TransactionException e) {
			e.printStackTrace();
			throw new DAOException(e);
		} finally {
			try {
				DBUtil.close(transaction);
			} catch (TransactionException e) {
				e.printStackTrace();
				throw new DAOException(e);
			}
		}
		return result;
	}
	
	private void initReservation(Reservation reservation) throws DAOException {
		Tour tour = tourDAO.find(reservation.getTourId());
		User user = userDAO.find(reservation.getUserId());

		reservation.setTour(tour);
		reservation.setUser(user);
	}
}
