package ua.nure.miroshnichenko.summarytask4.db.dao.mysql;

import java.util.ArrayList;
import java.util.List;

import ua.nure.miroshnichenko.summarytask4.db.DBUtil;
import ua.nure.miroshnichenko.summarytask4.db.dao.DAO;
import ua.nure.miroshnichenko.summarytask4.db.dao.DAOException;
import ua.nure.miroshnichenko.summarytask4.db.entity.Place;
import ua.nure.miroshnichenko.summarytask4.db.entity.Route;
import ua.nure.miroshnichenko.summarytask4.db.entity.Route;
import ua.nure.miroshnichenko.summarytask4.myorm.core.transaction.Transaction;
import ua.nure.miroshnichenko.summarytask4.myorm.core.transaction.exception.TransactionException;
import ua.nure.miroshnichenko.summarytask4.myorm.core.transaction.exception.TransactionFactoryException;

public class MysqlRouteDAO implements DAO<Route> {

	private MysqlPlaceDAO placeDao = new MysqlPlaceDAO();

	@Override
	public Route find(int id) throws DAOException {
		Transaction transaction = null;
		Route route = null;

		try {
			transaction = DBUtil.getTransaction();
			route = (Route) transaction.findByPK(Route.class, id);

			Place placeTo = placeDao.find(route.getRouteToId());
			Place placeFrom = placeDao.find(route.getRouteFromId());

			route.setTo(placeTo);
			route.setFrom(placeFrom);
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
		return route;
	}

	@Override
	public List<Route> findAll() throws DAOException {
		Transaction transaction = null;
		List<Route> routes = new ArrayList<>();

		try {
			transaction = DBUtil.getTransaction();
			routes = (List<Route>) (List<?>) transaction.findAll(Route.class);

			for (Route route : routes) {
				Place placeTo = placeDao.find(route.getRouteToId());
				Place placeFrom = placeDao.find(route.getRouteFromId());

				route.setTo(placeTo);
				route.setFrom(placeFrom);
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
		return routes;
	}

	@Override
	public boolean save(Route entity) throws DAOException {
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
	public boolean update(Route entity) throws DAOException {
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
	public boolean delete(Route entity) throws DAOException {
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
