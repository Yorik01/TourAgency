package ua.nure.miroshnichenko.touragency.service;

import java.util.ArrayList;
import java.util.List;

import ua.nure.miroshnichenko.touragency.db.dao.DAOException;
import ua.nure.miroshnichenko.touragency.db.dao.DAOFactory;
import ua.nure.miroshnichenko.touragency.db.dao.TransportDAO;
import ua.nure.miroshnichenko.touragency.db.entity.Transport;
import ua.nure.miroshnichenko.touragency.db.entity.TransportType;

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
	public boolean save(Transport entity) throws ServiceException {
		try {
			TransportDAO transportDAO = factoryDAO.getTransportDAO();
			boolean result = transportDAO.save(entity);

			return result;
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean update(Transport entity) throws ServiceException {
		try {
			TransportDAO transportDAO = factoryDAO.getTransportDAO();
			boolean result = transportDAO.update(entity);

			return result;
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean delete(Transport entity) throws ServiceException {
		try {
			TransportDAO transportDAO = factoryDAO.getTransportDAO();
			boolean result = transportDAO.delete(entity);

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
}
