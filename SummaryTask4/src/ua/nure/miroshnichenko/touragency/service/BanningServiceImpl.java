package ua.nure.miroshnichenko.touragency.service;

import ua.nure.miroshnichenko.touragency.db.dao.DAO;
import ua.nure.miroshnichenko.touragency.db.dao.DAOException;
import ua.nure.miroshnichenko.touragency.db.dao.DAOFactory;
import ua.nure.miroshnichenko.touragency.db.entity.User;

class BanningServiceImpl implements BanningService {

	private DAOFactory factoryDAO = DAOFactory.getInstance();

	@Override
	public boolean banUser(int id) throws ServiceException {
		try {
			DAO<User> userDao = factoryDAO.getUserDAO();
			User oldUser = userDao.find(id);
			oldUser.setBanned(1);
			boolean result = userDao.update(oldUser);

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
			User oldUser = userDao.find(id);
			oldUser.setBanned(0);
			boolean result = userDao.update(oldUser);

			return result;
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}
}
