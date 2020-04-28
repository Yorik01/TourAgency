package ua.nure.miroshnichenko.myorm.core;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import ua.nure.miroshnichenko.myorm.core.pool.ConnectionsPool;
import ua.nure.miroshnichenko.myorm.core.pool.exception.ConnectionsPoolException;
import ua.nure.miroshnichenko.myorm.core.transaction.TransactionFactory;
import ua.nure.miroshnichenko.myorm.core.transaction.exception.TransactionFactoryException;

/**
 * Read all configurations and create
 * {@link ua.myorm.core.transaction.TransactionFactory} for working with a
 * database.
 * 
 * @author Miroshnichenko Y. D
 */
public class Settings {

	private String url;
	private Properties properties;

	private TransactionFactory transactionFactory;

	// load all settings from app.properties
	public Settings() throws IOException, ClassNotFoundException { 
		try (InputStream inputStream = Settings.class.
				getClassLoader().
				getResourceAsStream("app.properties")) {
			properties = new Properties();
			properties.load(inputStream);
			
			url = properties.getProperty("db.url");
			
			Class.forName(properties.getProperty("db.driver"));
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			throw e;
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
