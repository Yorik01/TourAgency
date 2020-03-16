package ua.nure.miroshnichenko.summarytask4.service;

import ua.nure.miroshnichenko.summarytask4.db.entity.Transport;
import ua.nure.miroshnichenko.summarytask4.db.entity.TransportType;

public interface TransportService extends CRUDService<Transport> {

	Transport getTransportByCodeAndType(String code, TransportType type) throws ServiceException;
}
