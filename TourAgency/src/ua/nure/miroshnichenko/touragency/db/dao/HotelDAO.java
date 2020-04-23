package ua.nure.miroshnichenko.touragency.db.dao;

import ua.nure.miroshnichenko.touragency.db.entity.Hotel;

public interface HotelDAO extends DAO<Hotel> {

	Hotel getHotelByName(String name) throws DAOException;
}