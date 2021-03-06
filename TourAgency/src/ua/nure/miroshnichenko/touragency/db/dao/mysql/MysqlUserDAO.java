package ua.nure.miroshnichenko.touragency.db.dao.mysql;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.miroshnichenko.touragency.db.DBUtil;
import ua.nure.miroshnichenko.touragency.db.Queries;
import ua.nure.miroshnichenko.touragency.db.dao.DAOException;
import ua.nure.miroshnichenko.touragency.db.dao.UserDAO;
import ua.nure.miroshnichenko.touragency.db.entity.User;
import ua.nure.miroshnichenko.myorm.core.transaction.Transaction;
import ua.nure.miroshnichenko.myorm.core.transaction.exception.TransactionException;
import ua.nure.miroshnichenko.myorm.core.transaction.exception.TransactionFactoryException;

public class MysqlUserDAO implements UserDAO {

	private final Logger LOG = Logger.getLogger(MysqlUserDAO.class);
	
	private static MysqlUserDAO instance;
	
	private MysqlUserDAO() {
	}
	
	public static synchronized MysqlUserDAO getInstance() {
		if (instance == null) {
			instance = new MysqlUserDAO();
		}
		return instance;
	}
	
	@Override
	public User find(int id) throws DAOException {
		Transaction transaction = null;
		User user = null;

		try {
			transaction = DBUtil.getTransaction();
			user = transaction.findByPK(User.class, id);
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
		return user;
	}

	@Override
	public List<User> findAll() throws DAOException {
		Transaction transaction = null;
		List<User> users = new ArrayList<>();

		try {
			transaction = DBUtil.getTransaction();
			users = transaction.findAll(User.class);
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
		return users;
	}

	@Override
	public boolean save(User entity) throws DAOException {
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
	public boolean update(User entity) throws DAOException {
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
	public boolean delete(User entity) throws DAOException {
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
	public User getUserByEmail(String email) throws DAOException {
		Transaction transaction = null;
		User user = null;
		
		try {
			transaction = DBUtil.getTransaction();
			List<User> users = transaction.customQuery(
					Queries.USER_BY_EMAIL, User.class, email);
			
			if(!users.isEmpty()) {
				user = users.get(0);
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
		return user;
	}
	
	@Override
	public Double getDiscountStep() throws DAOException {
		Transaction transaction = null;
		Double discountStep = null;

		try {
			transaction = DBUtil.getTransaction();
			discountStep = (Double) 
					transaction.getSingleValue(Queries.DISCOUNT_STEP);
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
		return discountStep;
	}
}