package ua.nure.miroshnichenko.touragency.db.entity;

import java.util.ArrayList;
import java.util.List;

public enum HotelType {
	MOTEL, RESORTS, HOSTEL, BOUTIQUE;

	public static List<HotelType> getHotelTypes(String[] names) {
		List<HotelType> hotelTypes = new ArrayList<>();
		if (names != null) {
			for (String name : names) {
				hotelTypes.add(valueOf(name));
			}
		}
		return hotelTypes;
	}
}
