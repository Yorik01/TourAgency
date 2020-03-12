package ua.nure.miroshnichenko.summarytask4.db.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ua.nure.miroshnichenko.summarytask4.db.DBUtil;
import ua.nure.miroshnichenko.summarytask4.myorm.Entity;
import ua.nure.miroshnichenko.summarytask4.myorm.core.transaction.Transaction;
import ua.nure.miroshnichenko.summarytask4.myorm.core.transaction.exception.TransactionException;
import ua.nure.miroshnichenko.summarytask4.myorm.core.transaction.exception.TransactionFactoryException;

public interface DAO<T extends Entity> {
	T find(int id) throws DAOException;

	List<T> findAll() throws DAOException;

	boolean save(T entity) throws DAOException;

	boolean update(T entity) throws DAOException;

	boolean delete(T entity) throws DAOException;
	
	static List<Entity> customQuery(String query, Class<? extends Entity> type, String... params) throws DAOException {
		List<Entity> result = new ArrayList<>();
		Transaction transaction = null;
		try {
			transaction = DBUtil.getTransaction();
			result = (List<Entity>) transaction.customQuery(query, type, params);
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

	static List<Entity> customQuery(String query, Class<? extends Entity> type) throws DAOException {
		List<Entity> result = new ArrayList<>();
		Transaction transaction = null;
		try {
			transaction = DBUtil.getTransaction();
			result = (List<Entity>) transaction.customQuery(query, type);
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
