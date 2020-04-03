package ua.nure.miroshnichenko.touragency.service;

import java.util.List;

import ua.nure.miroshnichenko.myorm.Entity;

public interface CRUDService<T extends Entity> {
	
	T get(int id) throws ServiceException;

	List<T> getAll() throws ServiceException;
	
	boolean save(T entity) throws ServiceException;
	
	boolean update(T entity) throws ServiceException;
	
	boolean delete(T entity) throws ServiceException;
}
