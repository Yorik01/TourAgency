package ua.nure.miroshnichenko.summarytask4.db.dao;

import java.util.List;

import ua.nure.miroshnichenko.summarytask4.db.entity.Reservation;
import ua.nure.miroshnichenko.summarytask4.db.entity.Tour;
import ua.nure.miroshnichenko.summarytask4.db.entity.User;

public interface ReservationDAO extends DAO<Reservation> {

	List<Reservation> getUserReservations(User user) throws DAOException;

	List<Reservation> getTourReservations(Tour tour) throws DAOException;
}
