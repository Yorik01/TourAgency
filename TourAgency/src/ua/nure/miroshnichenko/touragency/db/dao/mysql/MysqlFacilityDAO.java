package ua.nure.miroshnichenko.touragency.db.dao.mysql;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.miroshnichenko.touragency.db.DBUtil;
import ua.nure.miroshnichenko.touragency.db.Queries;
import ua.nure.miroshnichenko.touragency.db.dao.DAOException;
import ua.nure.miroshnichenko.touragency.db.dao.FacilityDAO;
import ua.nure.miroshnichenko.touragency.db.entity.Facility;
import ua.nure.miroshnichenko.myorm.core.transaction.Transaction;
import ua.nure.miroshnichenko.myorm.core.transaction.exception.TransactionException;
import ua.nure.miroshnichenko.myorm.core.transaction.exception.TransactionFactoryException;

public class MysqlFacilityDAO implements FacilityDAO {

	private final Logger LOG = Logger.getLogger(MysqlFacilityDAO.class);
	
	private static MysqlFacilityDAO instance;
	
	private MysqlFacilityDAO() {
	}
	
	public static synchronized MysqlFacilityDAO getInstance() {
		if (instance == null) {
			instance = new MysqlFacilityDAO();
		}
		return instance;
	}
	
	@Override
	public Facility find(int id) throws DAOException {
		Transaction transaction = null;
		Facility facility = null;

		try {
			transaction = DBUtil.getTransaction();
			facility = transaction.findByPK(Facility.class, id);
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
		return facility;
	}

	@Override
	public List<Facility> findAll() throws DAOException {
		Transaction transaction = null;
		List<Facility> facilities = new ArrayList<>();

		try {
			transaction = DBUtil.getTransaction();
			facilities = transaction.findAll(Facility.class);
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
		return facilities;
	}

	@Override
	public boolean save(Facility entity) throws DAOException {
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
	public boolean update(Facility entity) throws DAOException {
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
	public boolean delete(Facility entity) throws DAOException {
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
	public Facility getFacilityByName(String name) throws DAOException {
		Transaction transaction = null;
		Facility facility = null;

		try {
			transaction = DBUtil.getTransaction();
			List<Facility> res = transaction.customQuery(
					Queries.FACILITY_BY_NAME, Facility.class, name);
			if (!res.isEmpty()) {
				facility = res.get(0);
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
		return facility;
	}
}
