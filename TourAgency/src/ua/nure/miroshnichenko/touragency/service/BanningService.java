package ua.nure.miroshnichenko.touragency.service;

import ua.nure.miroshnichenko.touragency.service.exception.ServiceException;

/**
 * Service to control status of users in system.
 * 
 * @author Miroshnichenko Y. D.
 */
public interface BanningService {
	
	/**
	 * Ban a specific user.
	 * 
	 * @param id id of user
	 * 	
	 * @return true if user has been banned.
	 * 
	 * @throws ServiceException
	 */
	boolean banUser(int id) throws ServiceException;

	/**
	 * Unban a specific user.
	 * 
	 * @param id id of user.
	 * 	
	 * @return true if user has been unbanned.
	 * 
	 * @throws ServiceException
	 */
	boolean unbanUser(int id) throws ServiceException;
}
