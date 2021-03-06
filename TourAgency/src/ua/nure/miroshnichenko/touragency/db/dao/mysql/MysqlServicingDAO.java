package ua.nure.miroshnichenko.touragency.db.dao.mysql;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.miroshnichenko.touragency.db.DBUtil;
import ua.nure.miroshnichenko.touragency.db.Queries;
import ua.nure.miroshnichenko.touragency.db.dao.DAOException;
import ua.nure.miroshnichenko.touragency.db.dao.ServicingDAO;
import ua.nure.miroshnichenko.touragency.db.entity.Servicing;
import ua.nure.miroshnichenko.myorm.core.transaction.Transaction;
import ua.nure.miroshnichenko.myorm.core.transaction.exception.TransactionException;
import ua.nure.miroshnichenko.myorm.core.transaction.exception.TransactionFactoryException;

public class MysqlServicingDAO implements ServicingDAO {

	private final Logger LOG = Logger.getLogger(MysqlServicingDAO.class);
	
	private static MysqlServicingDAO instance;
	
	private MysqlServicingDAO() {
	}
	
	public static synchronized MysqlServicingDAO getInstance() {
		if (instance == null) {
			instance = new MysqlServicingDAO();
		}
		return instance;
	}
	
	@Override
	public Servicing find(int id) throws DAOException {
		Transaction transaction = null;
		Servicing servicing = null;

		try {
			transaction = DBUtil.getTransaction();
			servicing = transaction.findByPK(Servicing.class, id);
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
		return servicing;
	}

	@Override
	public List<Servicing> findAll() throws DAOException {
		Transaction transaction = null;
		List<Servicing> servicings = new ArrayList<>();

		try {
			transaction = DBUtil.getTransaction();
			servicings = transaction.findAll(Servicing.class);
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
		return servicings;
	}

	@Override
	public boolean save(Servicing entity) throws DAOException {
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
	public boolean update(Servicing entity) throws DAOException {
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
	public boolean delete(Servicing entity) throws DAOException {
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
	public Servicing getServicingByName(String name) throws DAOException {
		Transaction transaction = null;
		Servicing servicing = null;

		try {
			transaction = DBUtil.getTransaction();
			List<Servicing> res = transaction.customQuery(
					Queries.SERVICING_BY_NAME, Servicing.class, name);
			if (!res.isEmpty()) {
				servicing = res.get(0);
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
		return servicing;
	}
}
