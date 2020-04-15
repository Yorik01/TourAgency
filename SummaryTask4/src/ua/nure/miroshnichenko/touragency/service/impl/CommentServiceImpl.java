package ua.nure.miroshnichenko.touragency.service.impl;

import java.util.List;

import ua.nure.miroshnichenko.touragency.db.dao.CommentDAO;
import ua.nure.miroshnichenko.touragency.db.dao.DAOException;
import ua.nure.miroshnichenko.touragency.db.dao.DAOFactory;
import ua.nure.miroshnichenko.touragency.db.entity.Comment;
import ua.nure.miroshnichenko.touragency.service.CommentService;
import ua.nure.miroshnichenko.touragency.service.exception.ServiceException;

public class CommentServiceImpl implements CommentService {

	private DAOFactory factoryDAO = DAOFactory.getInstance();
	
	@Override
	public Comment get(int id) throws ServiceException {
		Comment comment = null;

		try {
			CommentDAO commentDAO = factoryDAO.getCommentDAO();
			comment = commentDAO.find(id);
			
			return comment;
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Comment> getAll() throws ServiceException {
		List<Comment> comments;

		try {
			CommentDAO commentDAO = factoryDAO.getCommentDAO();
			comments = commentDAO.findAll();
			
			return comments;
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean save(Comment comment) throws ServiceException {
		boolean result = false;

		try {
			CommentDAO commentDAO = factoryDAO.getCommentDAO();
			result = commentDAO.save(comment);
			
			return result;
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean update(Comment comment) throws ServiceException {
		boolean result = false;

		try {
			CommentDAO commentDAO = factoryDAO.getCommentDAO();
			result = commentDAO.update(comment);
			
			return result;
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean delete(Comment comment) throws ServiceException {
		boolean result = false;

		try {
			CommentDAO commentDAO = factoryDAO.getCommentDAO();
			result = commentDAO.delete(comment);
			
			return result;
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Comment> getUserComments(int userId) throws ServiceException {
		List<Comment> comments;

		try {
			CommentDAO commentDAO = factoryDAO.getCommentDAO();
			comments = commentDAO.getUserComments(userId);
			
			return comments;
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Comment> getTourComments(int tourId) throws ServiceException {
		List<Comment> comments;

		try {
			CommentDAO commentDAO = factoryDAO.getCommentDAO();
			comments = commentDAO.getUserComments(tourId);
			
			return comments;
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}
}
