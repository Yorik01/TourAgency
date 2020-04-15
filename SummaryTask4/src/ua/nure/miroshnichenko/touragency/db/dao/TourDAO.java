package ua.nure.miroshnichenko.touragency.db.dao;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import ua.nure.miroshnichenko.touragency.db.entity.Beach;
import ua.nure.miroshnichenko.touragency.db.entity.Facility;
import ua.nure.miroshnichenko.touragency.db.entity.Food;
import ua.nure.miroshnichenko.touragency.db.entity.HotelType;
import ua.nure.miroshnichenko.touragency.db.entity.Servicing;
import ua.nure.miroshnichenko.touragency.db.entity.Tour;
import ua.nure.miroshnichenko.touragency.db.entity.TourType;
import ua.nure.miroshnichenko.touragency.db.entity.TransportType;

public interface TourDAO extends DAO<Tour> {

	List<Tour> filter(Map<String, String> values, List<Servicing> servicings, List<Facility> facilities,
			List<HotelType> hotelTypes, List<Food> foods, List<Beach> beaches, List<TourType> tourTypes,
			List<TransportType> transportTypes, List<String> stars) throws DAOException;

	Tour getTourByDateAndHotelId(Date startDate, Date endDate, int hotelId) 
			throws DAOException;
}