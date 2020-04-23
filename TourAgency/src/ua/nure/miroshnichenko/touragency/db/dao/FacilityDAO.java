package ua.nure.miroshnichenko.touragency.db.dao;

import ua.nure.miroshnichenko.touragency.db.entity.Facility;

public interface FacilityDAO extends DAO<Facility> {

	Facility getFacilityByName(String name) throws DAOException;
}
