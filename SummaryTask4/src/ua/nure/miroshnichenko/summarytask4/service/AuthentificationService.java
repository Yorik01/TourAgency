package ua.nure.miroshnichenko.summarytask4.service;

import ua.nure.miroshnichenko.summarytask4.db.entity.User;

public interface AuthentificationService {

	User login(String email, String password) throws ServiceException;

	boolean signup(User user) throws ServiceException;

	User getUserInfo(int id) throws ServiceException;
}
