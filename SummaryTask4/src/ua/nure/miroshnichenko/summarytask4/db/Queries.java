package ua.nure.miroshnichenko.summarytask4.db;

public final class Queries {

	private Queries() {
	}

	public static final String HOTEL_SERVICES = "SELECT "
			+ "s.service_id, s.service_name, s.service_type_id "
			+ "FROM service s "
			+ "INNER JOIN hotel_service hs USING (service_id)"
			+ " WHERE hs.hotel_id = ?";

	public static final String HOTEL_FACILITIES = "SELECT "
			+ "f.facility_id, f.facility_name, s.service_type_id "
			+ "FROM facility f "
			+ "INNER JOIN hotel_facility hf USING (facility_id)"
			+ " WHERE hs.hotel_id = ?";
}
