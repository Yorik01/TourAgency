package ua.nure.miroshnichenko.summarytask4.db.dao;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import ua.nure.miroshnichenko.summarytask4.AppPropertiesConstants;

public abstract class DAOFactory {

	private static DAOFactory instance;

	protected Map<String, DAO<?>> DAOEntities;

	public static synchronized DAOFactory getInstance() {
		if (instance == null) {
			Properties props = new Properties();
			try {
				props.load(new FileInputStream(AppPropertiesConstants.APP_PROPERTIES_FILE));
				String className = props.getProperty(AppPropertiesConstants.DAO_FACORY_PROPERTY);
				instance = (DAOFactory) Class.forName(className).newInstance();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return instance;
	}

	protected DAOFactory() {
		DAOEntities = new HashMap<>();
		//TODO
		
	}

	public DAO<?> getDAO(String name) {
		return DAOEntities.get(name);
	}
	
	public abstract ReservationDAO getReservationDAO();
	
	public abstract UserDAO getUserDAO();
}
