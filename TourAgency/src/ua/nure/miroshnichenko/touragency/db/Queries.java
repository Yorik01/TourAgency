package ua.nure.miroshnichenko.touragency.db;

public final class Queries {

	private Queries() {
	}
	
	public static final String ALL_TOURS = "SELECT * FROM tour ORDER BY is_fired";
	
	public static final String HOTEL_SERVICES = "SELECT *"
			+ "FROM service s "
			+ "INNER JOIN hotel_service hs USING (service_id)"
			+ " WHERE hs.hotel_id = ?";

	public static final String HOTEL_FACILITIES = "SELECT *"
			+ "FROM facility f "
			+ "INNER JOIN hotel_facility hf USING (facility_id)"
			+ " WHERE hf.hotel_id = ?";
	
	public static final String USER_RESERVATIONS = "SELECT * FROM reservation WHERE user_id = ?";
	
	public static final String TOUR_RESERVATIONS = "SELECT * FROM reservation WHERE tour_id = ?";
	
	public static final String USER_BY_EMAIL = "SELECT * FROM users WHERE email = ?";
	
	public static final String HOTEL_BY_NAME = "SELECT * FROM hotel WHERE hotel_name = ?";
	
	public static final String SERVICING_BY_NAME = "SELECT * FROM service WHERE service_name = ?";
	
	public static final String FACILITY_BY_NAME = "SELECT * FROM facility WHERE facility_name = ?";
	
	public static final String TRANSPORT_BY_CODE_AND_TYPE = "SELECT * FROM transport "
			+ "WHERE transport_code = ? AND transport_type_id = ?";
	
	public static final String SET_FACILITY_FOR_HOTEL = "INSERT INTO hotel_facility VALUES(?, ?)";
	
	public static final String SET_SERVICING_FOR_HOTEL = "INSERT INTO hotel_service VALUES(?, ?)";
	
	public static final String DELETE_FACILITY_FOR_HOTEL = "DELETE FROM hotel_facility WHERE facility_id = ? AND hotel_id = ?";
	
	public static final String DELETE_SERVICING_FOR_HOTEL = "DELETE FROM hotel_service WHERE service_id = ? AND hotel_id = ?";
	
	public static final String PLACE_BY_COUNTRY_AND_CITY = "SELECT * FROM place WHERE"
			+ " place_country = ? AND place_city = ?";
	
	public static final String ALL_HOTEL_TYPES = "SELECT hotel_type_id FROM hotel_type";
	
	public static final String ALL_FOODS = "SELECT food_id FROM food";
	
	public static final String ALL_BEACHES = "SELECT beach_id FROM beach";
	
	public static final String ALL_FACILITIES = "SELECT facility_id FROM facility";
	
	public static final String ALL_SERVICINGS = "SELECT service_id FROM service";
	
	public static final String ALL_TOUR_TYPES = "SELECT tour_type_id FROM tour_type";
	
	public static final String ALL_TRANSPORT_TYPES = "SELECT transport_type_id FROM transport";
	
	public static final String USER_BONUSES = "SELECT * FROM bonus WHERE user_id = ?";
	
	public static final String SET_DISCOUNT_STEP_FOR_ALL_USERS = "UPDATE users SET discount_step = ?";
	
	public static final String USER_COMMENTS = "SELECT * FROM comment WHERE user_id = ?";
	
	public static final String TOUR_COMMENTS = "SELECT * FROM comment WHERE tour_id = ? ORDER BY comment_date";
	
	public static final String ROUTE_BY_PLACES = "SELECT * FROM route WHERE route_from = ? AND route_to = ?";
	
	public static final String TRANSPORT_BY_CODE = "SELECT * FROM transport WHERE transport_code = ?";
	
	public static final String TOURS_BY_DATE_AND_HOTEL_ID = "SELECT * FROM tour t "
			+ "INNER JOIN hotel h USING(hotel_id) "
			+ "WHERE t.start_date = ? AND t.end_date = ? AND h.hotel_id = ?";

	public static final String AVERAGE_TOURS_MARKS_STATISTIC = "SELECT t.tour_id, t.start_date, t.end_date, h.hotel_name, AVG(c.comment_mark) AS average_mark "
			+ "FROM tour t "
			+ "INNER JOIN comment c USING (tour_id) "
			+ "INNER JOIN hotel h USING (hotel_id) "
			+ "GROUP BY t.tour_id, t.start_date, t.end_date, h.hotel_name";
	
	public static final String MANAGERS_REVENUES_STATISTIC = "SELECT u.email, "
			+ "SUM(r.people_count * ((100 - t.agency_procent) * ((h.hotel_price + trt.transport_price + trb.transport_price) / 100))) AS revenue "
			+ "FROM tour t INNER JOIN reservation r USING(tour_id) "
			+ "INNER JOIN users u ON u.user_id = r.manager_id "
			+ "INNER JOIN hotel h USING (hotel_id) "
			+ "INNER JOIN transport trt ON t.transport_to_id = trt.transport_id "
			+ "INNER JOIN transport trb ON trb.transport_id = t.transport_back_id "
			+ "WHERE u.role_id IN (2, 3) "
			+ "GROUP BY u.email";
	
	public static final String TOURS_RESERVATIONS_STATISTIC = "SELECT t.tour_id, t.start_date, t.end_date, h.hotel_name, COUNT(r.reservation_id) AS reservations_count "
			+ "FROM tour t "
			+ "INNER JOIN hotel h USING (hotel_id) "
			+ "INNER JOIN reservation r USING (tour_id) "
			+ "GROUP BY h.hotel_name";
	
	public static final String USER_RESERVATIONS_STATISTIC = "SELECT u.email, COUNT(r.reservation_id) AS reservations_count "
			+ "FROM tour t "
			+ "INNER JOIN reservation r USING (tour_id) "
			+ "INNER JOIN users u USING (user_id) "
			+ "GROUP BY u.email";
	
	public static final String FILTER_TOUR = "SELECT * FROM tour t "
			+ "INNER JOIN hotel h USING (hotel_id) "
			+ "INNER JOIN hotel_service hs USING (hotel_id) "
			+ "INNER JOIN hotel_facility hf USING (hotel_id) "
			+ "INNER JOIN facility f USING (facility_id) "
			+ "INNER JOIN service s USING (service_id) "
			+ "INNER JOIN transport trt ON trt.transport_id = t.transport_to_id "
			+ "INNER JOIN transport trb ON trb.transport_id = t.transport_back_id "
			+ "INNER JOIN route rt ON trt.route_id = rt.route_id "
			+ "INNER JOIN place pt ON rt.route_to = pt.place_id "
			+ "INNER JOIN place pf ON rt.route_from = pf.place_id WHERE "
			+ "h.hotel_type_id IN (%s) AND "
			+ "h.food_id IN (%s) AND "
			+ "h.beach_id IN (%s) AND "
			+ "f.facility_name IN (%s) AND "
			+ "s.service_name IN (%s) AND " 	
			+ "t.start_date = ? AND "
			+ "t.end_date = ? AND "
			+ "t.tour_type_id IN(%s) AND "
			+ "trt.transport_type_id IN (%s) AND "
			+ "h.hotel_stars IN (%s) AND "
			+ "pt.place_country = ? AND "
			+ "pt.place_city = ? AND "
			+ "pf.place_country = 'Ukraine' AND "
			+ "pf.place_city = ? AND "
			+ "(100 - t.agency_procent) * ((h.hotel_price + trt.transport_price + trb.transport_price) / 100) <= ? AND "
			+ "trt.max_places - (SELECT COUNT(*) FROM reservation WHERE tour_id = t.tour_id) >= ? AND "
			+ "h.hotel_max_rooms - (SELECT COUNT(*) FROM reservation WHERE tour_id = t.tour_id) >= ? "
			+ "ORDER BY t.is_fired";
}
