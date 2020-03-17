package ua.nure.miroshnichenko.summarytask4.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import ua.nure.miroshnichenko.summarytask4.db.dao.DAO;
import ua.nure.miroshnichenko.summarytask4.db.dao.DAOException;
import ua.nure.miroshnichenko.summarytask4.db.dao.DAOFactory;
import ua.nure.miroshnichenko.summarytask4.db.dao.FacilityDAO;
import ua.nure.miroshnichenko.summarytask4.db.dao.ReservationDAO;
import ua.nure.miroshnichenko.summarytask4.db.dao.ServicingDAO;
import ua.nure.miroshnichenko.summarytask4.db.dao.TourDAO;
import ua.nure.miroshnichenko.summarytask4.db.entity.Beach;
import ua.nure.miroshnichenko.summarytask4.db.entity.Facility;
import ua.nure.miroshnichenko.summarytask4.db.entity.Food;
import ua.nure.miroshnichenko.summarytask4.db.entity.HotelType;
import ua.nure.miroshnichenko.summarytask4.db.entity.Reservation;
import ua.nure.miroshnichenko.summarytask4.db.entity.Servicing;
import ua.nure.miroshnichenko.summarytask4.db.entity.Tour;
import ua.nure.miroshnichenko.summarytask4.db.entity.TourType;
import ua.nure.miroshnichenko.summarytask4.db.entity.TransportType;
import ua.nure.miroshnichenko.summarytask4.db.entity.User;

class TourServiceImpl implements TourService {

	private DAOFactory factoryDAO = DAOFactory.getInstance();

	private List<Servicing> list;
	
	private static TourService instance;
	
	public static synchronized TourService getInstance() {
		if(instance == null) {
			instance = new TourServiceImpl();
		}
		return instance;
	}

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
	public List<Tour> filter(Map<String, String[]> values) throws ServiceException {
		try {
			TourDAO dao = factoryDAO.geTourDAO();
			
			double maxPrices = Double.valueOf(values.get("maxPrices")[0]);
			
			List<Servicing> servicings = Servicing.getServicings(values.get("servicings"));
			List<Facility> facilities = Facility.getFacilities(values.get("facilities"));
			List<HotelType> hotelTypes = HotelType.getHotelTypes(values.get("hotelTypes"));
			List<Food> foods = Food.getFoods(values.get("foods"));
			List<Beach> beaches = Beach.getBeaches(values.get("beaches"));
			List<TourType> tourTypes = TourType.getTourTypes(values.get("tourTypes"));
			List<TransportType> transportTypes = TransportType.getTransportTypes(values.get("transportTypes"));
							
			List<Tour> tours = dao.filter(getMainParametrs(values), servicings, facilities,
					hotelTypes, foods, beaches, tourTypes, transportTypes);
			
			tours = tours
					.stream()
					.filter(element -> element.getPrice() <= maxPrices)
					.collect(Collectors.toList());
			
			return tours;
			
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean save(Tour tour) throws ServiceException {
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
	
	private <T> List<T> filterValuesWithMap(Map<String, String> values, List<T> allValues) {
		return allValues.stream()
				.filter(element -> values.containsKey(element.toString()))
				.collect(Collectors.toList());
	}
	
	private Map<String, String> getMainParametrs(Map<String, String[]> values) {
		Map<String, String> parametrs = new HashMap<>();
		for(Entry<String, String[]> entry : values.entrySet()) {
			parametrs.put(entry.getKey(), entry.getValue()[0]);
		}
		return parametrs;
	}
}
