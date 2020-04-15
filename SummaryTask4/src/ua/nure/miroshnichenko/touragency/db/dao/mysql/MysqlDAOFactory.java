package ua.nure.miroshnichenko.touragency.db.dao.mysql;

import java.util.HashMap;

import ua.nure.miroshnichenko.touragency.db.dao.CommentDAO;
import ua.nure.miroshnichenko.touragency.db.dao.DAOFactory;
import ua.nure.miroshnichenko.touragency.db.dao.FacilityDAO;
import ua.nure.miroshnichenko.touragency.db.dao.HotelDAO;
import ua.nure.miroshnichenko.touragency.db.dao.PlaceDAO;
import ua.nure.miroshnichenko.touragency.db.dao.ReservationDAO;
import ua.nure.miroshnichenko.touragency.db.dao.RouteDAO;
import ua.nure.miroshnichenko.touragency.db.dao.ServicingDAO;
import ua.nure.miroshnichenko.touragency.db.dao.TourDAO;
import ua.nure.miroshnichenko.touragency.db.dao.TransportDAO;
import ua.nure.miroshnichenko.touragency.db.dao.UserDAO;

public class MysqlDAOFactory extends DAOFactory {

	public MysqlDAOFactory() {
		DAOEntities = new HashMap<>();
		
		DAOEntities.put("tour", new MysqlTourDAO());
		DAOEntities.put("reservation", new MysqlReservationDAO());
		DAOEntities.put("user", new MysqlUserDAO());
		DAOEntities.put("hotel", new MysqlHotelDAO());
		DAOEntities.put("place", new MysqlPlaceDAO());
		DAOEntities.put("route", new MysqlRouteDAO());
		DAOEntities.put("servicing", new MysqlServicingDAO());
		DAOEntities.put("transport", new MysqlTransportDAO());
		DAOEntities.put("facility", new MysqlFacilityDAO());
	}
	
	@Override
	public ReservationDAO getReservationDAO() {
		return new MysqlReservationDAO();
	}

	@Override
	public UserDAO getUserDAO() {
		return new MysqlUserDAO();
	}

	@Override
	public TourDAO geTourDAO() {
		return new MysqlTourDAO();
	}
	
	@Override
	public HotelDAO getHotelDAO() {
		return new MysqlHotelDAO();
	}
	
	@Override
	public TransportDAO getTransportDAO() {
		return new MysqlTransportDAO();
	}
	
	@Override
	public PlaceDAO getPlaceDAO() {
		return new MysqlPlaceDAO();
	}
	
	@Override
	public ServicingDAO getServicingDAO() {
		return new MysqlServicingDAO();
	}
	
	@Override
	public FacilityDAO getFacilityDAO() {
		return new MysqlFacilityDAO();
	}
	
	@Override
	public RouteDAO getRouteDAO() {
		return new MysqlRouteDAO();
	}
	
	@Override
	public CommentDAO getCommentDAO() {
		return new MysqlCommentDAO();
	}
}
