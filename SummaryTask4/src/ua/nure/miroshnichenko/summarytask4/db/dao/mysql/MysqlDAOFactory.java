package ua.nure.miroshnichenko.summarytask4.db.dao.mysql;

import ua.nure.miroshnichenko.summarytask4.db.dao.DAOFactory;
import ua.nure.miroshnichenko.summarytask4.db.dao.ReservationDAO;
import ua.nure.miroshnichenko.summarytask4.db.dao.UserDAO;

public class MysqlDAOFactory extends DAOFactory {

	@Override
	public ReservationDAO getReservationDAO() {
		return new MysqlReservationDAO();
	}
	
	@Override
	public UserDAO getUserDAO() {
		return new MysqlUserDAO();
	}
}
