package ua.nure.miroshnichenko.summarytask4.myorm.core;

import java.sql.ResultSet;

/**
 * The interface for wrap class of java.sql.Connection. It is used by
 * {@link ua.myorm.core.ConnectionsPool} which controls a life cycle of
 * DBConnection and {@link ua.myorm.core.transaction.Transaction} which are
 * represented more high-level way for working with a database.
 * 
 * @author Miroshnichenko Y. D
 */
public interface DBConnection extends AutoCloseable {
	/**
	 * Execute sql query and return {@link #ResultSet}.
	 * 
	 * @param query the sql query.
	 * @return the {@link #ResultSet} object.
	 * @throws DBConnectionException
	 */
	ResultSet executeQuery(String query) throws DBConnectionException;

	/**
	 * Execute sql query and return {@link java.sql.ResultSet}. This method uses
	 * {@link java.sql.Connection#prepareStatement(String)}.
	 * 
	 * @param query     the sql query.
	 * @param parametrs values which replace '?' in the query.
	 * @return the {@link java.sql.ResultSet} object.
	 * @throws DBConnectionException
	 */
	ResultSet executeQuery(String query, Object... parametrs) throws DBConnectionException;

	/**
	 * Execute sql query and return true if there are one or more updates in a
	 * database.
	 * 
	 * @param query the sql query.
	 * @return true there are one or more updates in a database; false if there are
	 *         no changes in a database;
	 */
	boolean executeUpdate(String query) throws DBConnectionException;

	/**
	 * Execute sql query and return true if there are one or more updates in a
	 * database. This method uses
	 * {@link java.sql.Connection#prepareStatement(String)}.
	 * 
	 * @param query     the sql query.
	 * @param parametrs values which replace '?' in the query.
	 * @return true there are one or more updates in a database; false if there are
	 *         no changes in a database;
	 * @throws DBConnectionException
	 */
	boolean executeUpdate(String query, Object... parametrs) throws DBConnectionException;

	/**
	 * Call a sql procedure and return true if there are one or more updates in a
	 * database. This method uses
	 * {@link java.sql.Connection#prepareStatement(String)}.
	 * 
	 * @param query     the sql query.
	 * @param parametrs values which replace '?' in the query.
	 * @return true there are one or more updates in a database; false if there are
	 *         no changes in a database;
	 * @throws DBConnectionException
	 */
	boolean callProcedure(String query, Object...parametrs) throws DBConnectionException;
	
	/**
	 * Save all changes made in the current transaction.
	 * 
	 * @throws DBConnectionException
	 */
	void commit() throws DBConnectionException;

	/**
	 * Revoke all changes made in the current transaction.
	 * 
	 * @throws DBConnectionException
	 */
	void rollback() throws DBConnectionException;
}
