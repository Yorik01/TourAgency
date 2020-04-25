package ua.nure.miroshnichenko.touragency.service.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import ua.nure.miroshnichenko.touragency.db.dao.DAOException;
import ua.nure.miroshnichenko.touragency.db.dao.DAOFactory;
import ua.nure.miroshnichenko.touragency.db.dao.UserDAO;
import ua.nure.miroshnichenko.touragency.db.entity.Role;
import ua.nure.miroshnichenko.touragency.db.entity.User;
import ua.nure.miroshnichenko.touragency.service.AuthentificationService;
import ua.nure.miroshnichenko.touragency.service.exception.IncorrectLoginException;
import ua.nure.miroshnichenko.touragency.service.exception.ServiceException;
import ua.nure.miroshnichenko.touragency.service.exception.SignupException;

class AuthentificationServiceImpl implements AuthentificationService {

	private DAOFactory factoryDAO;
	
	private static AuthentificationServiceImpl instance;
	
	private AuthentificationServiceImpl() {
		factoryDAO = DAOFactory.getInstance();
	}
	
	public static synchronized AuthentificationServiceImpl getInstance() {
		if (instance == null) {
			instance = new AuthentificationServiceImpl();
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
	public User login(String email, String password) throws ServiceException {
		String hash = hashPassword(password);
		if (hash != null) {
			try {
				UserDAO userDAO = factoryDAO.getUserDAO();
				User user = userDAO.getUserByEmail(email);

				if (user != null) {
					if (user.getPassword().equals(hash)) {
						return user;
					}
					throw new IncorrectLoginException("Incorrect password!!!");
				}
				throw new IncorrectLoginException("Incorrect email!!!");
			} catch (DAOException e) {
				e.printStackTrace();
				throw new ServiceException(e);
			}
		} else {
			throw new ServiceException("Cannot genarate hash of password!!!");
		}
	}

	@Override
	public boolean signup(User user) throws ServiceException {
		String email = user.getEmail();
		String password = user.getPassword();

		try {
			UserDAO userDAO = factoryDAO.getUserDAO();
			
			User user1 = userDAO.getUserByEmail(email);

			if (user1 == null) {
				String hash = hashPassword(password);
				if (hash != null) {
					user.setPassword(hash);

					boolean result = userDAO.save(user);
					
					return result;
				}
				throw new ServiceException("Cannot genarate hash of password!!!");
			}
			throw new SignupException("The user with the same email already exists!");
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}
	
	@Override
	public boolean editUser(User user) throws ServiceException {
		String email = user.getEmail();
		String password = user.getPassword();

		try {
			UserDAO userDAO = factoryDAO.getUserDAO();
			
			User user1 = userDAO.getUserByEmail(email);

			if (user1 == null || user1.getId() == user.getId()) {
				String hash = hashPassword(password);
				if (hash != null) {
					user.setPassword(hash);

					boolean result = userDAO.update(user);
					
					return result;
				}
				throw new ServiceException("Cannot genarate hash of password!!!");
			}
			
			throw new ServiceException("The user with the same email already exists!");
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	@Override
	public User getUserInfo(int id) throws ServiceException {
		try {
			UserDAO userDAO = factoryDAO.getUserDAO();
			User user = userDAO.find(id);

			return user;
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}
	
	@Override
	public List<User> getAllUsers() throws ServiceException {
		try {
			UserDAO userDAO = factoryDAO.getUserDAO();
			List<User> users = userDAO.findAll();
			
			users.removeIf(user -> user.getRole() == Role.ADMIN);
			
			return users;
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	private static String hashPassword(String password) {
		String generatedPassword = null;

		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");

			md.update(password.getBytes());
			byte[] bytes = md.digest();

			StringBuilder hash = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				hash.append(Integer.toString(bytes[i], 16).substring(1));
			}

			generatedPassword = hash.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			generatedPassword = null;
		}
		return generatedPassword;
	}
}
