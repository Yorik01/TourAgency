package ua.nure.miroshnichenko.touragency.service;

public interface BanningService {

	boolean banUser(int id) throws ServiceException;

	boolean unbanUser(int id) throws ServiceException;
}
