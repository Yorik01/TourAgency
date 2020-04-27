package ua.nure.miroshnichenko.touragency.web.action.tour;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.miroshnichenko.touragency.db.entity.Hotel;
import ua.nure.miroshnichenko.touragency.db.entity.Tour;
import ua.nure.miroshnichenko.touragency.service.DiscountService;
import ua.nure.miroshnichenko.touragency.service.HotelService;
import ua.nure.miroshnichenko.touragency.service.TourService;
import ua.nure.miroshnichenko.touragency.service.exception.ServiceException;
import ua.nure.miroshnichenko.touragency.web.Path;
import ua.nure.miroshnichenko.touragency.web.action.Action;
import ua.nure.miroshnichenko.touragency.web.action.ActionException;
import ua.nure.miroshnichenko.touragency.web.action.ActionUtil;

/**
 * The action to get all tours.
 * 
 * @author Miroshnichenko Y. D.
 */
public class AllToursAction extends Action {

	private static final long serialVersionUID = -5757678081770546971L;

	private final Logger LOG = Logger.getLogger(AllToursAction.class);
	
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException, ActionException {

		TourService tourService = serviceFactory.getTourService();
		HotelService hotelService = serviceFactory.getHotelService();

		List<Tour> tours = ActionUtil.getAllEntities(tourService);
	
		String keyword = req.getParameter("keyword");
		if (keyword != null && !keyword.isEmpty()) {
			try {
				Hotel hotel = hotelService.getHotelByName(keyword);

				if (hotel != null) {
					tours = tours.stream()
							.filter(t -> t.getHotelId().equals(hotel.getId()))
							.collect(Collectors.toList());
				} else {
					tours.clear();
				}
			} catch (ServiceException e) {
				LOG.error(e);
				throw new ActionException(e);
			}
		}

		DiscountService discountService = serviceFactory.getDiscountService();
		
		ActionUtil.setAllEntitiesJsonInAttribute(req, tourService, "toursJSON");
		ActionUtil.setDiscountStepInAttribute(discountService, req);
		
		req.setAttribute("tours", tours);
		req.setAttribute("form", Path.TOURS_LIST);

		return Path.ADMIN_PAGE;
	}
}
