package ua.nure.miroshnichenko.myorm.core;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.util.Properties;

import ua.nure.miroshnichenko.myorm.core.transaction.Transaction;
import ua.nure.miroshnichenko.myorm.core.transaction.TransactionFactory;
import ua.nure.miroshnichenko.myorm.core.transaction.exception.TransactionFactoryException;

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

	public Settings(){ 
		try (InputStream inputStream = new FileInputStream("app.properties")) {
			properties = new Properties();
			properties.load(inputStream);
			
			url = properties.getProperty("db.url");
		} catch (IOException e) {
			e.printStackTrace();
		}
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
