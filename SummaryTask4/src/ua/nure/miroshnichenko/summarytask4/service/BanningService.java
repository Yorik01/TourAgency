package ua.nure.miroshnichenko.summarytask4.service;

import ua.nure.miroshnichenko.summarytask4.db.entity.User;

public interface BanningService {

	boolean banUser(User user) throws ServiceException;

	boolean unbanUser(User user) throws ServiceException;
}
