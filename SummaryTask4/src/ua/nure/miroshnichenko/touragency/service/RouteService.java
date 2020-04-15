package ua.nure.miroshnichenko.touragency.service;

import java.util.List;

import ua.nure.miroshnichenko.touragency.db.entity.Place;
import ua.nure.miroshnichenko.touragency.db.entity.Route;
import ua.nure.miroshnichenko.touragency.service.exception.ServiceException;

public interface RouteService extends CRUDService<Route> {

	Place getPlaceByCountryAndCity(String country, String city) throws ServiceException;
	
	List<Place> getAllPlaces() throws ServiceException;
}
