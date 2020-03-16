package ua.nure.miroshnichenko.summarytask4.db.dao;

import ua.nure.miroshnichenko.summarytask4.db.entity.Facility;

public interface FacilityDAO extends DAO<Facility> {

	Facility getFacilityByName(String name) throws DAOException;
}
