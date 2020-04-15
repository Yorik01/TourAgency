package ua.nure.miroshnichenko.touragency.db.dao;

import java.util.List;

import ua.nure.miroshnichenko.touragency.db.entity.Comment;

public interface CommentDAO extends DAO<Comment> {
	
	List<Comment> getUserComments(int userId) throws DAOException;
	
	List<Comment> getTourComments(int tourId) throws DAOException;
}
