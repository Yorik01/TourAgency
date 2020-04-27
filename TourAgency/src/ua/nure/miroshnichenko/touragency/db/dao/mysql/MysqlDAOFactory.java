package ua.nure.miroshnichenko.touragency.db.dao.mysql;

import ua.nure.miroshnichenko.touragency.db.dao.CommentDAO;
import ua.nure.miroshnichenko.touragency.db.dao.DAOFactory;
import ua.nure.miroshnichenko.touragency.db.dao.FacilityDAO;
import ua.nure.miroshnichenko.touragency.db.dao.HotelDAO;
import ua.nure.miroshnichenko.touragency.db.dao.PlaceDAO;
import ua.nure.miroshnichenko.touragency.db.dao.ReservationDAO;
import ua.nure.miroshnichenko.touragency.db.dao.RouteDAO;
import ua.nure.miroshnichenko.touragency.db.dao.ServicingDAO;
import ua.nure.miroshnichenko.touragency.db.dao.StatisticDAO;
import ua.nure.miroshnichenko.touragency.db.dao.TourDAO;
import ua.nure.miroshnichenko.touragency.db.dao.TransportDAO;
import ua.nure.miroshnichenko.touragency.db.dao.UserDAO;

public class MysqlDAOFactory extends DAOFactory {

	@Override
	public ReservationDAO getReservationDAO() {
		return MysqlReservationDAO.getInstance();
	}

	@Override
	public UserDAO getUserDAO() {
		return MysqlUserDAO.getInstance();
	}

	@Override
	public TourDAO geTourDAO() {
		return MysqlTourDAO.getInstance();
	}
	
	@Override
	public HotelDAO getHotelDAO() {
		return MysqlHotelDAO.getInstance();
	}
	
	@Override
	public TransportDAO getTransportDAO() {
		return MysqlTransportDAO.getInstance();
	}
	
	@Override
	public PlaceDAO getPlaceDAO() {
		return MysqlPlaceDAO.getInstance();
	}
	
	@Override
	public ServicingDAO getServicingDAO() {
		return MysqlServicingDAO.getInstance();
	}
	
	@Override
	public FacilityDAO getFacilityDAO() {
		return MysqlFacilityDAO.getInstance();
	}
	
	@Override
	public RouteDAO getRouteDAO() {
		return MysqlRouteDAO.getInstance();
	}
	
	@Override
	public CommentDAO getCommentDAO() {
		return MysqlCommentDAO.getInstance();
	}
	
	@Override
	public StatisticDAO getStatisticDAO() {
		return MysqlStatisticDAO.getInstance();
	}
}
