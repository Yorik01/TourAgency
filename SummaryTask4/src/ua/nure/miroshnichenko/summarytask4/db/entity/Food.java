package ua.nure.miroshnichenko.summarytask4.db.entity;

import java.util.ArrayList;
import java.util.List;

public enum Food {
	AL, FB, HB, BB, OB;

	public static List<Food> getFoods(String[] names) {
		List<Food> foods = new ArrayList<>();
		for (String name : names) {
			foods.add(valueOf(name));
		}
		return foods;
	}
}
