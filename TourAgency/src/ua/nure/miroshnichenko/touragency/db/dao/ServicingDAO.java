package ua.nure.miroshnichenko.touragency.db.dao;

import ua.nure.miroshnichenko.touragency.db.entity.Servicing;

public interface ServicingDAO extends DAO<Servicing> {

	Servicing getServicingByName(String name) throws DAOException;
}
