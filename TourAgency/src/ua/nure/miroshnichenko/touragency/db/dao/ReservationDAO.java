package ua.nure.miroshnichenko.touragency.db.dao;

import java.util.List;

import ua.nure.miroshnichenko.touragency.db.entity.Reservation;
import ua.nure.miroshnichenko.touragency.db.entity.Tour;
import ua.nure.miroshnichenko.touragency.db.entity.User;

public interface ReservationDAO extends DAO<Reservation> {

	List<Reservation> getUserReservations(User user) throws DAOException;

	List<Reservation> getTourReservations(Tour tour) throws DAOException;
	
	boolean setDiscountStepForAllUsers(double discountStep) throws DAOException;
}
