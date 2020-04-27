package ua.nure.miroshnichenko.touragency.service;

import java.util.List;

import ua.nure.miroshnichenko.touragency.db.entity.Place;
import ua.nure.miroshnichenko.touragency.db.entity.Route;
import ua.nure.miroshnichenko.touragency.service.exception.ServiceException;

/**
 * Service control information about routes.
 * 
 * @author Miroshnichenko Y. D.
 */
public interface RouteService extends CRUDService<Route> {

	/**
	 * Get information about place by country and city.
	 * 
	 * @param country country of place.
	 * @param city city of place.
	 * 	
	 * @return information about place.
	 * 
	 * @throws ServiceException
	 */
	Place getPlaceByCountryAndCity(String country, String city) throws ServiceException;
	
	/**
	 * Get a list of all places.
	 * 
	 * @return list of places.
	 * 
	 * @throws ServiceException
	 */
	List<Place> getAllPlaces() throws ServiceException;
	
	/**
	 * Get route information by places.
	 * 
	 * @param placeFromId id of place departure point.
	 * @param placeToId if of place of stay
	 * 	
	 * @return true if discount step has been changed.
	 * 
	 * @throws ServiceException
	 */
	Route getRouteByPlaces(int placeFromId, int placeToId) throws ServiceException;
}
