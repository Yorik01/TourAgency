package ua.nure.miroshnichenko.touragency.service;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import ua.nure.miroshnichenko.touragency.db.entity.Reservation;
import ua.nure.miroshnichenko.touragency.db.entity.Tour;
import ua.nure.miroshnichenko.touragency.db.entity.User;
import ua.nure.miroshnichenko.touragency.service.exception.ServiceException;

/**
 * Service control information about tours.
 * 
 * @author Miroshnichenko Y. D.
 */
public interface TourService extends CRUDService<Tour> {
	
	/**
	 * Get a list of tours filtered by specific parametrs.
	 * 
	 * @param values all parametrs for filtering.
	 * 
	 * @return list of tours.
	 * 
	 * @throws ServiceException
	 */
	List<Tour> filter(Map<String, String[]> values) throws ServiceException;
	
	/**
	 * Get a information about tour by period and hotel.
	 * 
	 * @param startDate start date of tour.
	 * @param endDate end date of tour.
	 * @param hotelId id of a specific hotel.
	 * 
	 * @return information about tour.
	 * 
	 * @throws ServiceException
	 */
	Tour getTourByDateAndHotelId(Date startDate, Date endDate, int hotelId)throws ServiceException;
	
	/**
	 * Reserve a specific tour by a specific user.
	 * 
	 * @param tourId id of a specific tour.
	 * @param userId id of a specific user.
	 * @param peopleCount count of people in a reservation.
	 * 
	 * @return true if a new reservation has been created.
	 * 
	 * @throws ServiceException
	 */
	boolean reserve(int tourId, int userId, int peopleCount) throws ServiceException;

	/**
	 * Revoke a specific reservation.
	 * 
	 * @param reservationId id of a specific reservation.
	 * 
	 * @return true if a new reservation has been revoked.
	 * 
	 * @throws ServiceException
	 */
	boolean revoke(int reservationId) throws ServiceException;
	
	/**
	 * Pay a specific reservation by a specific manager.
	 * 
	 * @param reservationId id of a specific reservation.
	 * @param managerId id of a specific manager.
	 * 
	 * @return true if a new reservation has been created.
	 * 
	 * @throws ServiceException
	 */
	boolean pay(int reservationId, int managerId) throws ServiceException;

	/**
	 * Get all reservations.
	 * 
	 * @return a list of all reservations.
	 * 
	 * @throws ServiceException
	 */
	List<Reservation> getAllReserved() throws ServiceException;

	/**
	 * Get a list of reservations of a specific user.
	 * 
	 * @param userId id of user.
	 * 	
	 * @return list of reservations.
	 * 
	 * @throws ServiceException
	 */
	List<Reservation> getUserReservations(User user) throws ServiceException;
	
	/**
	 * Get a list of reservations of a specific tour.
	 * 
	 * @param tourId id of tour.
	 * 	
	 * @return list of reservations.
	 * 
	 * @throws ServiceException
	 */
	List<Reservation> getTourReservations(Tour tour) throws ServiceException;
	
	/**
	 * Set a status for tour.
	 * 
	 * @param tourId id of tour.
	 * @param status status of tour (0 - unfired, 1 -fired).
	 * 	
	 * @return true if status has been changed.
	 * 
	 * @throws ServiceException
	 */
	boolean setTourStatus(int tourId, int status) throws ServiceException;
}
