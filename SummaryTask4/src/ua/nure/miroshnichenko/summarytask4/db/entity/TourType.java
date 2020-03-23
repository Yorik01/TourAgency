package ua.nure.miroshnichenko.summarytask4.db.entity;

import java.util.ArrayList;
import java.util.List;

public enum TourType {
	RELAX, EXCURSION, SHOPPING;

	public static List<TourType> getTourTypes(String[] names) {
		List<TourType> tourTypes = new ArrayList<>();
		if (names != null) {
			for (String name : names) {
				tourTypes.add(valueOf(name));
			}
		}
		return tourTypes;
	}
}
