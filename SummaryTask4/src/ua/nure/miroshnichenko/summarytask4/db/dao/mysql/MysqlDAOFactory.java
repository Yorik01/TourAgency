package ua.nure.miroshnichenko.summarytask4.db.dao.mysql;

import java.util.HashMap;

import ua.nure.miroshnichenko.summarytask4.db.dao.DAOFactory;
import ua.nure.miroshnichenko.summarytask4.db.dao.HotelDAO;
import ua.nure.miroshnichenko.summarytask4.db.dao.ReservationDAO;
import ua.nure.miroshnichenko.summarytask4.db.dao.TourDAO;
import ua.nure.miroshnichenko.summarytask4.db.dao.TransportDAO;
import ua.nure.miroshnichenko.summarytask4.db.dao.UserDAO;
import ua.nure.miroshnichenko.summarytask4.db.entity.Beach;

public class MysqlDAOFactory extends DAOFactory {

	public MysqlDAOFactory() {
		DAOEntities = new HashMap<>();
		
		DAOEntities.put("tour", new MysqlTourDAO());
		DAOEntities.put("reservation", new MysqlReservationDAO());
		DAOEntities.put("user", new MysqlUserDAO());
		DAOEntities.put("bonus", new MysqlBonusDAO());
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
}
