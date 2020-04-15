package ua.nure.miroshnichenko.touragency.web.action.comment;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.miroshnichenko.touragency.db.entity.Comment;
import ua.nure.miroshnichenko.touragency.service.CommentService;
import ua.nure.miroshnichenko.touragency.service.exception.ServiceException;
import ua.nure.miroshnichenko.touragency.web.Path;
import ua.nure.miroshnichenko.touragency.web.action.Action;
import ua.nure.miroshnichenko.touragency.web.action.ActionException;

public class UserCommentsAction extends Action {

	private static final long serialVersionUID = 6407377868716494616L;

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException, ActionException {
		
		CommentService commentService = serviceFactory.getCommentService();
		
		int userId = Integer.parseInt(req.getParameter("userId"));
		
		List<Comment> comments;
		try {
			comments = commentService.getUserComments(userId);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ActionException(e);
		}
		
		req.setAttribute("comments", comments);		
		req.setAttribute("form", Path.USER_COMMENTS);
		
		return Path.PROFILE_PAGE;
	}
}
