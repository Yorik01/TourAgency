package ua.nure.miroshnichenko.summarytask4.myorm.core.transaction;

import java.util.List;

import ua.nure.miroshnichenko.summarytask4.myorm.Entity;
import ua.nure.miroshnichenko.summarytask4.myorm.core.transaction.exception.TransactionException;

/**
 * Represent declaration of queries which are supported by this ORM.
 * 
 * @author Miroshnichenko Y. D
 */
interface Queries extends Crud {
	/**
	 * Allow to execute custom query to a database.
	 * 
	 * @param query sql query.
	 * @param type  meta info of the current class.
	 * 
	 * @return class which implements {@link ua.nure.miroshnichenko.summarytask4.myorm.Entity} and can be mapped to a
	 *         table in the database;
	 */
	List<Entity> customQuery(String query, Class<? extends Entity> type) throws TransactionException;

	/**
	 * Allow to execute custom query to a database.
	 * 
	 * @param query     sql query.
	 * @param type      meta info of the current class.
	 * @param parametrs values which replace '?' in the query.
	 * 
	 * @return class which implements {@link ua.nure.miroshnichenko.summarytask4.myorm.Entity} and can be mapped to a
	 *         table in the database;
	 */
	List<Entity> customQuery(String query, Class<? extends Entity> type, Object... parametrs)
			throws TransactionException;
	
	boolean customUpdate(String query, Object... parametrs)
			throws TransactionException;
	
	boolean callProcedure(String name, Object...parametrs) throws TransactionException;
}
