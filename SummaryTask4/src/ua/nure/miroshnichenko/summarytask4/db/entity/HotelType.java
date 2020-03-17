package ua.nure.miroshnichenko.summarytask4.db.entity;

import java.util.ArrayList;
import java.util.List;

public enum HotelType {
	MOTEL, RESORTS, HOSTEL, BOUTIQUE;

	public static List<HotelType> getHotelTypes(String[] names) {
		List<HotelType> hotelTypes = new ArrayList<>();
		for (String name : names) {
			hotelTypes.add(valueOf(name));
		}
		return hotelTypes;
	}
}
