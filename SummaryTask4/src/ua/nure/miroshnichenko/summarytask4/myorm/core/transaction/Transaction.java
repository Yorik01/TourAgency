package ua.nure.miroshnichenko.summarytask4.myorm.core.transaction;

import java.util.List;

import ua.nure.miroshnichenko.summarytask4.myorm.Entity;
import ua.nure.miroshnichenko.summarytask4.myorm.core.DBConnectionException;
import ua.nure.miroshnichenko.summarytask4.myorm.core.transaction.exception.TransactionException;

/**
 * Declare the highest layer for working with a database. It can use entities
 * which implement {@link #ua.nure.miroshnichenko.summarytask4.myorm.Entity}. Entities can be mapped into sql
 * queries and results of queries can be mapped into entities using
 * {@link #ua.nure.miroshnichenko.summarytask4.myorm.core.mapping.Mapper}.
 * 
 * @author Miroshnichenko Y. D
 * @see #ua.nure.miroshnichenko.summarytask4.myorm.core.mapping.Mapper
 */
public interface Transaction extends Queries, AutoCloseable {

	/**
	 * Get status of the transaction
	 * 
	 * @return current status.
	 * @see #TransactionStatus
	 */
	TransactionStatus geTransactionStatus();

	/**
	 * @return the result of the last query
	 */
	List<? extends Entity> getResult();

	/**
	 * Save all changes made in the current transaction.
	 * 
	 * @throws TransactionException
	 */
	void commit() throws TransactionException;

	/**
	 * Revoke all changes made in the current transaction.
	 * 
	 * @throws TransactionException
	 */
	void rollback() throws TransactionException;
	
	void close() throws TransactionException;
}
