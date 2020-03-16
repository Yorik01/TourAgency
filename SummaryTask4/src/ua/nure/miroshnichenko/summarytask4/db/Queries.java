package ua.nure.miroshnichenko.summarytask4.db;

public final class Queries {

	private Queries() {
	}

	public static final String HOTEL_SERVICES = "SELECT *"
			+ "FROM service s "
			+ "INNER JOIN hotel_service hs USING (service_id)"
			+ " WHERE hs.hotel_id = ?";

	public static final String HOTEL_FACILITIES = "SELECT *"
			+ "FROM facility f "
			+ "INNER JOIN hotel_facility hf USING (facility_id)"
			+ " WHERE hs.hotel_id = ?";
	
	public static final String USER_RESERVATIONS = "SELECT * reservation r WHERE user_id = ?";
	
	public static final String TOUR_RESERVATIONS = "SELECT * reservation r WHERE tour_id = ?";
	
	public static final String USER_BY_EMAIL = "SELECT * FROM users WHERE email = ?";
	
	public static final String HOTEL_BY_NAME = "SELECT * FROM hotel WHERE hotel_name = ?";
	
	public static final String TRANSPORT_BY_CODE_AND_TYPE = "SELECT * FROM transport "
			+ "WHERE transport_code = ? AND transport_type_id = ?";
	
	public static final String FILTER_TOUR = "SELECT * FROM tour t"
			+ "INNER JOIN hotel h USING (hotel_id)"
			+ "INNER JOIN hotel_service hs USING (hotel_id)"
			+ "INNER JOIN hotel_facility hf USING (hotel_id)"
			+ "INNER JOIN service s USING (service_id)"
			+ "INNER JOIN transport tr ON tr.transport_id = t.transport_to_id"
			+ "INNER JOIN route r USING (route_id)"
			+ "INNER JOIN place p ON r.route_to = p.place_id"
			+ "WHERE"
			+ "h.hotel_type_id IN (%s) AND"
			+ "h.food_id IN (%s) AND"
			+ "h.beach_id IN (%s) AND"
			+ "s.service_name IN (%s) AND"
			+ "t.start_date = ? AND"
			+ "t.end_date = ? AND"
			+ "t.tour_type_id = ? AND"
			+ "p.place_country = ? AND"
			+ "p.place_city = ? AND";
}
