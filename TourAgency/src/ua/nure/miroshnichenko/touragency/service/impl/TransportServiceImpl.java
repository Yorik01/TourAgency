package ua.nure.miroshnichenko.touragency.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.miroshnichenko.touragency.db.dao.DAOException;
import ua.nure.miroshnichenko.touragency.db.dao.DAOFactory;
import ua.nure.miroshnichenko.touragency.db.dao.TransportDAO;
import ua.nure.miroshnichenko.touragency.db.entity.Transport;
import ua.nure.miroshnichenko.touragency.db.entity.TransportType;
import ua.nure.miroshnichenko.touragency.service.TransportService;
import ua.nure.miroshnichenko.touragency.service.exception.ServiceException;
import ua.nure.miroshnichenko.touragency.service.impl.constants.ExceptionMessages;
import ua.nure.miroshnichenko.touragency.service.impl.constants.LogginMessages;

public class TransportServiceImpl implements TransportService {

	private Logger LOG = Logger.getLogger(TransportServiceImpl.class);
	
	private DAOFactory factoryDAO = DAOFactory.getInstance();
	
	private static TransportServiceImpl instance;
	
	private TransportServiceImpl () {
		factoryDAO = DAOFactory.getInstance();
	}
	
	public static synchronized TransportServiceImpl getInstance() {
		if(instance == null) {
			instance = new TransportServiceImpl();
		}
		return instance;
	}
	
	/**
	 * Set the DAOFactory implementation using the setter.
	 * It is used for mock test.
	 */
	public void setFactoryDAO(DAOFactory factoryDAO) {
		this.factoryDAO = factoryDAO;
	}
	
	@Override
	public Transport get(int id) throws ServiceException {
		Transport transport = null;

		try {
			TransportDAO transportDAO = factoryDAO.getTransportDAO();
			transport = transportDAO.find(id);

			return transport;
		} catch (DAOException e) {
			LOG.error(e);
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
			LOG.error(e);
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean save(Transport transport) throws ServiceException {
		try {
			TransportDAO transportDAO = factoryDAO.getTransportDAO();
			if (isTransportUnique(transport)) {
				boolean res = transportDAO.save(transport);
				
				if (res) {
					LOG.trace(LogginMessages.TRANSPORT_CREATED);
				}
				return res;
			}
			
			throw new ServiceException(ExceptionMessages.TRANSPORT_EXIST);
		} catch (DAOException e) {
			LOG.error(e);
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
				boolean res = transportDAO.update(transport);
				
				if (res) {
					LOG.trace(LogginMessages.TOUR_UPDATE);
				}
				return res;
			}
			
			throw new ServiceException(ExceptionMessages.TRANSPORT_EXIST);
		} catch (DAOException e) {
			LOG.error(e);
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean delete(Transport transport) throws ServiceException {
		try {
			TransportDAO transportDAO = factoryDAO.getTransportDAO();
			boolean res = transportDAO.delete(transport);

			if (res) {
				LOG.trace(LogginMessages.TOUR_DELETE);
			}
			
			return res;
		} catch (DAOException e) {
			LOG.error(e);
			throw new ServiceException(e);
		}
	}

	
	@Override
	public List<Transport> getTransportsByCode(String code) throws ServiceException {
		List<Transport> transports;
		try {
			TransportDAO transportDAO = factoryDAO.getTransportDAO();
			transports = transportDAO.getTransportsByCode(code);
			
			return transports;
		} catch (DAOException e) {
			LOG.error(e);
			throw new ServiceException(e);
		}
	}
	
	@Override
	public Transport getTransportByCodeAndType(String code, TransportType type) throws ServiceException {
		Transport transport = null;

		try {
			TransportDAO transportDAO = factoryDAO.getTransportDAO();
			transport = transportDAO.geTransportByCodeAndType(code, type);

			return transport;
		} catch (DAOException e) {
			LOG.error(e);
			throw new ServiceException(e);
		}
	}
	
	private boolean isTransportUnique(Transport transport) throws ServiceException {
		return getTransportByCodeAndType(
				transport.getCode(), transport.getType()) == null;
	}
}
