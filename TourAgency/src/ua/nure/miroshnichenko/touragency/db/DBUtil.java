package ua.nure.miroshnichenko.touragency.db;

import org.apache.log4j.Logger;

import ua.nure.miroshnichenko.myorm.core.Settings;
import ua.nure.miroshnichenko.myorm.core.transaction.Transaction;
import ua.nure.miroshnichenko.myorm.core.transaction.TransactionFactory;
import ua.nure.miroshnichenko.myorm.core.transaction.exception.TransactionException;
import ua.nure.miroshnichenko.myorm.core.transaction.exception.TransactionFactoryException;

/**
 * Util class to work with database
 * 
 * @author Miroshnichenko Y. D.
 */
public final class DBUtil {

	private static Settings settings;

	private static TransactionFactory transactionFactory;
	
	private static final Logger LOG = Logger.getLogger(DBUtil.class);

	static {
		// load db properties from aoo.properties
		settings = new Settings();
		try {
			transactionFactory = settings.getTransactionFactory();
		} catch (Exception e) {
			LOG.error(e);
		}
	}
	
	private DBUtil() {
	}

	/**
	 * Get transaction.
	 */
	public static Transaction getTransaction() throws TransactionFactoryException {
		return transactionFactory.createTransaction();
	}

	/**
	 * Call a stored procedure.
	 * 
	 * @param name name of a stored procedure.
	 * @param parametrs params of a store procedure.
	 * 
	 * @return result of working a stored procedure
	 */
	public static boolean callProcedure(String name, Object...parametrs) throws TransactionFactoryException, TransactionException {
		Transaction transaction = getTransaction();
		boolean result = transaction.callProcedure(name, parametrs);
		transaction.commit();
		
		return result;
	}
	
	/**
	 * Close connection
	 */
	public static void close(Transaction transaction) throws TransactionException {
		if (transaction != null) {
			transaction.close();
		}
	}

	/**
	 * Close all connections in the pool.
	 */
	public static void closeConnection() throws TransactionFactoryException {
		if (transactionFactory != null) {
			transactionFactory.close();
		}
	}
}
