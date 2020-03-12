package ua.nure.miroshnichenko.summarytask4.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ua.nure.miroshnichenko.summarytask4.db.dao.DAO;
import ua.nure.miroshnichenko.summarytask4.db.dao.DAOException;
import ua.nure.miroshnichenko.summarytask4.db.dao.DAOFactory;
import ua.nure.miroshnichenko.summarytask4.db.dao.ReservationDAO;
import ua.nure.miroshnichenko.summarytask4.db.entity.Reservation;
import ua.nure.miroshnichenko.summarytask4.db.entity.Tour;
import ua.nure.miroshnichenko.summarytask4.db.entity.User;

public class TourServiceImpl implements TourService {

	private DAOFactory factoryDAO = DAOFactory.getInstance();

	@Override
	public List<Tour> getAll() throws ServiceException {
		List<Tour> tours = new ArrayList<>();

		try {
			DAO<Tour> dao = (DAO<Tour>) factoryDAO.getDAO("tour");
			tours = dao.findAll();

			return tours;
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	@Override
	public Tour get(int id) throws ServiceException {
		Tour tour = null;

		try {
			DAO<Tour> dao = (DAO<Tour>) factoryDAO.getDAO("tour");
			tour = (Tour) dao.find(id);

			return tour;
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}
	
	@Override
	public List<Tour> filter(Tour tour) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(Tour tour) throws ServiceException {
		try {
			DAO<Tour> dao = (DAO<Tour>) factoryDAO.getDAO("tour");
			boolean result = dao.save(tour);

			return result;
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean update(Tour tour) throws ServiceException {
		try {
			DAO<Tour> dao = (DAO<Tour>) factoryDAO.getDAO("tour");
			boolean result = dao.update(tour);

			return result;
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean delete(Tour tour) throws ServiceException {
		try {
			DAO<Tour> dao = (DAO<Tour>) factoryDAO.getDAO("tour");
			boolean result = dao.delete(tour);

			return result;
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean reserve(Tour tour, User user, byte peopleCount) throws ServiceException {
		Reservation reservation = new Reservation();

		reservation.setTourId(tour.getId());
		reservation.setUserId(user.getId());
		reservation.setPeopleCount(peopleCount);
		reservation.setResrveDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));

		try {
			DAO<Reservation> dao = (DAO<Reservation>) factoryDAO.getDAO("reservation");
			boolean result = dao.save(reservation);

			return result;
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);

		}
	}

	@Override
	public boolean revoke(Reservation reservation) throws ServiceException {
		try {
			DAO<Reservation> dao = (DAO<Reservation>) factoryDAO.getDAO("reservation");
			boolean result = dao.delete(reservation);

			return result;
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);

		}
	}

	@Override
	public List<Reservation> getAllReserved() throws ServiceException {
		try {
			DAO<Reservation> dao = (DAO<Reservation>) factoryDAO.getDAO("reservation");
			List<Reservation> reservations = dao.findAll();

			return reservations;
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);

		}
	}

	@Override
	public List<Reservation> getUserReservations(User user) throws ServiceException {
		try {
			ReservationDAO reservationDAO = factoryDAO.getReservationDAO();
			List<Reservation> reservations = reservationDAO.getUserReservations(user);

			return reservations;
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Reservation> getTourReservations(Tour tour) throws ServiceException {
		try {
			ReservationDAO reservationDAO = factoryDAO.getReservationDAO();
			List<Reservation> reservations = reservationDAO.getTourReservations(tour);

			return reservations;
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}
}
