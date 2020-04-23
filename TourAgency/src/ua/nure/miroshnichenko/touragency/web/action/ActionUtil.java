package ua.nure.miroshnichenko.touragency.web.action;

import java.io.File;
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
import ua.nure.miroshnichenko.touragency.service.CRUDService;
import ua.nure.miroshnichenko.touragency.service.exception.ServiceException;

public final class ActionUtil {
	
	private static final Gson gson = new Gson();

	private ActionUtil() {
	}
	
	public static <T extends Entity> List<T> getAllEntities(CRUDService<T> crudService) throws ActionException {
		List<T> entities;
		try {
			entities = crudService.getAll();
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ActionException(e);
		}
		return entities;
	}
	
	public static void setAllEntitiesJsonInAttribute(HttpServletRequest req, CRUDService<?> crudService, String nameAttr) throws ActionException {
		List<? extends Entity> tours = ActionUtil.getAllEntities(crudService);

		String toursJson = ActionUtil.entitiesToJson(tours);
		req.setAttribute(nameAttr, toursJson);
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
	
	public static List<String> getHotelPhotos(int hotelId, HttpServletRequest request) throws IOException {
		String path = getPhotosFolderPath(request);
		
		List<String> photos = new ArrayList<>();
		
		try (Stream<Path> paths = Files.list(Paths.get(path))) {
			paths
				.filter(p -> photoBelongToHotel(hotelId, p))
				.forEach(p -> photos.add(p.getFileName().toString()));
		}
		return photos;
	}
	
	public static String getFirstPhoto(int hotelId, HttpServletRequest request) throws IOException {
		String path = getPhotosFolderPath(request);
		
		String photo = "";
		try (Stream<Path> paths = Files.list(Paths.get(path))) {
			Optional<Path> optional = paths
				.filter(p -> photoBelongToHotel(hotelId, p))
				.findFirst();
			
			if (optional.isPresent()) {
				photo = optional.get().getFileName().toString();
			}
		}
		return photo;
	}
	
	public static void deleteHotelPhotos(int hotelId, HttpServletRequest request) throws IOException {
		String path = getPhotosFolderPath(request);

		try (Stream<Path> paths = Files.list(Paths.get(path))) {
			paths
				.filter(p -> photoBelongToHotel(hotelId, p))
				.forEach(p -> p.toFile().delete());
		}
	}
	
	public static void deleteHotelPhotos(String[] photos, HttpServletRequest request) {
		String path = getPhotosFolderPath(request);
		
		for (String photo : photos) {
			File file = new File(path + File.separator + photo);
			file.delete();
		}
	}
	
	private static boolean photoBelongToHotel(int hotelId, Path path) {
		String pathName = path.toString();
		String[] pathNameParts = pathName.split("_");
		return pathNameParts[1].equals(String.valueOf(hotelId));
	}
}
