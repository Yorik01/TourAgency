package ua.nure.miroshnichenko.touragency.service;

import java.util.List;

import ua.nure.miroshnichenko.myorm.Entity;
import ua.nure.miroshnichenko.touragency.service.exception.ServiceException;

/**
 * Base interface of all services which has CRUD operations.
 * 
 * @author Miroshnichenko Y. D.
 */
public interface CRUDService<T extends Entity> {
	
	/**
	 * Get an entity by id.
	 * 
	 * @param id id of entity.
	 * 	
	 * @return entity.
	 * 
	 * @throws ServiceException
	 */
	T get(int id) throws ServiceException;

	/**
	 * Get all entities.
	 * 
	 * @return list of entities.
	 * 
	 * @throws ServiceException
	 */
	List<T> getAll() throws ServiceException;
	
	/**
	 * Save a specific entity.
	 * 
	 * @param entity entity.
	 * 	
	 * @return true if entity has been added in database.
	 * 
	 * @throws ServiceException
	 */
	boolean save(T entity) throws ServiceException;
	
	/**
	 * Update a specific entity.
	 * 
	 * @param entity entity.
	 * 	
	 * @return true if entity has been updated in database.
	 * 
	 * @throws ServiceException
	 */
	boolean update(T entity) throws ServiceException;
	
	/**
	 * Delete a specific entity.
	 * 
	 * @param entity entity.
	 * 	
	 * @return true if entity has been deleted.
	 * 
	 * @throws ServiceException
	 */
	boolean delete(T entity) throws ServiceException;
}
