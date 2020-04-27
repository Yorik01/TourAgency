package ua.nure.miroshnichenko.touragency.service.impl;

import org.apache.log4j.Logger;

import ua.nure.miroshnichenko.touragency.db.dao.DAO;
import ua.nure.miroshnichenko.touragency.db.dao.DAOException;
import ua.nure.miroshnichenko.touragency.db.dao.DAOFactory;
import ua.nure.miroshnichenko.touragency.db.entity.User;
import ua.nure.miroshnichenko.touragency.service.BanningService;
import ua.nure.miroshnichenko.touragency.service.exception.ServiceException;
import ua.nure.miroshnichenko.touragency.service.impl.constants.LogginMessages;

class BanningServiceImpl implements BanningService {

	private final Logger LOG = Logger.getLogger(BanningServiceImpl.class);
	
	private DAOFactory factoryDAO = DAOFactory.getInstance();
	
	private static BanningServiceImpl instance;
	
	private BanningServiceImpl () {
		factoryDAO = DAOFactory.getInstance();
	}
	
	public static synchronized BanningServiceImpl getInstance() {
		if(instance == null) {
			instance = new BanningServiceImpl();
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
	public boolean banUser(int id) throws ServiceException {
		try {
			DAO<User> userDao = factoryDAO.getUserDAO();
			User oldUser = userDao.find(id);
			oldUser.setBanned(1);
			boolean result = userDao.update(oldUser);
			
			if (result) {
				LOG.trace(String.format(
						LogginMessages.USER_BANNED, oldUser.getEmail()));
			}
			return result;
		} catch (DAOException e) {
			LOG.error(e);
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean unbanUser(int id) throws ServiceException {
		try {
			DAO<User> userDao = factoryDAO.getUserDAO();
			User oldUser = userDao.find(id);
			oldUser.setBanned(0);
			boolean result = userDao.update(oldUser);

			if (result) {
				LOG.trace(String.format(
						LogginMessages.USER_UNBANNED, oldUser.getEmail()));
			}
			return result;
		} catch (DAOException e) {
			LOG.error(e);
			throw new ServiceException(e);
		}
	}
}
