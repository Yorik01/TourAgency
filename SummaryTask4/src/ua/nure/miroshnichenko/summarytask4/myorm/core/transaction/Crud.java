package ua.nure.miroshnichenko.summarytask4.myorm.core.transaction;

import java.util.List;
import java.util.Map;

import ua.nure.miroshnichenko.summarytask4.myorm.Entity;
import ua.nure.miroshnichenko.summarytask4.myorm.core.transaction.exception.TransactionException;

/**
 * Declare the CRUD operations for this ORM.
 * 
 * @author Miroshnichenko Y. D
 */
interface Crud {
	/**
	 * Save entity to a database.
	 * 
	 * @param entity a class which implements {@link #ua.orm.Entity}
	 * @return true if entity has been saved successfully; false if is hasn't.
	 * @throws TransactionException
	 */
	boolean insert(Entity entity) throws TransactionException;

	/**
	 * Update entity in a database.
	 * 
	 * @param entity a class which implements {@link #ua.orm.Entity}
	 * @return true if entity has been updated successfully; false if is hasn't.
	 * @throws TransactionException
	 */
	boolean update(Entity entity) throws TransactionException;

	/**
	 * Find and return entity in a database by primary keys.
	 * 
	 * @param type   meta info of the searched class in a database.
	 * @param values values of primary keys.
	 * @throws TransactionException
	 */
	Entity findByPK(Class<? extends Entity> type, Object... values) throws TransactionException;

	/**
	 * Find and return entities in a database by the condition.
	 * 
	 * @param type      meta info of the searched class in a database.
	 * @param condition condition for the query is represented by {@ #java.util.Map}
	 *                  where keys are names of columns in the database.
	 * @throws TransactionException
	 */
	List<Entity> find(Class<? extends Entity> type, Map<String, Object> condition) throws TransactionException;

	/**
	 * Find and return all entities from the current table.
	 * 
	 * @param type meta info of the searched class in a database.
	 * @throws TransactionException
	 */
	List<Entity> findAll(Class<? extends Entity> type) throws TransactionException;

	/**
	 * Delete entity from a database.
	 * 
	 * @param entity a class which implements {@link #ua.orm.Entity}
	 * @return true if entity has been deleted successfully; false if is hasn't.
	 * @throws TransactionException
	 */
	boolean delete(Entity entity) throws TransactionException;

	/**
	 * Save entity to a database by the condition.
	 * 
	 * @param entity    a class which implements {@link #ua.orm.Entity}
	 * @param condition condition for the query is represented by {@ #java.util.Map}
	 *                  where keys are names of columns in the database.
	 * @return true if entity has been saved successfully; false if is hasn't.
	 * @throws TransactionException
	 */
	boolean delete(Class<? extends Entity> type, Map<String, Object> condition) throws TransactionException;

	/**
	 * Delete all records from a database.
	 * 
	 * @param entity a class which implements {@link #ua.orm.Entity}
	 * @return true if entity has been deleted successfully; false if is hasn't.
	 * @throws TransactionException
	 */
	boolean deleteAll(Class<? extends Entity> type) throws TransactionException;
}
