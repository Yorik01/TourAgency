package ua.nure.miroshnichenko.summarytask4.service;

import ua.nure.miroshnichenko.summarytask4.db.entity.User;

public interface BanningService {

	boolean banUser(int id) throws ServiceException;

	boolean unbanUser(int id) throws ServiceException;
}
