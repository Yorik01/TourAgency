package ua.nure.miroshnichenko.touragency.service;

import java.util.ArrayList;
import java.util.List;

import ua.nure.miroshnichenko.touragency.db.dao.DAOException;
import ua.nure.miroshnichenko.touragency.db.dao.DAOFactory;
import ua.nure.miroshnichenko.touragency.db.dao.PlaceDAO;
import ua.nure.miroshnichenko.touragency.db.dao.RouteDAO;
import ua.nure.miroshnichenko.touragency.db.entity.Place;
import ua.nure.miroshnichenko.touragency.db.entity.Route;

public class RouteServiceImpl implements RouteService {

	private static RouteService instance;
	
	private DAOFactory factoryDAO = DAOFactory.getInstance();
	
	public static synchronized RouteService getInstance() {
		if(instance == null) {
			instance = new RouteServiceImpl();
		}
		return instance;
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
	public boolean save(Route entity) throws ServiceException {
		try {
			RouteDAO routeDAO = factoryDAO.getRouteDAO();
			boolean result = routeDAO.save(entity);

			return result;
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean update(Route entity) throws ServiceException {
		try {
			RouteDAO routeDAO = factoryDAO.getRouteDAO();
			boolean result = routeDAO.update(entity);

			return result;
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean delete(Route entity) throws ServiceException {
		try {
			RouteDAO routeDAO = factoryDAO.getRouteDAO();
			boolean result = routeDAO.delete(entity);

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
}
