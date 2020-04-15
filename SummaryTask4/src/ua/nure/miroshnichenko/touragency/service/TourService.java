package ua.nure.miroshnichenko.touragency.service;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import ua.nure.miroshnichenko.touragency.db.entity.Reservation;
import ua.nure.miroshnichenko.touragency.db.entity.Tour;
import ua.nure.miroshnichenko.touragency.db.entity.User;
import ua.nure.miroshnichenko.touragency.service.exception.ServiceException;

public interface TourService extends CRUDService<Tour> {

	List<Tour> filter(Map<String, String[]> values) throws ServiceException;
	
	Tour getTourByDateAndHotelId(Date startDate, Date endDate, int hotelId)throws ServiceException;
	
	boolean reserve(int tourId, int userId, int peopleCount) throws ServiceException;

	boolean revoke(int reservationId) throws ServiceException;

	List<Reservation> getAllReserved() throws ServiceException;

	List<Reservation> getUserReservations(User user) throws ServiceException;
	
	List<Reservation> getTourReservations(Tour tour) throws ServiceException;
	
	boolean setTourStatus(int tourId, int status) throws ServiceException;
}
