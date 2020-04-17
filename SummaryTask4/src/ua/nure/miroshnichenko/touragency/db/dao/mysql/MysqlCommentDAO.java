package ua.nure.miroshnichenko.touragency.db.dao.mysql;

import java.util.ArrayList;
import java.util.List;

import ua.nure.miroshnichenko.myorm.core.transaction.Transaction;
import ua.nure.miroshnichenko.myorm.core.transaction.exception.TransactionException;
import ua.nure.miroshnichenko.myorm.core.transaction.exception.TransactionFactoryException;
import ua.nure.miroshnichenko.touragency.db.DBUtil;
import ua.nure.miroshnichenko.touragency.db.Queries;
import ua.nure.miroshnichenko.touragency.db.dao.CommentDAO;
import ua.nure.miroshnichenko.touragency.db.dao.DAOException;
import ua.nure.miroshnichenko.touragency.db.dao.TourDAO;
import ua.nure.miroshnichenko.touragency.db.dao.UserDAO;
import ua.nure.miroshnichenko.touragency.db.entity.Comment;
import ua.nure.miroshnichenko.touragency.db.entity.Tour;
import ua.nure.miroshnichenko.touragency.db.entity.User;

public class MysqlCommentDAO implements CommentDAO {

	private UserDAO userDAO = new MysqlUserDAO();
	
	private TourDAO tourDAO = new MysqlTourDAO();
	
	@Override
	public Comment find(int id) throws DAOException {
		Transaction transaction = null;
		Comment comment = null;
			
		try {
			transaction = DBUtil.getTransaction();
			comment = transaction.findByPK(Comment.class, id);
			
			initComment(comment);
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
		return comment;
	}

	@Override
	public List<Comment> findAll() throws DAOException {
		Transaction transaction = null;
		List<Comment> comments = new ArrayList<>();
			
		try {
			transaction = DBUtil.getTransaction();
			comments = transaction.findAll(Comment.class);
			
			for (Comment comment : comments) {
				initComment(comment);
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
		return comments;
	}

	@Override
	public boolean save(Comment comment) throws DAOException {
		Transaction transaction = null;
		boolean result = false;
			
		try {
			transaction = DBUtil.getTransaction();
			result = transaction.insert(comment);
			
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
	public boolean update(Comment comment) throws DAOException {
		Transaction transaction = null;
		boolean result = false;
			
		try {
			transaction = DBUtil.getTransaction();
			result = transaction.update(comment);
			
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
	public boolean delete(Comment comment) throws DAOException {
		Transaction transaction = null;
		boolean result = false;
			
		try {
			transaction = DBUtil.getTransaction();
			result = transaction.delete(comment);
			
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
	public List<Comment> getUserComments(int userId) throws DAOException {
		Transaction transaction = null;
		List<Comment> comments = new ArrayList<>();
		
		try {
			transaction = DBUtil.getTransaction();
			comments = transaction.customQuery(
					Queries.USER_COMMENTS, Comment.class, userId);
			
			for (Comment comment : comments) {
				initComment(comment);
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
		return comments;
	}

	@Override
	public List<Comment> getTourComments(int tourId) throws DAOException {
		Transaction transaction = null;
		List<Comment> comments = new ArrayList<>();
		
		try {
			transaction = DBUtil.getTransaction();
			comments = transaction.customQuery(
					Queries.TOUR_COMMENTS, Comment.class, tourId);
			
			for (Comment comment : comments) {
				initComment(comment);
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
		return comments;
	}
	
	private void initComment(Comment comment) throws DAOException {
		User user = userDAO.find(comment.getUserId());
		Tour tour = tourDAO.find(comment.getTourId());
		
		comment.setTour(tour);
		comment.setUser(user);
	}
}
