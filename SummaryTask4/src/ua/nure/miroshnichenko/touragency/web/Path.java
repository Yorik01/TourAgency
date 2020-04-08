package ua.nure.miroshnichenko.touragency.web;

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
	
	public static final String HOTEL_FORM = "/WEB-INF/jsp/hotelForm.jsp";
	
	public static final String ROUTE_FORM = "/WEB-INF/jsp/routeForm.jsp";

	public static final String TRANSPORT_FORM = "/WEB-INF/jsp/transportForm.jsp";

	public static final String PROFILE_EDIT_FORM = "/WEB-INF/jsp/profileEditForm.jsp";
	
	public static final String TOUR_FORM = "/WEB-INF/jsp/tourForm.jsp";
	
	public static final String USERS_LIST = "/WEB-INF/jsp/users.jsp";
	
	public static final String CONTROLLER = "/TourAgency/controller?action=%s";
	
	public static final String RESERVATIONS_LIST = "/WEB-INF/jsp/reservations.jsp";
	
	public static final String ROUTES_LIST = "/WEB-INF/jsp/routes.jsp";
	
	public static final String HOTELS_LIST = "/WEB-INF/jsp/hotels.jsp";
	
	public static final String TOURS_LIST = "/WEB-INF/jsp/tours.jsp";
	
	public static final String TRANSPORTS_LIST = "/WEB-INF/jsp/transports.jsp";
	
	public static final String USER_RESERVATIONS_LIST = "/WEB-INF/jsp/userReservations.jsp";
	
	public static String getControllerPath(String action) {
		return String.format(CONTROLLER, action);
	}
}
