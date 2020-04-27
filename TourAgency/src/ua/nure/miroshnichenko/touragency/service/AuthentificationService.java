package ua.nure.miroshnichenko.touragency.service;

import java.util.List;

import ua.nure.miroshnichenko.touragency.db.entity.User;
import ua.nure.miroshnichenko.touragency.service.exception.ServiceException;

/**
 * Service to control a authentications of users.
 * 
 * @author Miroshnichenko Y. D.
 */
public interface AuthentificationService {

	/**
	 * Log in user in system. Throws 
	 * {@link ua.nure.miroshnichenko.touragency.service.exception.IncorrectLoginException}
	 * if entered values are bad.
	 * 
	 * @param email email of user.
	 * @param password password of user.
	 * 
	 * @return user information if user has logged in successfully.
	 * 
	 * @throws IncorrectLoginException
	 */
	User login(String email, String password) throws ServiceException;

	/**
	 * Register a new user in system.
	 * 
	 * @param user information about new user.
	 * 
	 * @return true if user has been added to database.
	 * 
	 * @throws SignupException
	 */
	boolean signup(User user) throws ServiceException;

	/**
	 * Edit information about specific user.
	 * 
	 * @param user information about user.
	 * 
	 * @return true if user has been changed in database.
	 * 
	 * @throws ServiceException
	 */
	boolean editUser(User user) throws ServiceException;
	
	/**
	 * Retrieve information about user by id.
	 * 
	 * @param id id of user
	 * 
	 * @return information about user
	 * 
	 * @throws ServiceException
	 */
	User getUserInfo(int id) throws ServiceException;
	
	
	/**
	 * Get a list of all users.
	 * 
	 * @return list of all users
	 * 
	 * @throws ServiceException
	 */
	List<User> getAllUsers() throws ServiceException;
}
