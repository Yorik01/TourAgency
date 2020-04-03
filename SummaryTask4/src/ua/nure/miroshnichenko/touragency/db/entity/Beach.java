package ua.nure.miroshnichenko.touragency.db.entity;

import java.util.ArrayList;
import java.util.List;

public enum Beach {
	SAND, PEBBLE, PLATE, SAND_PEBBLE;

	public static List<Beach> getBeaches(String[] names) {
		List<Beach> beaches = new ArrayList<>();
		if (names != null) {
			for (String name : names) {
				beaches.add(valueOf(name));
			}
		}
		return beaches;
	}
}
