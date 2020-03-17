package ua.nure.miroshnichenko.summarytask4.db.dao;

import ua.nure.miroshnichenko.summarytask4.db.entity.Place;

public interface PlaceDAO extends DAO<Place> {

	Place getPlaceByCountryAndCity(String country, String city) throws DAOException;
}
