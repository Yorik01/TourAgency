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
}
