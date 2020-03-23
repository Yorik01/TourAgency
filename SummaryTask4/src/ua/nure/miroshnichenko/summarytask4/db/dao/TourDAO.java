package ua.nure.miroshnichenko.summarytask4.db.dao;

import java.util.List;
import java.util.Map;

import ua.nure.miroshnichenko.summarytask4.db.entity.Beach;
import ua.nure.miroshnichenko.summarytask4.db.entity.Facility;
import ua.nure.miroshnichenko.summarytask4.db.entity.Food;
import ua.nure.miroshnichenko.summarytask4.db.entity.HotelType;
import ua.nure.miroshnichenko.summarytask4.db.entity.Servicing;
import ua.nure.miroshnichenko.summarytask4.db.entity.Tour;
import ua.nure.miroshnichenko.summarytask4.db.entity.TourType;
import ua.nure.miroshnichenko.summarytask4.db.entity.TransportType;

public interface TourDAO extends DAO<Tour> {

	List<Tour> filter(Map<String, String> values, List<Servicing> servicings, List<Facility> facilities,
			List<HotelType> hotelTypes, List<Food> foods, List<Beach> beaches, List<TourType> tourTypes,
			List<TransportType> transportTypes, List<String> stars)
			throws DAOException;
}