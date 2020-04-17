package ua.nure.miroshnichenko.touragency.web.action.tour;

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

public class TourInfoAction extends Action {

	private static final long serialVersionUID = 1L;

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException, ActionException {
		
		TourService tourService = serviceFactory.getTourService();
		CommentService commentService = serviceFactory.getCommentService();
		
		int id = Integer.parseInt(req.getParameter("id"));
		
		Tour tour;
		List<Tour> tours;
		List<String> photos;
		List<Comment> comments;
		try {
			tours = tourService.getAll();
			tour = tourService.get(id);
			comments = commentService.getTourComments(id);
		} catch (ServiceException e) {
			throw new ActionException(e);
		}
		
		photos = ActionUtil.getHotelPhotos(
				tour.getHotelId(),
				ActionUtil.getPhotosFolderPath(req));
		
		String toursJSON = ActionUtil.entitiesToJson(tours);
		
		req.setAttribute("tour", tour);
		req.setAttribute("photos", photos);
		req.setAttribute("toursJSON", toursJSON);
		req.setAttribute("comments", comments);
		
		return Path.TOUR_INFO;
	}
}
