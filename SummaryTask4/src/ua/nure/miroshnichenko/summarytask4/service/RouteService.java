package ua.nure.miroshnichenko.summarytask4.service;

import ua.nure.miroshnichenko.summarytask4.db.entity.Place;
import ua.nure.miroshnichenko.summarytask4.db.entity.Route;

public interface RouteService extends CRUDService<Route> {

	Place getPlaceByCountryAndCity(String country, String city) throws ServiceException;
}
