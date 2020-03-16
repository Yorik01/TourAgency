package ua.nure.miroshnichenko.summarytask4.db.dao;

import ua.nure.miroshnichenko.summarytask4.db.entity.Hotel;

public interface HotelDAO extends DAO<Hotel> {

	Hotel getHotelByName(String name) throws DAOException;
}