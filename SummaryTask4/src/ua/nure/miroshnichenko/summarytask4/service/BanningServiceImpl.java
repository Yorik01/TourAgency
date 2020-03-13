package ua.nure.miroshnichenko.summarytask4.service;

import ua.nure.miroshnichenko.summarytask4.db.dao.DAO;
import ua.nure.miroshnichenko.summarytask4.db.dao.DAOException;
import ua.nure.miroshnichenko.summarytask4.db.dao.DAOFactory;
import ua.nure.miroshnichenko.summarytask4.db.entity.User;

public class BanningServiceImpl implements BanningService {

	private DAOFactory factoryDAO = DAOFactory.getInstance();

	@Override
	public boolean banUser(int id) throws ServiceException {
		try {
			DAO<User> userDao = factoryDAO.getUserDAO();
			User user2 = userDao.find(id);
			user2.setBanned(true);
			boolean result = userDao.update(user2);

			return result;
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean unbanUser(int id) throws ServiceException {
		try {
			DAO<User> userDao = factoryDAO.getUserDAO();
			User user2 = userDao.find(id);
			user2.setBanned(false);
			boolean result = userDao.update(user2);

			return result;
		} catch (DAOException e) {
			e.printStackTrace();	
			throw new ServiceException(e);
		}
	}
}
