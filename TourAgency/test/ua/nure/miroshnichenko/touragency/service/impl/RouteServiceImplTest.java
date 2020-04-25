package ua.nure.miroshnichenko.touragency.service.impl;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import ua.nure.miroshnichenko.myorm.Entity;
import ua.nure.miroshnichenko.touragency.db.dao.DAOException;
import ua.nure.miroshnichenko.touragency.db.entity.Place;
import ua.nure.miroshnichenko.touragency.db.entity.Route;
import ua.nure.miroshnichenko.touragency.service.exception.ServiceException;

public class RouteServiceImplTest extends ServiceTest {

	private RouteServiceImpl service;
	
	private final int testId = 1;
	
	private final int testPlaceFromId = 1;
	
	private final int testPlaceToId = 2;
	
	private final String testCountryFrom = "Ukraine";
	
	private final String testCityFrom = "Kharkiv";
	
	private final String testCountryTo = "Turkish";
	
	private final String testCityTo = "Kimer";
	
	@Before
	@Override
	public void init() throws Exception {
		super.init();
		
		when(routeDAO.find(anyInt())).thenAnswer(findByIdAnswer());
		when(routeDAO.findAll()).thenReturn(new ArrayList<>());
		when(routeDAO.save(any(Route.class))).thenReturn(true);
		when(routeDAO.update(any(Route.class))).thenReturn(true);
		when(routeDAO.delete(any(Route.class))).thenReturn(true);

		when(placeDAO.find(anyInt())).thenAnswer(findByIdAnswer());
		when(placeDAO.findAll()).thenReturn(new ArrayList<>());
		when(placeDAO.save(any(Place.class))).thenReturn(true);
		when(placeDAO.update(any(Place.class))).thenReturn(true);
		when(placeDAO.delete(any(Place.class))).thenReturn(true);
		
		when(routeDAO.getRouteByPlaces(anyInt(), anyInt())).thenAnswer(getRouteByPlacesAnswer());
		when(placeDAO.getPlaceByCountryAndCity(anyString(), anyString())).thenAnswer(getPlaceByCountryAndCity());
		
		service = RouteServiceImpl.getInstance();
		service.setFactoryDAO(factoryDAO);
	}
	
	@Test
	public void getByIdTest() throws ServiceException, DAOException {
		Route route = service.get(testId);
		
		verify(factoryDAO, times(1)).getRouteDAO();
		verify(routeDAO, times(1)).find(testId);
		
		assertNotNull(route);
	}
	
	@Test
	public void getUnrealByIdTest() throws ServiceException, DAOException {
		Route route = service.get(8);
		
		verify(factoryDAO, times(1)).getRouteDAO();
		verify(routeDAO, times(1)).find(8);
		
		assertNull(route);
	}
	
	@Test
	public void getAllTest() throws ServiceException, DAOException {
		List<Route> routes = service.getAll();

		verify(factoryDAO, times(1)).getRouteDAO();
		verify(routeDAO, times(1)).findAll();
		
		assertNotNull(routes);
	}
	
	
	@Test
	public void saveRouteTest() throws ServiceException, DAOException {
		Route route = new Route();
		route.setId(testId);
		route.setRouteFromId(1);
		route.setRouteToId(2);
		
		Place from = new Place();
		from.setCountry("Ukraine");
		from.setCity("Kiev");
		
		Place to = new Place();
		to.setCountry("Turkish");
		to.setCity("Alania");
		
		route.setFrom(from);
		route.setTo(to);
		
		boolean res = service.save(route);
		
		verify(factoryDAO, times(1)).getRouteDAO();
		verify(routeDAO, times(1)).save(route);
		
		assertTrue(res);
	}
	
	@Test(expected = ServiceException.class)
	public void saveDuplicateRouteTest() throws ServiceException, DAOException {
		Route route = createTestRoute();
		service.save(route);
	}
	
	@Test
	public void updateRouteTest() throws ServiceException, DAOException {
		Route route = new Route();
		route.setId(testId);
		route.setRouteFromId(1);
		route.setRouteToId(2);
		
		Place from = new Place();
		from.setCountry("Ukraine");
		from.setCity("Kiev");
		
		Place to = new Place();
		to.setCountry("Turkish");
		to.setCity("Alania");
		
		route.setFrom(from);
		route.setTo(to);
		
		boolean res = service.update(route);
		
		verify(factoryDAO, times(1)).getRouteDAO();
		verify(placeDAO, times(2)).getPlaceByCountryAndCity(anyString(), anyString());
		
		assertTrue(res);
	}
	
	@Test(expected = ServiceException.class)
	public void updateDuplicateRouteTest() throws ServiceException, DAOException {
		Route route = createTestRoute();
		
		service.update(route);
	}
	
	@Test
	public void deleteRoteTest() throws ServiceException, DAOException {
		Route route = createTestRoute();
		
		boolean res = service.delete(route);
		
		verify(factoryDAO, times(1)).getRouteDAO();
		verify(routeDAO, times(1)).delete(route);
		
		assertTrue(res);
	}
	
	@Override
	protected Answer<? extends Entity> findByIdAnswer() {
		return new Answer<Route>() {
			
			@Override
			public Route answer(InvocationOnMock invocation) throws Throwable {
				int id = invocation.getArgument(0);
				
				return id == testId ? createTestRoute() : null;
			}
		};
	}
	
	private Answer<Place> getPlaceByCountryAndCity() {
		return new Answer<Place>() {
			
			@Override
			public Place answer(InvocationOnMock invocation) throws Throwable {
				String country = invocation.getArgument(0);
				String city = invocation.getArgument(1);
				
				if (testCountryFrom.equals(country) && testCityFrom.equals(city)) {
					return createTestPlaceFrom();
				}
				
				if (testCountryTo.equals(country) && testCityTo.equals(city)) {
					return createTestPlaceTo();
				}
				
				return null;
			}
		};
	}
	
	private Answer<Route> getRouteByPlacesAnswer() {
		return new Answer<Route>() {
			
			@Override
			public Route answer(InvocationOnMock invocation) throws Throwable {
				int placeFromId = invocation.getArgument(0);
				int placeToId = invocation.getArgument(1);
				
				return placeFromId == testPlaceFromId &&
						placeToId == testPlaceToId ? createTestRoute() : null;
			}
		};	
	}
	
	private Route createTestRoute() {
		Route route = new Route();
		route.setId(testId);
		route.setRouteFromId(testPlaceFromId);
		route.setRouteToId(testPlaceToId);
		
		route.setFrom(createTestPlaceFrom());
		route.setTo(createTestPlaceTo());
		
		return route;
	}
	
	private Place createTestPlaceFrom() {
		Place place = new Place();
		place.setId(testPlaceFromId);
		place.setCountry(testCountryFrom);
		place.setCity(testCityFrom);
		
		return place;
	}
	
	private Place createTestPlaceTo() {
		Place place = new Place();
		place.setId(testPlaceToId);
		place.setCountry(testCountryTo);
		place.setCity(testCityTo);
		
		return place;
	}
}
