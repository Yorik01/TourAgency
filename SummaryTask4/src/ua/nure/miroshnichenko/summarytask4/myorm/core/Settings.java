package ua.nure.miroshnichenko.summarytask4.myorm.core;

import java.sql.DriverManager;
import java.util.Properties;

import ua.nure.miroshnichenko.summarytask4.myorm.core.transaction.Transaction;
import ua.nure.miroshnichenko.summarytask4.myorm.core.transaction.TransactionFactory;
import ua.nure.miroshnichenko.summarytask4.myorm.core.transaction.exception.TransactionFactoryException;

/**
 * Read all configurations and create
 * {@link #ua.myorm.core.transaction.TransactionFactory} for working with a
 * database.
 * 
 * @author Miroshnichenko Y. D
 */
public class Settings {

	private String url;
	private Properties properties;

	private TransactionFactory transactionFactory;

	public Settings(String url, String user, String password) {
		this.url = url;

		properties = new Properties();
		properties.setProperty("user", user);
		properties.setProperty("password", password);
	}

	public Settings() {
		// TODO read properties from xml
	}

	public String getUrl() {
		return url;
	}

	public Properties getProperties() {
		return properties;
	}

	/**
	 * Return {@link #ua.myorm.core.transaction.TransactionFactory} according to the
	 * current settings.
	 */
	public synchronized TransactionFactory getTransactionFactory() throws TransactionFactoryException {
		if (transactionFactory == null) {
			transactionFactory = new TransactionFactoryImpl(this);
		}
		return transactionFactory;
	}

	public ConnectionsPool getConnectionsPool() throws ConnectionsPoolException {
		return new ConnectionsPoolImpl(url, properties);
	}
}
