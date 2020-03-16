package ua.nure.miroshnichenko.summarytask4.db.dao;

import ua.nure.miroshnichenko.summarytask4.db.entity.Transport;
import ua.nure.miroshnichenko.summarytask4.db.entity.TransportType;

public interface TransportDAO extends DAO<Transport> {

	Transport geTransportByCodeAndType(String code, TransportType transportType) throws DAOException;
}
