package ua.nure.miroshnichenko.touragency.service;

import java.util.List;

import ua.nure.miroshnichenko.touragency.db.entity.Comment;
import ua.nure.miroshnichenko.touragency.service.exception.ServiceException;

public interface CommentService extends CRUDService<Comment> {
	
	List<Comment> getUserComments(int userId) throws ServiceException;
	
	List<Comment> getTourComments(int tourId) throws ServiceException;
}
