package ua.nure.miroshnichenko.summarytask4.service;

import java.util.List;
import java.util.Map;

import ua.nure.miroshnichenko.summarytask4.db.entity.Reservation;
import ua.nure.miroshnichenko.summarytask4.db.entity.Servicing;
import ua.nure.miroshnichenko.summarytask4.db.entity.Tour;
import ua.nure.miroshnichenko.summarytask4.db.entity.User;

public interface TourService {

	List<Tour> getAll() throws ServiceException;

	Tour get(int id) throws ServiceException;

	List<Tour> filter(Map<String, String> values, List<Servicing> servicings, double maxPrice) throws ServiceException;
	
	boolean add(Tour tour) throws ServiceException;

	boolean update(Tour tour) throws ServiceException;

	boolean delete(Tour tour) throws ServiceException;

	boolean reserve(Tour tour, User user, byte peopleCount) throws ServiceException;

	boolean revoke(Reservation reservation) throws ServiceException;

	List<Reservation> getAllReserved() throws ServiceException;

	List<Reservation> getUserReservations(User user) throws ServiceException;
	
	List<Reservation> getTourReservations(Tour tour) throws ServiceException;
}
