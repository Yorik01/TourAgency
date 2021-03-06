package ua.nure.miroshnichenko.touragency.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

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
import ua.nure.miroshnichenko.touragency.service.TourService;
import ua.nure.miroshnichenko.touragency.service.exception.ServiceException;
import ua.nure.miroshnichenko.touragency.service.impl.constants.ExceptionMessages;
import ua.nure.miroshnichenko.touragency.service.impl.constants.LogginMessages;

class TourServiceImpl implements TourService {

	private final Logger LOG = Logger.getLogger(TourServiceImpl.class);
	
	private DAOFactory factoryDAO = DAOFactory.getInstance();
	
	private static TourServiceImpl instance;
	
	private TourServiceImpl () {
		factoryDAO = DAOFactory.getInstance();
	}
	
	public static synchronized TourServiceImpl getInstance() {
		if(instance == null) {
			instance = new TourServiceImpl();
		}
		return instance;
	}
	
	private static final String reserveTourProcedureName = "reserve_tour";
	
	/**
	 * Set the DAOFactory implementation using the setter.
	 * It is used for mock test.
	 */
	public void setFactoryDAO(DAOFactory factoryDAO) {
		this.factoryDAO = factoryDAO;
	}
	
	@Override
	public List<Tour> getAll() throws ServiceException {
		List<Tour> tours = new ArrayList<>();

		try {
			TourDAO tourDAO = factoryDAO.geTourDAO();
			tours = tourDAO.findAll();

			return tours;
		} catch (DAOException e) {
			LOG.error(e);
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
			LOG.error(e);
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
			LOG.error(e);
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean save(Tour tour) throws ServiceException {
		try {
			TourDAO tourDAO = factoryDAO.geTourDAO();
			if (isTourUnique(tour)) {
				boolean res = tourDAO.save(tour);
				
				if (res) {
					LOG.trace(LogginMessages.TOUR_CREATED);
				}
				return res;
			}
			
			throw new ServiceException(ExceptionMessages.TOUR_EXIST);
		} catch (DAOException e) {
			LOG.error(e);
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean update(Tour tour) throws ServiceException {
		try {
			TourDAO tourDAO = factoryDAO.geTourDAO();
			
			Tour oldTour = getTourByDateAndHotelId(
					tour.getStartDate(),
					tour.getEndDate(),
					tour.getHotelId());
			
			if (oldTour == null || oldTour.getId() == tour.getId()) {
				boolean res = tourDAO.update(tour);
				
				if (res) {
					LOG.trace(String.format(
							LogginMessages.TOUR_UPDATE, tour.getId()));
				}
				return res;
			}
			
			throw new ServiceException(ExceptionMessages.TOUR_EXIST);
		} catch (DAOException e) {
			LOG.error(e);
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean delete(Tour tour) throws ServiceException {
		try {
			TourDAO tourDAO = factoryDAO.geTourDAO();
			boolean res = tourDAO.delete(tour);
			
			if (res) {
				LOG.trace(String.format(
						LogginMessages.HOTEL_DELETE, tour.getId()));
			}

			return res;
		} catch (DAOException e) {
			LOG.error(e);
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean reserve(int tourId, int userId, int peopleCount) throws ServiceException {
		try {
			boolean res = DBUtil.callProcedure(
					reserveTourProcedureName,
					userId, tourId,
					peopleCount,
					ReservationStatus.RESERVED.ordinal() + 1);
			
			if (res) {
				LOG.trace(String.format(
						LogginMessages.TOUR_RESERVED, tourId, userId));
			}
			
			return res;
		} catch (TransactionException | TransactionFactoryException e) {
			LOG.error(e);
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean revoke(int reservationId) throws ServiceException {
		boolean res = setReservationStatus(reservationId, ReservationStatus.REVOKED, null);
		
		if (res) {
			LOG.trace(String.format(
					LogginMessages.RESERVETION_REVOKED, reservationId));
		}
		
		return res;
	}
	
	@Override
	public boolean pay(int reservationId, int managerId) throws ServiceException {
		boolean res = setReservationStatus(reservationId, ReservationStatus.BOUGHT, managerId);
		
		if (res) {
			LOG.trace(String.format(
					LogginMessages.RESERVETION_PAYED, reservationId, managerId));
		}
		
		return res;
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
			LOG.error(e);
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
			LOG.error(e);
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
			LOG.error(e);
			throw new ServiceException(e);
		}
	}
	
	@Override
	public boolean setTourStatus(int tourId, int status) throws ServiceException {
		try {
			TourDAO tourDAO = factoryDAO.geTourDAO();
			
			Tour tour = tourDAO.find(tourId);
			
			if (status < 0 || status > 1) {
				throw new ServiceException(ExceptionMessages.TOUR_STATUS_OUT_OF_RANGE);
			}
			
			if (tour == null) {
				throw new ServiceException(ExceptionMessages.TOUR_NOT_EXIST);
			}
			
			tour.setFired(status);
			return tourDAO.update(tour);
		
		} catch (DAOException e) {
			LOG.error(e);
			throw new ServiceException(e);
		}
	}
	
	@Override
	public Tour getTourByDateAndHotelId(Date startDate, Date endDate, int hotelId) throws ServiceException {
		Tour tour = null;

		try {
			TourDAO tourDAO = factoryDAO.geTourDAO();
			tour = tourDAO.getTourByDateAndHotelId(startDate, endDate, hotelId);

			return tour;
		} catch (DAOException e) {
			LOG.error(e);
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
	
	private boolean isTourUnique(Tour tour) throws ServiceException {
		return getTourByDateAndHotelId(
				tour.getStartDate(),
				tour.getEndDate(),
				tour.getHotelId()) == null;
	}
	
	private boolean setReservationStatus(int reservationId, ReservationStatus status, Integer managerId) throws ServiceException{
		try {
			ReservationDAO dao = factoryDAO.getReservationDAO();
			Reservation reservation = dao.find(reservationId);
			
			if (reservation != null) {
				reservation.setStatus(status);
				reservation.setManagerId(managerId);
			
				return dao.update(reservation);
			}
			
			throw new ServiceException("");
		} catch (DAOException e) {
			LOG.error(e);
			throw new ServiceException(e);
		}
	}
}