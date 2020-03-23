package ua.nure.miroshnichenko.summarytask4.service;

import java.util.ArrayList;
import java.util.List;

import ua.nure.miroshnichenko.summarytask4.db.dao.DAO;
import ua.nure.miroshnichenko.summarytask4.db.dao.DAOException;
import ua.nure.miroshnichenko.summarytask4.db.dao.DAOFactory;
import ua.nure.miroshnichenko.summarytask4.db.dao.TransportDAO;
import ua.nure.miroshnichenko.summarytask4.db.entity.Transport;
import ua.nure.miroshnichenko.summarytask4.db.entity.TransportType;

public class TransportServiceImpl implements TransportService {

	private DAOFactory factoryDAO = DAOFactory.getInstance();
	
	@Override
	public Transport get(int id) throws ServiceException {
		Transport transport = null;

		try {
			DAO<Transport> dao = (DAO<Transport>) factoryDAO.getDAO("transport");
			transport = (Transport) dao.find(id);

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
			DAO<Transport> dao = (DAO<Transport>) factoryDAO.getDAO("transport");
			transports = dao.findAll();

			return transports;
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean save(Transport entity) throws ServiceException {
		try {
			DAO<Transport> dao = (DAO<Transport>) factoryDAO.getDAO("transport");
			boolean result = dao.save(entity);

			return result;
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean update(Transport entity) throws ServiceException {
		try {
			DAO<Transport> dao = (DAO<Transport>) factoryDAO.getDAO("transport");
			boolean result = dao.update(entity);

			return result;
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean delete(Transport entity) throws ServiceException {
		try {
			DAO<Transport> dao = (DAO<Transport>) factoryDAO.getDAO("transport");
			boolean result = dao.delete(entity);

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
