package ua.nure.miroshnichenko.touragency.db.dao.mysql;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ua.nure.miroshnichenko.touragency.Util;
import ua.nure.miroshnichenko.touragency.db.DBUtil;
import ua.nure.miroshnichenko.touragency.db.Queries;
import ua.nure.miroshnichenko.touragency.db.dao.DAOException;
import ua.nure.miroshnichenko.touragency.db.dao.TourDAO;
import ua.nure.miroshnichenko.touragency.db.entity.Beach;
import ua.nure.miroshnichenko.touragency.db.entity.Facility;
import ua.nure.miroshnichenko.touragency.db.entity.Food;
import ua.nure.miroshnichenko.touragency.db.entity.Hotel;
import ua.nure.miroshnichenko.touragency.db.entity.HotelType;
import ua.nure.miroshnichenko.touragency.db.entity.Servicing;
import ua.nure.miroshnichenko.touragency.db.entity.Tour;
import ua.nure.miroshnichenko.touragency.db.entity.TourType;
import ua.nure.miroshnichenko.touragency.db.entity.Transport;
import ua.nure.miroshnichenko.touragency.db.entity.TransportType;
import ua.nure.miroshnichenko.myorm.core.transaction.Transaction;
import ua.nure.miroshnichenko.myorm.core.transaction.exception.TransactionException;
import ua.nure.miroshnichenko.myorm.core.transaction.exception.TransactionFactoryException;

public class MysqlTourDAO implements TourDAO {

	private MysqlTransportDAO transportDAO = new MysqlTransportDAO();

	private MysqlHotelDAO hotelDAO = new MysqlHotelDAO();

	@Override
	public Tour find(int id) throws DAOException {
		Transaction transaction = null;
		Tour tour = null;

		try {
			transaction = DBUtil.getTransaction();
			tour = transaction.findByPK(Tour.class, id);

			initTour(tour);

		} catch (TransactionFactoryException | TransactionException e) {
			e.printStackTrace();
			throw new DAOException(e);
		} finally {
			try {
				DBUtil.close(transaction);
			} catch (TransactionException e) {
				e.printStackTrace();
				throw new DAOException(e);
			}
		}
		return tour;
	}

	@Override
	public List<Tour> findAll() throws DAOException {
		Transaction transaction = null;
		List<Tour> tours = new ArrayList<>();

		try {
			transaction = DBUtil.getTransaction();
			tours = transaction.findAll(Tour.class);

			for (Tour tour : tours) {
				initTour(tour);
			}

		} catch (TransactionFactoryException | TransactionException e) {
			e.printStackTrace();
			throw new DAOException(e);
		} finally {
			try {
				DBUtil.close(transaction);
			} catch (TransactionException e) {
				e.printStackTrace();
				throw new DAOException(e);
			}
		}
		return tours;
	}

	@Override
	public boolean save(Tour entity) throws DAOException {
		Transaction transaction = null;
		boolean result = false;

		try {
			transaction = DBUtil.getTransaction();
			result = transaction.insert(entity);
			transaction.commit();

		} catch (TransactionFactoryException | TransactionException e) {
			e.printStackTrace();
			throw new DAOException(e);
		} finally {
			try {
				DBUtil.close(transaction);
			} catch (TransactionException e) {
				e.printStackTrace();
				throw new DAOException(e);
			}
		}
		return result;
	}

	@Override
	public boolean update(Tour entity) throws DAOException {
		Transaction transaction = null;
		boolean result = false;

		try {
			transaction = DBUtil.getTransaction();
			result = transaction.update(entity);
			transaction.commit();

		} catch (TransactionFactoryException | TransactionException e) {
			e.printStackTrace();
			throw new DAOException(e);
		} finally {
			try {
				DBUtil.close(transaction);
			} catch (TransactionException e) {
				e.printStackTrace();
				throw new DAOException(e);
			}
		}
		return result;
	}

	@Override
	public boolean delete(Tour entity) throws DAOException {
		Transaction transaction = null;
		boolean result = false;

		try {
			transaction = DBUtil.getTransaction();
			result = transaction.delete(entity);
			transaction.commit();
		} catch (TransactionFactoryException | TransactionException e) {
			e.printStackTrace();
			throw new DAOException(e);
		} finally {
			try {
				DBUtil.close(transaction);
			} catch (TransactionException e) {
				e.printStackTrace();
				throw new DAOException(e);
			}
		}
		return result;
	}
	
	@Override
	public List<Tour> filter(Map<String, String> values, 
			List<Servicing> servicings, List<Facility> facilities,
			List<HotelType> hotelTypes, List<Food> foods, List<Beach> beaches,
			List<TourType> tourTypes, List<TransportType> transportTypes,
			List<String> stars)
			throws DAOException {
		
		Transaction transaction = null;
		List<Tour> tours = new ArrayList<>();

		try {
			transaction = DBUtil.getTransaction();
			
			String servicingsStr;
			if (servicings.size() > 0) {
				servicingsStr = Util.listToString(servicings);
			} else {
				servicingsStr = Queries.ALL_SERVICINGS;
			}
			
			String facilitiesStr;
			if (facilities.size() > 0) {
				facilitiesStr = Util.listToString(facilities);
			} else {
				facilitiesStr = Queries.ALL_FACILITIES;
			}
			
			String starsStr;
			if (stars.size() > 0) {
				starsStr = Util.listToString(stars);
			} else {
				starsStr = "1, 2, 3, 4, 5";
			}
			
			String hotelTypesStr;
			if (hotelTypes.size() > 0) {
				hotelTypesStr = Util.enumListToOrdinalString(hotelTypes);
			} else {
				hotelTypesStr = Queries.ALL_HOTEL_TYPES;
			}
			
			String foodsStr;
			if (foods.size() > 0) {
				foodsStr = Util.enumListToOrdinalString(foods);
			} else {
				foodsStr = Queries.ALL_FOODS;
			}
			
			String beachesStr;
			if (beaches.size() > 0) {
				beachesStr = Util.enumListToOrdinalString(beaches);
			} else {
				beachesStr = Queries.ALL_BEACHES;
			}
			
			String tourTypesStr;
			if (tourTypes.size() > 0) {
				tourTypesStr = Util.enumListToOrdinalString(tourTypes);
			} else {
				tourTypesStr = Queries.ALL_TOUR_TYPES;
			}
			
			String transportTypesStr;
			if (transportTypes.size() > 0) {
				transportTypesStr = Util.enumListToOrdinalString(transportTypes);
			} else {
				transportTypesStr = Queries.ALL_TRANSPORT_TYPES;
			}
			
			final String query = prepareFilterQuery(
					servicingsStr, facilitiesStr, hotelTypesStr,
					foodsStr, beachesStr, tourTypesStr, transportTypesStr, starsStr);
			
			String peopleCount = values.get("peopleCount");
			
			tours = transaction.customQuery(
					query, Tour.class,
					values.get("startDate"),
					values.get("endDate"),
					values.get("toCountry"),
					values.get("toCity"),
					values.get("fromCity"),
					values.get("maxPrice"),
					peopleCount,
					peopleCount
					);
			
			for (Tour tour : tours) {
				initTour(tour);
			}

		} catch (TransactionFactoryException | TransactionException e) {
			e.printStackTrace();
			throw new DAOException(e);
		} finally {
			try {
				DBUtil.close(transaction);
			} catch (TransactionException e) {
				e.printStackTrace();
				throw new DAOException(e);
			}
		}
		return tours;
	}
	
	private void initTour(Tour tour) throws DAOException {
		Hotel hotel = hotelDAO.find(tour.getHotelId());
		Transport transportTo = transportDAO.find(tour.getTransportToId());
		Transport transportBack = transportDAO.find(tour.getTransportBackId());

		tour.setHotel(hotel);
		tour.setTransportTo(transportTo);
		tour.setTransportBack(transportBack);
		
		double total = hotel.getPrice() + transportTo.getPrice() + transportBack.getPrice();
		
		tour.setPrice(total  - (total * (tour.getAgencyProcent() / 100d)));
	}
	
	private String prepareFilterQuery(String servicingsStr, String facilitiesStr, 
			String hotelTypesStr, String foodsStr, String beachesStr, String tourTypesStr,
			String transportTypesStr, String starsStr) {

		return String.format(
				Queries.FILTER_TOUR,
				hotelTypesStr,
				foodsStr,
				beachesStr,
				facilitiesStr,
				servicingsStr,
				tourTypesStr,
				transportTypesStr,
				starsStr);
	}	
}
