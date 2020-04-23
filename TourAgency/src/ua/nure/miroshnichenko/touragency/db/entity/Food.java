package ua.nure.miroshnichenko.touragency.db.entity;

import java.util.ArrayList;
import java.util.List;

public enum Food {
	AL, FB, HB, BB, OB;

	public static List<Food> getFoods(String[] names) {
		List<Food> foods = new ArrayList<>();
		if (names != null) {
			for (String name : names) {
				foods.add(valueOf(name));
			}
		}
		return foods;
	}
}
