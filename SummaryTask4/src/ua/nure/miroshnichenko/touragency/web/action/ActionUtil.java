package ua.nure.miroshnichenko.touragency.web.action;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;

import ua.nure.miroshnichenko.myorm.Entity;
import ua.nure.miroshnichenko.touragency.db.entity.Place;

public final class ActionUtil {
	
	private static final Gson gson = new Gson();

	private ActionUtil() {
	}
	
	public static String entitiesToJson(List<? extends Entity> entities) {
		return gson.toJson(entities);
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
	
	public static String getPhotosFolderPath(HttpServletRequest request) {
		return request.getServletContext().getInitParameter("photo-storage");
	}
	
	public static List<String> getHotelPhotos(int hotelId, final String path) throws IOException {
		List<String> photos = new ArrayList<>();
		
		try (Stream<Path> paths = Files.list(Paths.get(path))) {
			paths
				.forEach(p -> {
					String pathName = p.toString();
					String[] pathNameParts = pathName.split("_");
					if (pathNameParts[1].equals(String.valueOf(hotelId))) {
						photos.add(p.getFileName().toString());
					}
				});
		}
		return photos;
	}
	
	public static String getFirstPhoto(int hotelId, final String path) throws IOException {
		String photo = "";
		try (Stream<Path> paths = Files.list(Paths.get(path))) {
			Optional<Path> optional = paths.findFirst();
			if (optional.isPresent()) {
				photo = optional.get().getFileName().toString();
			}
		}
		return photo;
	}
}
