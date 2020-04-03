package ua.nure.miroshnichenko.touragency.db.dao;

import ua.nure.miroshnichenko.touragency.db.entity.Transport;
import ua.nure.miroshnichenko.touragency.db.entity.TransportType;

public interface TransportDAO extends DAO<Transport> {

	Transport geTransportByCodeAndType(String code, TransportType transportType) throws DAOException;
}
