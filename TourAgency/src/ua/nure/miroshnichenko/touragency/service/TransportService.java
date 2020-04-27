package ua.nure.miroshnichenko.touragency.service;

import java.util.List;

import ua.nure.miroshnichenko.touragency.db.entity.Transport;
import ua.nure.miroshnichenko.touragency.db.entity.TransportType;
import ua.nure.miroshnichenko.touragency.service.exception.ServiceException;

/**
 * Service control information about transports.
 * 
 * @author Miroshnichenko Y. D.
 */
public interface TransportService extends CRUDService<Transport> {

	/**
	 * Get a list of transports by a code
	 * 
	 * @param code code of transport.
	 * 	
	 * @return list of transports.
	 * 
	 * @throws ServiceException
	 */
	List<Transport> getTransportsByCode(String code) throws ServiceException;
	
	/**
	 * Get information about transport by code and type.
	 * 
	 * @param code code of transport.
	 * @param type type of transport.
	 * 
	 * @return information about transport.
	 * 
	 * @throws ServiceException
	 */
	Transport getTransportByCodeAndType(String code, TransportType type) throws ServiceException;
}
