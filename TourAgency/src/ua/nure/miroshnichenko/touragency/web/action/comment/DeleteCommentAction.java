package ua.nure.miroshnichenko.touragency.web.action.comment;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.miroshnichenko.touragency.db.entity.Comment;
import ua.nure.miroshnichenko.touragency.service.CommentService;
import ua.nure.miroshnichenko.touragency.service.exception.ServiceException;
import ua.nure.miroshnichenko.touragency.web.Path;
import ua.nure.miroshnichenko.touragency.web.action.Action;
import ua.nure.miroshnichenko.touragency.web.action.ActionException;

/**
 * The action to delete comment.
 * 
 * @author Miroshnichenko Y. D.
 */
public class DeleteCommentAction extends Action {

	private static final long serialVersionUID = 144309401930576588L;

	private final Logger LOG = Logger.getLogger(DeleteCommentAction.class);
	
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException, ActionException {
		
		CommentService commentService = serviceFactory.getCommentService();
		
		int commentId = Integer.parseInt(req.getParameter("commentId"));
		
		Comment comment = new Comment();
		comment.setId(commentId);
		
		try {
			commentService.delete(comment);
		} catch (ServiceException e) {
			LOG.error(e);
			throw new ActionException(e);
		}
		
		return Path.NO_PATH;
	}
}
