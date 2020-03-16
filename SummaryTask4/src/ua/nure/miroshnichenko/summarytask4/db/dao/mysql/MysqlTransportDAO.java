package ua.nure.miroshnichenko.summarytask4.db.dao.mysql;

import java.util.ArrayList;
import java.util.List;

import ua.nure.miroshnichenko.summarytask4.db.DBUtil;
import ua.nure.miroshnichenko.summarytask4.db.Queries;
import ua.nure.miroshnichenko.summarytask4.db.dao.DAO;
import ua.nure.miroshnichenko.summarytask4.db.dao.DAOException;
import ua.nure.miroshnichenko.summarytask4.db.dao.TransportDAO;
import ua.nure.miroshnichenko.summarytask4.db.entity.Route;
import ua.nure.miroshnichenko.summarytask4.db.entity.Transport;
import ua.nure.miroshnichenko.summarytask4.db.entity.TransportType;
import ua.nure.miroshnichenko.summarytask4.myorm.core.transaction.Transaction;
import ua.nure.miroshnichenko.summarytask4.myorm.core.transaction.exception.TransactionException;
import ua.nure.miroshnichenko.summarytask4.myorm.core.transaction.exception.TransactionFactoryException;

public class MysqlTransportDAO implements TransportDAO {

	private MysqlRouteDAO routeDAO = new MysqlRouteDAO();

	@Override
	public Transport find(int id) throws DAOException {
		Transaction transaction = null;
		Transport transport = null;

		try {
			transaction = DBUtil.getTransaction();
			transport = (Transport) transaction.findByPK(Transport.class, id);

			Route route = routeDAO.find(transport.getRouteId());
			transport.setRoute(route);
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
		return transport;
	}

	@Override
	public List<Transport> findAll() throws DAOException {
		Transaction transaction = null;
		List<Transport> transports = new ArrayList<>();

		try {
			transaction = DBUtil.getTransaction();
			transports = (List<Transport>) (List<?>) transaction.findAll(Transport.class);

			for (Transport transport : transports) {
				Route route = routeDAO.find(transport.getRouteId());
				transport.setRoute(route);
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
		return transports;
	}

	@Override
	public boolean save(Transport entity) throws DAOException {
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
	public boolean update(Transport entity) throws DAOException {
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
	public boolean delete(Transport entity) throws DAOException {
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
	public Transport geTransportByCodeAndType(String code, TransportType type)
			throws DAOException {
		
		Transaction transaction = null;
		Transport transport = null;

		try {
			transaction = DBUtil.getTransaction();
			transport = (Transport) transaction.customQuery(
					Queries.TRANSPORT_BY_CODE_AND_TYPE, Transport.class, code, type.ordinal()).get(0);

			Route route = routeDAO.find(transport.getRouteId());
			transport.setRoute(route);
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
		return transport;
	}
}
