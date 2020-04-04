package ua.nure.miroshnichenko.touragency.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import ua.nure.miroshnichenko.myorm.core.transaction.exception.TransactionException;
import ua.nure.miroshnichenko.myorm.core.transaction.exception.TransactionFactoryException;
import ua.nure.miroshnichenko.touragency.db.DBUtil;
import ua.nure.miroshnichenko.touragency.db.dao.DAOException;
import ua.nure.miroshnichenko.touragency.db.dao.DAOFactory;
import ua.nure.miroshnichenko.touragency.db.dao.ReservationDAO;
import ua.nure.miroshnichenko.touragency.db.dao.TourDAO;
import ua.nure.miroshnichenko.touragency.db.entity.Beach;
import ua.nure.miroshnichenko.touragency.db.entity.Facility;
import ua.nure.miroshnichenko.touragency.db.entity.Food;
import ua.nure.miroshnichenko.touragency.db.entity.HotelType;
import ua.nure.miroshnichenko.touragency.db.entity.Reservation;
import ua.nure.miroshnichenko.touragency.db.entity.ReservationStatus;
import ua.nure.miroshnichenko.touragency.db.entity.Servicing;
import ua.nure.miroshnichenko.touragency.db.entity.Tour;
import ua.nure.miroshnichenko.touragency.db.entity.TourType;
import ua.nure.miroshnichenko.touragency.db.entity.TransportType;
import ua.nure.miroshnichenko.touragency.db.entity.User;

class TourServiceImpl implements TourService {

	private DAOFactory factoryDAO = DAOFactory.getInstance();
	
	@Override
	public List<Tour> getAll() throws ServiceException {
		List<Tour> tours = new ArrayList<>();

		try {
			TourDAO tourDAO = factoryDAO.geTourDAO();
			tours = tourDAO.findAll();

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
			TourDAO tourDAO = factoryDAO.geTourDAO();
			tour = tourDAO.find(id);

			return tour;
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Tour> filter(Map<String, String[]> values) throws ServiceException {
		try {
			TourDAO dao = factoryDAO.geTourDAO();
			
			List<Servicing> servicings = Servicing.getServicings(values.get("servicing"));
			List<Facility> facilities = Facility.getFacilities(values.get("facility"));
			List<HotelType> hotelTypes = HotelType.getHotelTypes(values.get("hotelType"));
			List<Food> foods = Food.getFoods(values.get("food"));
			List<Beach> beaches = Beach.getBeaches(values.get("beach"));
			List<TourType> tourTypes = TourType.getTourTypes(values.get("tourType"));
			List<TransportType> transportTypes = TransportType.getTransportTypes(values.get("transportType"));
			
			String[] starsArr = values.get("stars");
			List<String> stars = new ArrayList<>();
			
			if(starsArr != null) {
				stars = Arrays.asList(starsArr);
			}
			
			List<Tour> tours = dao.filter(getMainParametrs(values), servicings, facilities,
					hotelTypes, foods, beaches, tourTypes, transportTypes, stars);
			
			return tours;
			
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean save(Tour tour) throws ServiceException {
		try {
			TourDAO tourDAO = factoryDAO.geTourDAO();
			boolean result = tourDAO.save(tour);

			return result;
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean update(Tour tour) throws ServiceException {
		try {
			TourDAO tourDAO = factoryDAO.geTourDAO();
			boolean result = tourDAO.update(tour);

			return result;
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean delete(Tour tour) throws ServiceException {
		try {
			TourDAO tourDAO = factoryDAO.geTourDAO();
			boolean result = tourDAO.delete(tour);

			return result;
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean reserve(int tourId, int userId, int peopleCount) throws ServiceException {
		try {
			return DBUtil.callProcedure(
					"reserve_tour",
					userId, tourId,
					peopleCount,
					ReservationStatus.RESERVED.ordinal());
		} catch (TransactionException | TransactionFactoryException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean revoke(int reservationId) throws ServiceException {
		try {
			ReservationDAO dao = factoryDAO.getReservationDAO();
			Reservation reservation = dao.find(reservationId);
			
			reservation.setStatus(ReservationStatus.REVOKED);
			
			boolean result = dao.update(reservation);

			return result;
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);

		}
	}

	@Override
	public List<Reservation> getAllReserved() throws ServiceException {
		try {
			ReservationDAO reservationDAO = factoryDAO.getReservationDAO();
			List<Reservation> reservations = reservationDAO.findAll();
			
			reservations.sort((x, y) -> 
						x.getResrveDate().compareTo(y.getResrveDate()));
			
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
	
	private Map<String, String> getMainParametrs(Map<String, String[]> values) {
		Map<String, String> parametrs = new HashMap<>();
		for(Entry<String, String[]> entry : values.entrySet()) {
			parametrs.put(entry.getKey(), entry.getValue()[0]);
		}
		return parametrs;
	}
}
