package ua.nure.miroshnichenko.touragency.db.dao;

import ua.nure.miroshnichenko.touragency.db.entity.Place;

public interface PlaceDAO extends DAO<Place> {

	Place getPlaceByCountryAndCity(String country, String city) throws DAOException;
}
