package ua.nure.miroshnichenko.touragency.service;

import ua.nure.miroshnichenko.touragency.service.exception.ServiceException;

public interface BanningService {

	boolean banUser(int id) throws ServiceException;

	boolean unbanUser(int id) throws ServiceException;
}
