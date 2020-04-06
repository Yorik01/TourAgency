package ua.nure.miroshnichenko.touragency.service;

import java.util.List;

import ua.nure.miroshnichenko.touragency.db.entity.User;

public interface AuthentificationService {

	User login(String email, String password) throws ServiceException;

	boolean signup(User user) throws ServiceException;

	boolean editUser(User user) throws ServiceException;
	
	User getUserInfo(int id) throws ServiceException;
	
	List<User> getAllUsers() throws ServiceException;
}
