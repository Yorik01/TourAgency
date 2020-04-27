package ua.nure.miroshnichenko.touragency.service.impl;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.miroshnichenko.touragency.db.dao.DAOException;
import ua.nure.miroshnichenko.touragency.db.dao.DAOFactory;
import ua.nure.miroshnichenko.touragency.db.dao.UserDAO;
import ua.nure.miroshnichenko.touragency.db.entity.Role;
import ua.nure.miroshnichenko.touragency.db.entity.User;
import ua.nure.miroshnichenko.touragency.service.AuthentificationService;
import ua.nure.miroshnichenko.touragency.service.exception.IncorrectLoginException;
import ua.nure.miroshnichenko.touragency.service.exception.ServiceException;
import ua.nure.miroshnichenko.touragency.service.exception.SignupException;
import ua.nure.miroshnichenko.touragency.service.impl.constants.ExceptionMessages;
import ua.nure.miroshnichenko.touragency.service.impl.constants.LogginMessages;

class AuthentificationServiceImpl implements AuthentificationService {

	private final Logger LOG = Logger.getLogger(AuthentificationServiceImpl.class);
	
	private DAOFactory factoryDAO;
	
	private static AuthentificationServiceImpl instance;
	
	private final String hashAlgorithm = "SHA-256";
	
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
						LOG.trace(String.format(LogginMessages.USER_LOGGED_IN, email));
						return user;
					}
					throw new IncorrectLoginException(ExceptionMessages.INCORECT_PASSWORD);
				}
				throw new IncorrectLoginException(ExceptionMessages.INCORRECT_LOGIN);
			} catch (DAOException e) {
				LOG.error(e);
				throw new ServiceException(e);
			}
		} 
		throw new ServiceException(ExceptionMessages.ERROR_HASH_GENERATE);
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
					
					LOG.trace(LogginMessages.USER_CREATED);
					return result;
				}
				throw new ServiceException(ExceptionMessages.ERROR_HASH_GENERATE);
			}
			throw new SignupException(ExceptionMessages.USER_EXIST);
		} catch (DAOException e) {
			LOG.error(e);
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
				throw new ServiceException(ExceptionMessages.ERROR_HASH_GENERATE);
			}
			
			throw new ServiceException(ExceptionMessages.USER_EXIST);
		} catch (DAOException e) {
			LOG.error(e);
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
			LOG.error(e);
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
			LOG.error(e);
			throw new ServiceException(e);
		}
	}

	private String hashPassword(String password) {
		String generatedPassword = null;

		try {
			MessageDigest md = MessageDigest.getInstance(hashAlgorithm);

			md.update(password.getBytes("UTF-8"));
			byte[] bytes = md.digest();

			StringBuilder hash = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				hash.append(Integer.toString(0xff & bytes[i], 16));
			}

			generatedPassword = hash.toString();
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			LOG.error(e);
			generatedPassword = null;
		}
		return generatedPassword;
	}
}
