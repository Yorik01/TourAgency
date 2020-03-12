package ua.nure.miroshnichenko.summarytask4.db;

import ua.nure.miroshnichenko.summarytask4.myorm.core.Settings;
import ua.nure.miroshnichenko.summarytask4.myorm.core.transaction.Transaction;
import ua.nure.miroshnichenko.summarytask4.myorm.core.transaction.TransactionFactory;
import ua.nure.miroshnichenko.summarytask4.myorm.core.transaction.exception.TransactionException;
import ua.nure.miroshnichenko.summarytask4.myorm.core.transaction.exception.TransactionFactoryException;

public final class DBUtil {

	private static final String URL = "jdbc:mysql://localhost:3306/tour_agency";

	private static final String USER_NAME = "epam_practice";

	private static final String PASSWORD = "1234";

	private static Settings settings;

	private static TransactionFactory transactionFactory;

	static {
		settings = new Settings(URL, USER_NAME, PASSWORD);
		try {
			transactionFactory = settings.getTransactionFactory();
		} catch (TransactionFactoryException e) {
			e.printStackTrace();
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

	public static void close(Transaction transaction) throws TransactionException {
		if (transaction != null) {
			transaction.close();
		}
	}

	public static void closeConnection() throws TransactionFactoryException {
		transactionFactory.close();
	}
}
