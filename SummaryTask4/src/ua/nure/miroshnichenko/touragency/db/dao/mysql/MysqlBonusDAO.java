package ua.nure.miroshnichenko.touragency.db.dao.mysql;

import java.util.ArrayList;
import java.util.List;

import ua.nure.miroshnichenko.touragency.db.DBUtil;
import ua.nure.miroshnichenko.touragency.db.Queries;
import ua.nure.miroshnichenko.touragency.db.dao.BonusDAO;
import ua.nure.miroshnichenko.touragency.db.dao.DAOException;
import ua.nure.miroshnichenko.touragency.db.entity.Bonus;
import ua.nure.miroshnichenko.touragency.db.entity.Tour;
import ua.nure.miroshnichenko.touragency.db.entity.User;
import ua.nure.miroshnichenko.myorm.core.transaction.Transaction;
import ua.nure.miroshnichenko.myorm.core.transaction.exception.TransactionException;
import ua.nure.miroshnichenko.myorm.core.transaction.exception.TransactionFactoryException;

public class MysqlBonusDAO implements BonusDAO {

	private MysqlTourDAO tourDAO = new MysqlTourDAO();

	private MysqlUserDAO userDAO = new MysqlUserDAO();

	@Override
	public Bonus find(int id) throws DAOException {
		Transaction transaction = null;
		Bonus bonus = null;

		try {
			transaction = DBUtil.getTransaction();
			bonus = transaction.findByPK(Bonus.class, id);

			initBonus(bonus);
			
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
		return bonus;
	}

	@Override
	public List<Bonus> findAll() throws DAOException {
		Transaction transaction = null;
		List<Bonus> bonuses = new ArrayList<>();

		try {
			transaction = DBUtil.getTransaction();
			bonuses = transaction.findAll(Bonus.class);

			for (Bonus bonus : bonuses) {
				initBonus(bonus);
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
		return bonuses;
	}

	@Override
	public boolean save(Bonus entity) throws DAOException {
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
	public boolean update(Bonus entity) throws DAOException {
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
	public boolean delete(Bonus entity) throws DAOException {
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
	public List<Bonus> getUserBonuses(int userId) throws DAOException {
		Transaction transaction = null;
		List<Bonus> bonuses = new ArrayList<>();

		try {
			transaction = DBUtil.getTransaction();
			bonuses = transaction.customQuery(Queries.USER_BONUSES, Bonus.class, userId);

			for (Bonus bonus : bonuses) {
				initBonus(bonus);
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
		return bonuses;
	}
	
	private void initBonus(Bonus bonus) throws DAOException {
		Tour tour = tourDAO.find(bonus.getTourId());
		User user = userDAO.find(bonus.getUserId());

		bonus.setTour(tour);
		bonus.setUser(user);
	}
}
