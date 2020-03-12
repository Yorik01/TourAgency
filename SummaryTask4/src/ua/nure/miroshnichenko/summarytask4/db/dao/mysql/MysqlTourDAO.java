package ua.nure.miroshnichenko.summarytask4.db.dao.mysql;

import java.util.ArrayList;
import java.util.List;

import ua.nure.miroshnichenko.summarytask4.db.DBUtil;
import ua.nure.miroshnichenko.summarytask4.db.dao.DAO;
import ua.nure.miroshnichenko.summarytask4.db.dao.DAOException;
import ua.nure.miroshnichenko.summarytask4.db.entity.Hotel;
import ua.nure.miroshnichenko.summarytask4.db.entity.Tour;
import ua.nure.miroshnichenko.summarytask4.db.entity.Transport;
import ua.nure.miroshnichenko.summarytask4.myorm.core.transaction.Transaction;
import ua.nure.miroshnichenko.summarytask4.myorm.core.transaction.exception.TransactionException;
import ua.nure.miroshnichenko.summarytask4.myorm.core.transaction.exception.TransactionFactoryException;

public class MysqlTourDAO implements DAO<Tour> {

	private MysqlTransportDAO transportDAO = new MysqlTransportDAO();

	private MysqlHotelDAO hotelDAO = new MysqlHotelDAO();

	@Override
	public Tour find(int id) throws DAOException {
		Transaction transaction = null;
		Tour tour = null;

		try {
			transaction = DBUtil.getTransaction();
			tour = (Tour) transaction.findByPK(Tour.class, id);

			Hotel hotel = hotelDAO.find(tour.getHotelId());
			Transport transportTo = transportDAO.find(tour.getTransportToId());
			Transport transportBack = transportDAO.find(tour.getTransportBackId());

			tour.setHotel(hotel);
			tour.setTransportTo(transportTo);
			tour.setTransportBack(transportBack);

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
				Hotel hotel = hotelDAO.find(tour.getHotelId());
				Transport transportTo = transportDAO.find(tour.getTransportToId());
				Transport transportBack = transportDAO.find(tour.getTransportBackId());

				tour.setHotel(hotel);
				tour.setTransportTo(transportTo);
				tour.setTransportBack(transportBack);
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
}
