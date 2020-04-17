package ua.nure.miroshnichenko.touragency.web.action.comment;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.miroshnichenko.touragency.db.entity.Comment;
import ua.nure.miroshnichenko.touragency.service.CommentService;
import ua.nure.miroshnichenko.touragency.service.exception.ServiceException;
import ua.nure.miroshnichenko.touragency.web.Path;
import ua.nure.miroshnichenko.touragency.web.action.Action;
import ua.nure.miroshnichenko.touragency.web.action.ActionException;

public class SaveCommentAction extends Action {

	private static final long serialVersionUID = -2446431015129774625L;

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException, ActionException {
		
		CommentService commentService = serviceFactory.getCommentService();
	
		int userId = Integer.parseInt(req.getParameter("userId"));
		int tourId = Integer.parseInt(req.getParameter("tourId"));
		
		String text = req.getParameter("commentText");
		int mark = Integer.parseInt(req.getParameter("commentMark"));
		
		Timestamp timestamp = Timestamp.from(Instant.now());
		
		Comment comment = new Comment();
		comment.setUserId(userId);
		comment.setTourId(tourId);
		comment.setDate(timestamp);
		comment.setText(text);
		comment.setMark(mark);
		
		Map<String, Object> params = new HashMap<>();
		params.put("id", tourId);
		
		if (Boolean.parseBoolean(req.getParameter("edit"))) {
			int commentId = Integer.parseInt(req.getParameter("commentId"));
			comment.setId(commentId);
			
			try {
				commentService.update(comment);
			} catch (ServiceException e) {
				e.printStackTrace();
				throw new ActionException(e);
			}
			
			return Path.NO_PATH;
		}
		
		try {
			commentService.save(comment);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ActionException(e);
		}
		
		return Path.NO_PATH;
	}
}
