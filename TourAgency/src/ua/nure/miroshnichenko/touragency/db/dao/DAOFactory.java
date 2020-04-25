package ua.nure.miroshnichenko.touragency.db.dao;

import java.util.Properties;

import ua.nure.miroshnichenko.touragency.db.dao.mysql.MysqlDAOFactory;

public abstract class DAOFactory {

	private static DAOFactory instance;

	public static synchronized DAOFactory getInstance() {
		if (instance == null) {
			Properties props = new Properties();
			try {
				props.load(DAOFactory.class.getClassLoader().getResourceAsStream("app.properties"));
				String className = props.getProperty("dao.factory.fqn");
				
				instance = (DAOFactory) Class.forName(className).newInstance();
				instance = new MysqlDAOFactory();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return instance;
	}

	protected DAOFactory() {
	}

	public abstract ReservationDAO getReservationDAO();
	
	public abstract UserDAO getUserDAO();
	
	public abstract TourDAO geTourDAO();
	
	public abstract HotelDAO getHotelDAO();
	
	public abstract TransportDAO getTransportDAO();
	
	public abstract PlaceDAO getPlaceDAO();
	
	public abstract ServicingDAO getServicingDAO();
	
	public abstract FacilityDAO getFacilityDAO();
	
	public abstract RouteDAO getRouteDAO();
	
	public abstract CommentDAO getCommentDAO();
	
	public abstract StatisticDAO getStatisticDAO();
}
