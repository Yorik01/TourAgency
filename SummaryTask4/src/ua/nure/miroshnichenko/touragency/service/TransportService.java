package ua.nure.miroshnichenko.touragency.service;

import java.util.List;

import ua.nure.miroshnichenko.touragency.db.entity.Transport;
import ua.nure.miroshnichenko.touragency.db.entity.TransportType;
import ua.nure.miroshnichenko.touragency.service.exception.ServiceException;

public interface TransportService extends CRUDService<Transport> {

	List<Transport> getTransportsByCode(String code) throws ServiceException;
	
	Transport getTransportByCodeAndType(String code, TransportType type) throws ServiceException;
}
