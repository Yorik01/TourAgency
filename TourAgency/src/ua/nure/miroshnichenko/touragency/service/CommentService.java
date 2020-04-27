package ua.nure.miroshnichenko.touragency.service;

import java.util.List;

import ua.nure.miroshnichenko.touragency.db.entity.Comment;
import ua.nure.miroshnichenko.touragency.service.exception.ServiceException;


/**
 * Service to control information about comments.
 * 
 * @author Miroshnichenko Y. D.
 */
public interface CommentService extends CRUDService<Comment> {
	
	/**
	 * Get a list of comments of a specific user.
	 * 
	 * @param userId id of user.
	 * 	
	 * @return list of comments.
	 * 
	 * @throws ServiceException
	 */
	List<Comment> getUserComments(int userId) throws ServiceException;
	
	/**
	 * Get a list of comments of a specific tour.
	 * 
	 * @param tourId id of tour.
	 * 	
	 * @return list of comments.
	 * 
	 * @throws ServiceException
	 */
	List<Comment> getTourComments(int tourId) throws ServiceException;
}
