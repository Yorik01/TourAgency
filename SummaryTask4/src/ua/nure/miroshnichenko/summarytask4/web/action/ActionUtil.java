package ua.nure.miroshnichenko.summarytask4.web.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import ua.nure.miroshnichenko.summarytask4.db.entity.Place;

public final class ActionUtil {
	
	private static final Gson gson = new Gson();
	
	private ActionUtil() {
	}
	
	public static String PlacesToJson(List<Place> places) {
		return gson.toJson(placesToMap(places));
	}
	
	public static Map<String, List<String>> placesToMap(List<Place> places) {
		Map<String, List<String>> mapPlaces = new HashMap<>();
		for(Place place : places) {
			String country = place.getCountry();
			String city = place.getCity();
			
			if(mapPlaces.containsKey(country)) {
				List<String> cities = mapPlaces.get(country);
				cities.add(city);
			} else {
				List<String> cities = new ArrayList<>();
				cities.add(city);
				
				mapPlaces.put(country, cities);
			}
		}
		return mapPlaces;
	}
}
