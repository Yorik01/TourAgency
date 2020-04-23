package ua.nure.miroshnichenko.touragency.web.action.comment;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.miroshnichenko.touragency.db.entity.Comment;
import ua.nure.miroshnichenko.touragency.db.entity.Tour;
import ua.nure.miroshnichenko.touragency.service.CommentService;
import ua.nure.miroshnichenko.touragency.service.TourService;
import ua.nure.miroshnichenko.touragency.service.exception.ServiceException;
import ua.nure.miroshnichenko.touragency.web.Path;
import ua.nure.miroshnichenko.touragency.web.action.Action;
import ua.nure.miroshnichenko.touragency.web.action.ActionException;
import ua.nure.miroshnichenko.touragency.web.action.ActionUtil;

public class UserCommentsAction extends Action {

	private static final long serialVersionUID = 6407377868716494616L;

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException, ActionException {
		
		CommentService commentService = serviceFactory.getCommentService();
		TourService tourService = serviceFactory.getTourService();
		
		int userId = Integer.parseInt(req.getParameter("id"));
		
		List<Comment> comments;
		List<Tour> tours;
		try {
			comments = commentService.getUserComments(userId);
			tours = tourService.getAll();
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ActionException(e);
		}
		
		String toursJSON = ActionUtil.entitiesToJson(tours);
		
		req.setAttribute("comments", comments);		
		req.setAttribute("form", Path.USER_COMMENTS);
		req.setAttribute("toursJSON", toursJSON);
		
		return Path.PROFILE_PAGE;
	}
}
