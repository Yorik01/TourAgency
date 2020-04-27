package ua.nure.miroshnichenko.touragency.db;

import org.apache.log4j.Logger;

import ua.nure.miroshnichenko.myorm.core.Settings;
import ua.nure.miroshnichenko.myorm.core.transaction.Transaction;
import ua.nure.miroshnichenko.myorm.core.transaction.TransactionFactory;
import ua.nure.miroshnichenko.myorm.core.transaction.exception.TransactionException;
import ua.nure.miroshnichenko.myorm.core.transaction.exception.TransactionFactoryException;

public final class DBUtil {

	private static final String URL = "jdbc:mysql://localhost:3306/tour_agency?serverTimezone=UTC";

	private static final String USER_NAME = "epam_practice";

	private static final String PASSWORD = "1234";

	private static Settings settings;

	private static TransactionFactory transactionFactory;
	
	private static final Logger LOG = Logger.getLogger(DBUtil.class);

	static {
		settings = new Settings(URL, USER_NAME, PASSWORD);
		try {
			transactionFactory = settings.getTransactionFactory();
		} catch (TransactionFactoryException e) {
			LOG.error(e);
		}
	}
	
	private DBUtil() {
	}

	public static void connect() throws TransactionFactoryException {
		settings = new Settings(URL, USER_NAME, PASSWORD);
		transactionFactory = settings.getTransactionFactory();
	}

	public static Transaction getTransaction() throws TransactionFactoryException {
		return transactionFactory.createTransaction();
	}

	public static boolean callProcedure(String name, Object...parametrs) throws TransactionFactoryException, TransactionException {
		Transaction transaction = getTransaction();
		boolean result = transaction.callProcedure(name, parametrs);
		transaction.commit();
		
		return result;
	}
	
	public static void close(Transaction transaction) throws TransactionException {
		if (transaction != null) {
			transaction.close();
		}
	}

	public static void closeConnection() throws TransactionFactoryException {
		if (transactionFactory != null) {
			transactionFactory.close();
		}
	}
}
