package ua.nure.miroshnichenko.summarytask4.db.dao.mysql;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import ua.nure.miroshnichenko.summarytask4.db.DBUtil;
import ua.nure.miroshnichenko.summarytask4.db.Queries;
import ua.nure.miroshnichenko.summarytask4.db.dao.DAO;
import ua.nure.miroshnichenko.summarytask4.db.dao.DAOException;
import ua.nure.miroshnichenko.summarytask4.db.entity.Facility;
import ua.nure.miroshnichenko.summarytask4.db.entity.Hotel;
import ua.nure.miroshnichenko.summarytask4.db.entity.Servicing;
import ua.nure.miroshnichenko.summarytask4.myorm.core.transaction.Transaction;
import ua.nure.miroshnichenko.summarytask4.myorm.core.transaction.exception.TransactionException;
import ua.nure.miroshnichenko.summarytask4.myorm.core.transaction.exception.TransactionFactoryException;

public class MysqlHotelDAO implements DAO<Hotel> {

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

			hotel.setFacilities(new HashSet<>(facilities));
			hotel.setServicings(new HashSet<>(servicings));

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

				hotel.setFacilities(new HashSet<>(facilities));
				hotel.setServicings(new HashSet<>(servicings));
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