package ua.nure.miroshnichenko.touragency.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.miroshnichenko.touragency.db.dao.CommentDAO;
import ua.nure.miroshnichenko.touragency.db.dao.DAOException;
import ua.nure.miroshnichenko.touragency.db.dao.DAOFactory;
import ua.nure.miroshnichenko.touragency.db.entity.Comment;
import ua.nure.miroshnichenko.touragency.service.CommentService;
import ua.nure.miroshnichenko.touragency.service.exception.ServiceException;
import ua.nure.miroshnichenko.touragency.service.impl.constants.LogginMessages;

public class CommentServiceImpl implements CommentService {

	private final Logger LOG = Logger.getLogger(CommentServiceImpl.class);
	
	private DAOFactory factoryDAO = DAOFactory.getInstance();
	
	private static CommentServiceImpl instance;
	
	private CommentServiceImpl () {
		factoryDAO = DAOFactory.getInstance();
	}
	
	public static synchronized CommentServiceImpl getInstance() {
		if(instance == null) {
			instance = new CommentServiceImpl();
		}
		return instance;
	}
	
	/**
	 * Set the DAOFactory implementation using the setter.
	 * It is used for mock test.
	 */
	public void setFactoryDAO(DAOFactory factoryDAO) {
		this.factoryDAO = factoryDAO;
	}
	
	@Override
	public Comment get(int id) throws ServiceException {
		Comment comment = null;

		try {
			CommentDAO commentDAO = factoryDAO.getCommentDAO();
			comment = commentDAO.find(id);
			
			return comment;
		} catch (DAOException e) {
			LOG.error(e);
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
			LOG.error(e);
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean save(Comment comment) throws ServiceException {
		try {
			CommentDAO commentDAO = factoryDAO.getCommentDAO();
			boolean result = commentDAO.save(comment);
			
			if (result) {
				LOG.trace(String.format(LogginMessages.USER_SAVE_COMMENT,
						comment.getUser().getEmail(), comment.getTourId()));
			}
			return result;
		} catch (DAOException e) {
			LOG.error(e);
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean update(Comment comment) throws ServiceException {
		try {
			CommentDAO commentDAO = factoryDAO.getCommentDAO();
			boolean result = commentDAO.update(comment);
			
			if (result) {
				LOG.trace(String.format(
						LogginMessages.USER_UPDATE_COMMENT,
						comment.getUser().getEmail(), comment.getTourId()));
			}
			return result;
		} catch (DAOException e) {
			LOG.error(e);
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean delete(Comment comment) throws ServiceException {
		try {
			CommentDAO commentDAO = factoryDAO.getCommentDAO();
			boolean result = commentDAO.delete(comment);
			
			if (result) {
				LOG.trace(String.format(
						LogginMessages.USER_DELTE_COMMENT,
						comment.getUser().getEmail(), comment.getTourId()));
			}
			
			return result;
		} catch (DAOException e) {
			LOG.error(e);
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
			LOG.error(e);
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Comment> getTourComments(int tourId) throws ServiceException {
		List<Comment> comments;

		try {
			CommentDAO commentDAO = factoryDAO.getCommentDAO();
			comments = commentDAO.getTourComments(tourId);
			
			return comments;
		} catch (DAOException e) {
			LOG.error(e);
			throw new ServiceException(e);
		}
	}
}
