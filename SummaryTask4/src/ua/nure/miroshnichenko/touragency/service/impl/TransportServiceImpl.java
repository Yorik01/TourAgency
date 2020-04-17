package ua.nure.miroshnichenko.touragency.service.impl;

import java.util.ArrayList;
import java.util.List;

import ua.nure.miroshnichenko.touragency.db.dao.DAOException;
import ua.nure.miroshnichenko.touragency.db.dao.DAOFactory;
import ua.nure.miroshnichenko.touragency.db.dao.TransportDAO;
import ua.nure.miroshnichenko.touragency.db.entity.Transport;
import ua.nure.miroshnichenko.touragency.db.entity.TransportType;
import ua.nure.miroshnichenko.touragency.service.TransportService;
import ua.nure.miroshnichenko.touragency.service.exception.ServiceException;

public class TransportServiceImpl implements TransportService {

	private DAOFactory factoryDAO = DAOFactory.getInstance();
	
	@Override
	public Transport get(int id) throws ServiceException {
		Transport transport = null;

		try {
			TransportDAO transportDAO = factoryDAO.getTransportDAO();
			transport = transportDAO.find(id);

			return transport;
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
		
	}

	@Override
	public List<Transport> getAll() throws ServiceException {
		List<Transport> transports = new ArrayList<>();

		try {
			TransportDAO transportDAO = factoryDAO.getTransportDAO();
			transports = transportDAO.findAll();

			return transports;
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean save(Transport transport) throws ServiceException {
		try {
			TransportDAO transportDAO = factoryDAO.getTransportDAO();
			if (isTransportUnique(transport)) {
				return transportDAO.save(transport);
			}
			
			throw new ServiceException("This transport already exist!");
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean update(Transport transport) throws ServiceException {
		try {
			TransportDAO transportDAO = factoryDAO.getTransportDAO();
			
			Transport oldTransport = getTransportByCodeAndType(
					transport.getCode(), transport.getType());
			
			if (oldTransport == null || transport.getId() == oldTransport.getId()) {
				return transportDAO.update(transport);
			}
			
			throw new ServiceException("This transport already exist!");
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean delete(Transport transport) throws ServiceException {
		try {
			TransportDAO transportDAO = factoryDAO.getTransportDAO();
			boolean result = transportDAO.delete(transport);

			return result;
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	@Override
	public Transport getTransportByCodeAndType(String code, TransportType type) throws ServiceException {
		Transport transport = null;

		try {
			TransportDAO transportDAO = factoryDAO.getTransportDAO();
			transport = (Transport) transportDAO.geTransportByCodeAndType(code, type);

			return transport;
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}
	
	private boolean isTransportUnique(Transport transport) throws ServiceException {
		return getTransportByCodeAndType(
				transport.getCode(), transport.getType()) == null;
	}
}
