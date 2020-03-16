package ua.nure.miroshnichenko.summarytask4.db.dao;

import ua.nure.miroshnichenko.summarytask4.db.entity.Servicing;

public interface ServicingDAO extends DAO<Servicing> {

	Servicing getServicingByName(String name) throws DAOException;
}
