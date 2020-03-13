package ua.nure.miroshnichenko.summarytask4.db.dao.mysql;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import sun.net.www.content.audio.x_aiff;
import ua.nure.miroshnichenko.summarytask4.db.DBUtil;
import ua.nure.miroshnichenko.summarytask4.db.Queries;
import ua.nure.miroshnichenko.summarytask4.db.dao.DAO;
import ua.nure.miroshnichenko.summarytask4.db.dao.DAOException;
import ua.nure.miroshnichenko.summarytask4.db.entity.Facility;
import ua.nure.miroshnichenko.summarytask4.db.entity.Hotel;
import ua.nure.miroshnichenko.summarytask4.db.entity.Servicing;
import ua.nure.miroshnichenko.summarytask4.db.entity.TypeServicing;
import ua.nure.miroshnichenko.summarytask4.myorm.core.transaction.Transaction;
import ua.nure.miroshnichenko.summarytask4.myorm.core.transaction.exception.TransactionException;
import ua.nure.miroshnichenko.summarytask4.myorm.core.transaction.exception.TransactionFactoryException;

public class MysqlHotelDAO implements DAO<Hotel> {

	private Object object;

	@Override
	public Hotel find(int id) throws DAOException {
		Transaction transaction = null;
		Hotel hotel = null;

		try {
			transaction = DBUtil.getTransaction();
			hotel = (Hotel) transaction.findByPK(Hotel.class, id);

			List<Servicing> servicings = (List<Servicing>)(List<?>)transaction.customQuery(
					Queries.HOTEL_SERVICES, Servicing.class, hotel.getId().toString());
			List<Facility> facilities = (List<Facility>)(List<?>)transaction.customQuery(
					Queries.HOTEL_FACILITIES, Facility.class, hotel.getId().toString());

			Map<TypeServicing, Set<Servicing>> servicingsMap = new HashMap<>();
			for(TypeServicing type : TypeServicing.values()) {
				Set<Servicing> servicingSet = servicings.stream().filter(
						s -> s.getType().equals(type)).collect(Collectors.toSet());
				
				servicingsMap.put(type, servicingSet);
			}
			
			hotel.setFacilities(new HashSet<>(facilities));
			hotel.setServicings(servicingsMap);

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
		return hotel;
	}

	@Override
	public List<Hotel> findAll() throws DAOException {
		Transaction transaction = null;
		List<Hotel> hotels = new ArrayList<>();

		try {
			transaction = DBUtil.getTransaction();
			hotels = (List<Hotel>) (List<?>) transaction.findAll(Hotel.class);

			for (Hotel hotel : hotels) {
				List<Servicing> servicings = (List<Servicing>)(List<?>)transaction.customQuery(
						Queries.HOTEL_SERVICES, Servicing.class, hotel.getId().toString());
				List<Facility> facilities = (List<Facility>)(List<?>)transaction.customQuery(
						Queries.HOTEL_FACILITIES, Facility.class, hotel.getId().toString());

				Map<TypeServicing, Set<Servicing>> servicingsMap = new HashMap<>();
				for(TypeServicing type : TypeServicing.values()) {
					Set<Servicing> servicingSet = servicings.stream().filter(
							s -> s.getType().equals(type)).collect(Collectors.toSet());
					
					servicingsMap.put(type, servicingSet);
				}
				
				hotel.setFacilities(new HashSet<>(facilities));
				hotel.setServicings(servicingsMap);
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
		return hotels;
	}

	@Override
	public boolean save(Hotel entity) throws DAOException {
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
	public boolean update(Hotel entity) throws DAOException {
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
	public boolean delete(Hotel entity) throws DAOException {
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
}
