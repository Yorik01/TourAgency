package ua.nure.miroshnichenko.summarytask4.service;

import java.util.List;
import java.util.Map;

import ua.nure.miroshnichenko.summarytask4.db.entity.Reservation;
import ua.nure.miroshnichenko.summarytask4.db.entity.Tour;
import ua.nure.miroshnichenko.summarytask4.db.entity.User;

public interface TourService extends CRUDService<Tour> {

	List<Tour> filter(Map<String, String[]> values) throws ServiceException;
	
	boolean reserve(Tour tour, User user, int peopleCount) throws ServiceException;

	boolean revoke(Reservation reservation) throws ServiceException;

	List<Reservation> getAllReserved() throws ServiceException;

	List<Reservation> getUserReservations(User user) throws ServiceException;
	
	List<Reservation> getTourReservations(Tour tour) throws ServiceException;
}
