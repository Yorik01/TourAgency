package ua.nure.miroshnichenko.touragency.db.dao.mysql;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.miroshnichenko.touragency.db.DBUtil;
import ua.nure.miroshnichenko.touragency.db.Queries;
import ua.nure.miroshnichenko.touragency.db.dao.DAOException;
import ua.nure.miroshnichenko.touragency.db.dao.RouteDAO;
import ua.nure.miroshnichenko.touragency.db.entity.Place;
import ua.nure.miroshnichenko.touragency.db.entity.Route;
import ua.nure.miroshnichenko.myorm.core.transaction.Transaction;
import ua.nure.miroshnichenko.myorm.core.transaction.exception.TransactionException;
import ua.nure.miroshnichenko.myorm.core.transaction.exception.TransactionFactoryException;

public class MysqlRouteDAO implements RouteDAO {

	private MysqlPlaceDAO placeDao = new MysqlPlaceDAO();

	private final Logger LOG = Logger.getLogger(MysqlRouteDAO.class);
	
	@Override
	public Route find(int id) throws DAOException {
		Transaction transaction = null;
		Route route = null;

		try {
			transaction = DBUtil.getTransaction();
			route = transaction.findByPK(Route.class, id);

			Place placeTo = placeDao.find(route.getRouteToId());
			Place placeFrom = placeDao.find(route.getRouteFromId());

			route.setTo(placeTo);
			route.setFrom(placeFrom);
		} catch (TransactionFactoryException | TransactionException e) {
			LOG.error(e);
			throw new DAOException(e);
		} finally {
			try {
				DBUtil.close(transaction);
			} catch (TransactionException e) {
				LOG.error(e);
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
			routes = transaction.findAll(Route.class);

			for (Route route : routes) {
				Place placeTo = placeDao.find(route.getRouteToId());
				Place placeFrom = placeDao.find(route.getRouteFromId());

				route.setTo(placeTo);
				route.setFrom(placeFrom);
			}
		} catch (TransactionFactoryException | TransactionException e) {
			LOG.error(e);
			throw new DAOException(e);
		} finally {
			try {
				DBUtil.close(transaction);
			} catch (TransactionException e) {
				LOG.error(e);
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
			LOG.error(e);
			throw new DAOException(e);
		} finally {
			try {
				DBUtil.close(transaction);
			} catch (TransactionException e) {
				LOG.error(e);
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
			LOG.error(e);
			throw new DAOException(e);
		} finally {
			try {
				DBUtil.close(transaction);
			} catch (TransactionException e) {
				LOG.error(e);
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
			LOG.error(e);
			throw new DAOException(e);
		} finally {
			try {
				DBUtil.close(transaction);
			} catch (TransactionException e) {
				LOG.error(e);
				throw new DAOException(e);
			}
		}
		return result;
	}
	
	@Override
	public Route getRouteByPlaces(int placeFromId, int placeToId) throws DAOException {
		Transaction transaction = null;
		Route route = null;

		try {
			transaction = DBUtil.getTransaction();
			List<Route> routes = transaction.customQuery(
					Queries.ROUTE_BY_PLACES, 
					Route.class,
					placeFromId,
					placeToId);
			
			if (!routes.isEmpty()) {
				route = routes.get(0);
				
				Place placeTo = placeDao.find(route.getRouteToId());
				Place placeFrom = placeDao.find(route.getRouteFromId());

				route.setTo(placeTo);
				route.setFrom(placeFrom);
			}
		} catch (TransactionFactoryException | TransactionException e) {
			LOG.error(e);
			throw new DAOException(e);
		} finally {
			try {
				DBUtil.close(transaction);
			} catch (TransactionException e) {
				LOG.error(e);
				throw new DAOException(e);
			}
		}
		return route;
	}
}
