package ua.nure.miroshnichenko.touragency.web.action;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.google.gson.Gson;

import ua.nure.miroshnichenko.myorm.Entity;
import ua.nure.miroshnichenko.touragency.db.entity.Place;
import ua.nure.miroshnichenko.touragency.db.entity.Tour;
import ua.nure.miroshnichenko.touragency.service.CRUDService;
import ua.nure.miroshnichenko.touragency.service.DiscountService;
import ua.nure.miroshnichenko.touragency.service.exception.ServiceException;

/**
 * The class contains the utility methods which are used in 'actions layer'. 
 * 
 * @author Miroshnichenko Y. D.
 */
public final class ActionUtil {
	
	private static final Gson gson = new Gson();

	private ActionUtil() {
	}
	
	/**
	 * The method allow to get all entities from crud service.
	 * 
	 * @param crudService service extended 
	 * {@link ua.nure.miroshnichenko.touragency.service.CrudService}
	 * 
	 * @return list of entities
	 */
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
	
	/**
	 * The method fetches all entities from crudService then convert 
	 * these objects in JSON format and insert them into attribute 
	 * of request.
	 *  
	 *  @param req HttpServletRequest.
	 *  @param crudService service extended 
	 * {@link ua.nure.miroshnichenko.touragency.service.CrudService}
	 *  
	 *  @param nameAttr of the attribute to which the object will be inserted .
	 */
	public static void setAllEntitiesJsonInAttribute(HttpServletRequest req, CRUDService<?> crudService, String nameAttr) throws ActionException {
		List<? extends Entity> tours = ActionUtil.getAllEntities(crudService);

		String toursJson = ActionUtil.entitiesToJson(tours);
		req.setAttribute(nameAttr, toursJson);
	}
	
	/**
	 * The method fetches the current discount step from 
	 * {@link ua.nure.miroshnichenko.touragency.service.DiscountService}
	 * and set it into request attribute.
	 * 
	 * @param discountService {@link ua.nure.miroshnichenko.touragency.service.DiscountService}
	 * @param req HttpServletRequest.
	 */
	public static void setDiscountStepInAttribute(DiscountService discountService, HttpServletRequest req) throws ActionException {
		try {
			double discountStep = discountService.getDiscountStep();
			req.setAttribute("currentDiscountStep", discountStep);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ActionException(e);
		}
	}
	
	
	/**
	 * The method converts a list of entities in JSON format.
	 * 
	 *  @param entities a list of entities.
	 *  
	 *  @return a JSON format of entities.
	 */
	public static String entitiesToJson(List<? extends Entity> entities) {
		return gson.toJson(entities);
	}
	
	public static String PlacesToJson(List<Place> places) {
		return gson.toJson(placesToMap(places));
	}
	
	/**
	 * The method converts a list of places in a map 
	 * where a country is a key and a list of cities is value.
	 * 
	 * @param places the list of places.
	 * @return a map.
	 */
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
	
	public static void uploadImgs(HttpServletRequest req, HttpServletResponse res, int hotelId)
			throws IOException, ServletException, ActionException {
		List<Part> fileParts = req.getParts().stream()
				.filter(part -> "photo".equals(part.getName()))
				.collect(Collectors.toList());
		
		if (!fileParts.isEmpty() && fileParts.get(0).getSize() != 0) {
			for (Part part : fileParts) {
				UUID uuid = UUID.randomUUID();
				String fileName = "hotelPhoto_" + hotelId + "_" + uuid;
		        
				String path = getPhotosFolderPath(req);
				OutputStream out = null;
				InputStream fileContent = null;
				File file = new File(path + File.separator + fileName);
				
				try {
					if (file.createNewFile()) {
						out = new FileOutputStream(file);
						fileContent = part.getInputStream();
	
						int read = 0;
						final byte[] bytes = new byte[1024];
	
						while ((read = fileContent.read(bytes)) != -1) {
							out.write(bytes, 0, read);
						}
					} else {
						throw new ActionException("Cannot upload file!");
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
					throw new ActionException(e);
				} finally {
					if (out != null) {
						out.close();
					}
	
					if (fileContent != null) {
						fileContent.close();
					}
				}
			}
		}
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
	
	public static void setToursPhotos(List<Tour> tours, HttpServletRequest req) throws IOException {
		for (Tour tour : tours) {
			String photo = ActionUtil.getFirstPhoto(tour.getHotelId(), req);
			tour.setPhotoLink(photo);
		}
	}
	
	public static void deleteAllHotelPhotos(int hotelId, HttpServletRequest request) throws IOException {
		String path = getPhotosFolderPath(request);

		try (Stream<Path> paths = Files.list(Paths.get(path))) {
			paths
				.filter(p -> photoBelongToHotel(hotelId, p))
				.forEach(p -> p.toFile().delete());
		}
	}
	
	public static void deleteHotelPhotos(HttpServletRequest request) {
		String path = getPhotosFolderPath(request);
		String[] photos = request.getParameterValues("deletedPhotos");
		
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
