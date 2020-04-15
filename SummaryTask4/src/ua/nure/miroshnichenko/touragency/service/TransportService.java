package ua.nure.miroshnichenko.touragency.service;

import ua.nure.miroshnichenko.touragency.db.entity.Transport;
import ua.nure.miroshnichenko.touragency.db.entity.TransportType;
import ua.nure.miroshnichenko.touragency.service.exception.ServiceException;

public interface TransportService extends CRUDService<Transport> {

	Transport getTransportByCodeAndType(String code, TransportType type) throws ServiceException;
}
