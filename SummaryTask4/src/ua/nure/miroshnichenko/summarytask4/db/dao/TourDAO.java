package ua.nure.miroshnichenko.summarytask4.db.dao;

import java.util.List;
import java.util.Map;

import ua.nure.miroshnichenko.summarytask4.db.entity.Servicing;
import ua.nure.miroshnichenko.summarytask4.db.entity.Tour;

public interface TourDAO extends DAO<Tour> {
	
	List<Tour> filter(Map<String, String> values, List<Servicing> servicings) throws DAOException;
}
