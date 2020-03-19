package ua.nure.miroshnichenko.summarytask4.myorm.core;

/**
 * It controls a pool of connections to database and theirs life cycles.
 * 
 * @author Miroshnichenko Y. D
 * @see {@link ua.myorm.core.DBConnection}
 */
public interface ConnectionsPool extends AutoCloseable {

	/**
	 * Get a connection from a pool and make it unavailable to be got.
	 * 
	 * @return {@link ua.myorm.core.DBConnection}
	 * @throws ConnectionsPoolException
	 */
	DBConnection getDBConnection() throws ConnectionsPoolException;

	/**
	 * Get a size of connection pool
	 */
	int size();

	/**
	 * Get a max number of connections which can be stored in the pool.
	 */
	int getLimit();

	/**
	 * Set a max number of connections which can be stored in the pool.
	 * 
	 */
	void setLimit(int limit);

	/**
	 * Return the current connection to the pool and make it available to be got.
	 * 
	 * @param connection which is finished to use.
	 */
	void free(DBConnection connection) throws ConnectionsPoolException;

	/**
	 * Set the time interval for working of internal collector which removes unused
	 * connections every 'interval' milliseconds.
	 * 
	 * @param time interval in millisecond.
	 */
	void setCollectorSchedule(long interval);

	/**
	 * Get the time interval for working of internal collector which removes unused
	 * connections every 'interval' milliseconds.
	 * 
	 * @return time interval in millisecond.
	 */
	long getCollectorSchedule();

	/**
	 * Get allowed time which every connection can be unused.
	 * 
	 * @return allowed time of waiting of connection.
	 */
	long getAllowedWaitingTime();

	/**
	 * Set allowed time which every connection can be unused.
	 * 
	 * @param allowed time of waiting of connection.
	 */

	void setAllowedWAitingTime(long time);
}
