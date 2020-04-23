package ua.nure.miroshnichenko.touragency.db.dao;

import java.util.List;

import ua.nure.miroshnichenko.touragency.db.entity.Transport;
import ua.nure.miroshnichenko.touragency.db.entity.TransportType;

public interface TransportDAO extends DAO<Transport> {

	List<Transport> getTransportsByCode(String code) throws DAOException;
	
	Transport geTransportByCodeAndType(String code, TransportType transportType) throws DAOException;
}
