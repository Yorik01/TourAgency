package ua.nure.miroshnichenko.touragency.web;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public final class Path {
	
	private Path() {
	}
	
	public static final String NO_PATH = "";
	
	public static final String INDEX_PAGE = "index.jsp";
	
	public static final String ADMIN_PAGE = "admin.jsp";
	
	public static final String LOGIN_PAGE = "login.jsp";
	
	public static final String PAGE_404 = "page404.jsp";
	
	public static final String PROFILE_PAGE = "profile.jsp";
	
	public static final String ERR_PAGE = "error.jsp";
	
	public static final String TOUR_INFO = "tour.jsp";
	
	public static final String HOTEL_FORM = "/WEB-INF/jsp/hotel/hotelForm.jsp";
	
	public static final String ROUTE_FORM = "/WEB-INF/jsp/route/routeForm.jsp";

	public static final String TRANSPORT_FORM = "/WEB-INF/jsp/transport/transportForm.jsp";

	public static final String PROFILE_EDIT_FORM = "/WEB-INF/jsp/user/profileEditForm.jsp";
	
	public static final String TOUR_FORM = "/WEB-INF/jsp/tour/tourForm.jsp";
	
	public static final String USERS_LIST = "/WEB-INF/jsp/user/users.jsp";
	
	public static final String CONTROLLER = "/TourAgency/controller?action=%s";
	
	public static final String RESERVATIONS_LIST = "/WEB-INF/jsp/reservation/reservations.jsp";
	
	public static final String ROUTES_LIST = "/WEB-INF/jsp/route/routes.jsp";
	
	public static final String HOTELS_LIST = "/WEB-INF/jsp/hotel/hotels.jsp";
	
	public static final String TOURS_LIST = "/WEB-INF/jsp/tour/tours.jsp";
	
	public static final String TRANSPORTS_LIST = "/WEB-INF/jsp/transport/transports.jsp";
	
	public static final String USER_RESERVATIONS_LIST = "/WEB-INF/jsp/reservation/userReservations.jsp";

	public static final String USER_COMMENTS = "/WEB-INF/jsp/comment/comments.jsp";
	
	public static final String AVERAGE_TOUR_MARK_STATISTIC = "/WEB-INF/jsp/statistic/averageTourMark.jsp";
	
	public static final String MANAGER_REVENUE_STATISTIC = "/WEB-INF/jsp/statistic/managerRevenue.jsp";

	public static final String TOUR_RESERVAION_STATISTIC = "/WEB-INF/jsp/statistic/tourReservation.jsp";

	public static final String USER_RESERVAION_STATISTIC = "/WEB-INF/jsp/statistic/userReservaion.jsp";

	public static String getControllerPath(String action) {
		return String.format(CONTROLLER, action);
	}
	
	public static String getControllerPath(String action, Map<String, Object> params) {
		StringBuilder url = new StringBuilder(String.format(CONTROLLER,  action));

		if (!params.isEmpty()) {
			url.append('&');
			
			Iterator<Entry<String, Object>> iterator = params.entrySet().iterator();
			while (iterator.hasNext()) {
				Entry<String, Object> pair = iterator.next();
				
				url
					.append(pair.getKey())
					.append('=')
					.append(pair.getValue());
			
				if (iterator.hasNext()) {
					url.append('&');
				}
			}
		}
	
		return url.toString();
	}
}
