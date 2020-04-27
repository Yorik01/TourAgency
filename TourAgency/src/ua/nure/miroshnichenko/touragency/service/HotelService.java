package ua.nure.miroshnichenko.touragency.service;

import ua.nure.miroshnichenko.touragency.db.entity.Hotel;
import ua.nure.miroshnichenko.touragency.service.exception.ServiceException;

/**
 * Service control information about hotels.
 * 
 * @author Miroshnichenko Y. D.
 */
public interface HotelService extends CRUDService<Hotel> {

	/**
	 * Retrieve information about specific hotel by name.
	 * 
	 * @param name name of hotel.
	 * 	
	 * @return information about tour.
	 * 
	 * @throws ServiceException
	 */
	Hotel getHotelByName(String name) throws ServiceException;
}
