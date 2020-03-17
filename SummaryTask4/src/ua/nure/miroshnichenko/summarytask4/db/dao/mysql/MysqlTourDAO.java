package ua.nure.miroshnichenko.summarytask4.db.dao.mysql;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import ua.nure.miroshnichenko.summarytask4.db.DBUtil;
import ua.nure.miroshnichenko.summarytask4.db.Queries;
import ua.nure.miroshnichenko.summarytask4.db.dao.DAOException;
import ua.nure.miroshnichenko.summarytask4.db.dao.TourDAO;
import ua.nure.miroshnichenko.summarytask4.db.entity.Beach;
import ua.nure.miroshnichenko.summarytask4.db.entity.Facility;
import ua.nure.miroshnichenko.summarytask4.db.entity.Food;
import ua.nure.miroshnichenko.summarytask4.db.entity.Hotel;
import ua.nure.miroshnichenko.summarytask4.db.entity.HotelType;
import ua.nure.miroshnichenko.summarytask4.db.entity.Servicing;
import ua.nure.miroshnichenko.summarytask4.db.entity.Tour;
import ua.nure.miroshnichenko.summarytask4.db.entity.TourType;
import ua.nure.miroshnichenko.summarytask4.db.entity.Transport;
import ua.nure.miroshnichenko.summarytask4.db.entity.TransportType;
import ua.nure.miroshnichenko.summarytask4.myorm.core.transaction.Transaction;
import ua.nure.miroshnichenko.summarytask4.myorm.core.transaction.exception.TransactionException;
import ua.nure.miroshnichenko.summarytask4.myorm.core.transaction.exception.TransactionFactoryException;

public class MysqlTourDAO implements TourDAO {

	private MysqlTransportDAO transportDAO = new MysqlTransportDAO();

	private MysqlHotelDAO hotelDAO = new MysqlHotelDAO();

	@Override
	public Tour find(int id) throws DAOException {
		Transaction transaction = null;
		Tour tour = null;

		try {
			transaction = DBUtil.getTransaction();
			tour = (Tour) transaction.findByPK(Tour.class, id);

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
			tours = (List<Tour>) (List<?>) transaction.findAll(Tour.class);

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
			List<TourType> tourTypes, List<TransportType> transportTypes)
			throws DAOException {
		
		Transaction transaction = null;
		List<Tour> tours = new ArrayList<>();

		try {
			transaction = DBUtil.getTransaction();
			
			String servicingsStr = servicings.toString();
			String facilitiesStr = facilities.toString();
			String hotelTypesStr = enumListToOrdinalString(hotelTypes);
			String foodsStr = enumListToOrdinalString(foods);
			String beachesStr = enumListToOrdinalString(beaches);
			String tourTypesStr = enumListToOrdinalString(tourTypes);
			String transportTypesStr = enumListToOrdinalString(transportTypes);
			
			final String query = prepareFilterQuery(
					servicingsStr, facilitiesStr, hotelTypesStr,
					foodsStr, beachesStr, tourTypesStr, transportTypesStr);
					
			tours = (List<Tour>) (List<?>) transaction.customQuery(
					query, Tour.class,
					values.get("startDate"),
					values.get("endDate"),
					values.get("toCountry"),
					values.get("toCity"),
					values.get("fromCountry"),
					values.get("fromCity")
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
		
		tour.setPrice(hotel.getPrice() + transportTo.getPrice() + transportBack.getPrice());
	}
	
	private String prepareFilterQuery(String servicingsStr, String facilitiesStr, 
			String hotelTypesStr, String foodsStr, String beachesStr, String tourTypesStr,
			String transportTypesStr) {
		
		return String.format(
				Queries.FILTER_TOUR,
				hotelTypesStr.substring(1, hotelTypesStr.length() - 1),
				foodsStr.substring(1, foodsStr.length() - 1),
				beachesStr.substring(1, beachesStr.length() - 1),
				facilitiesStr.substring(1, facilitiesStr.length() - 1),
				servicingsStr.substring(1, servicingsStr.length() - 1),
				tourTypesStr.substring(1, tourTypesStr.length() - 1),
				transportTypesStr.substring(1, transportTypesStr.length() - 1));
	}
	
	private String enumListToOrdinalString(List<? extends Enum> list) {
		StringBuilder result = new StringBuilder();

		Iterator<? extends Enum> iterator = list.iterator();
		while(true) {
			Enum en = iterator.next();
			result.append(en.ordinal());
			if(!iterator.hasNext()) {
				break;
			}
			result.append(',');
		}
		return result.toString();
	}
}
