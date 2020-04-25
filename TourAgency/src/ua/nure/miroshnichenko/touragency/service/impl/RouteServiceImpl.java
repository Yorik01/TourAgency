package ua.nure.miroshnichenko.touragency.service.impl;

import java.util.ArrayList;
import java.util.List;

import ua.nure.miroshnichenko.touragency.db.dao.DAOException;
import ua.nure.miroshnichenko.touragency.db.dao.DAOFactory;
import ua.nure.miroshnichenko.touragency.db.dao.PlaceDAO;
import ua.nure.miroshnichenko.touragency.db.dao.RouteDAO;
import ua.nure.miroshnichenko.touragency.db.entity.Place;
import ua.nure.miroshnichenko.touragency.db.entity.Route;
import ua.nure.miroshnichenko.touragency.service.RouteService;
import ua.nure.miroshnichenko.touragency.service.exception.ServiceException;

public class RouteServiceImpl implements RouteService {

	private static RouteServiceImpl instance;
	
	private DAOFactory factoryDAO;
	
	private RouteServiceImpl () {
		factoryDAO = DAOFactory.getInstance();
	}
	
	public static synchronized RouteServiceImpl getInstance() {
		if(instance == null) {
			instance = new RouteServiceImpl();
		}
		return instance;
	}
	
	/**
	 * Set the DAOFactory implementation using the setter.
	 * It is used for mock test.
	 */
	public void setFactoryDAO(DAOFactory factoryDAO) {
		this.factoryDAO = factoryDAO;
	}
	
	@Override
	public Route get(int id) throws ServiceException {
		Route route = null;

		try {
			RouteDAO routeDAO = factoryDAO.getRouteDAO();
			route = routeDAO.find(id);

			return route;
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Route> getAll() throws ServiceException {
		List<Route> routes = new ArrayList<>();

		try {
			RouteDAO routeDAO = factoryDAO.getRouteDAO();
			routes = routeDAO.findAll();

			return routes;
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean save(Route route) throws ServiceException {
		try {
			RouteDAO routeDAO = factoryDAO.getRouteDAO();
			if (isRouteUnique(route)) {
				return routeDAO.save(route);
			}
			
			throw new ServiceException("This route already exist!");
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean update(Route route) throws ServiceException {
		try {
			RouteDAO routeDAO = factoryDAO.getRouteDAO();
			
			if (isRouteUnique(route)) {
				return routeDAO.update(route);
			}
			
			throw new ServiceException("This route already exist!");
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean delete(Route route) throws ServiceException {
		try {
			RouteDAO routeDAO = factoryDAO.getRouteDAO();
			boolean result = routeDAO.delete(route);

			return result;
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	@Override
	public Place getPlaceByCountryAndCity(String country, String city) throws ServiceException {
		Place place = null;

		try {
			PlaceDAO dao = factoryDAO.getPlaceDAO();
			place = dao.getPlaceByCountryAndCity(country, city);

			return place;
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}
	
	@Override
	public List<Place> getAllPlaces() throws ServiceException {
		List<Place> places = null;
		
		try {
			PlaceDAO dao = factoryDAO.getPlaceDAO();
			places = dao.findAll();
			
			return places;
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}
	
	@Override
	public Route getRouteByPlaces(int placeFromId, int placeToId) throws ServiceException {
		Route route = null;
		
		try {
			RouteDAO routeDAO = factoryDAO.getRouteDAO();
			route = routeDAO.getRouteByPlaces(placeFromId, placeToId);
			
			return route;
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}
	
	private boolean isRouteUnique(Route route) throws ServiceException {
		Place from = route.getFrom();
		Place to = route.getTo();
		
		Place oldFrom = getPlaceByCountryAndCity(from.getCountry(), from.getCity());
		Place oldTo = getPlaceByCountryAndCity(to.getCountry(), to.getCity());
		
		if (oldFrom == null) {
			return true;
		}
		
		if (oldTo == null) {
			return true;
		}
		
		Route oldRoute = getRouteByPlaces(oldFrom.getId(), oldTo.getId());
		
		return oldRoute == null;
	}
}
