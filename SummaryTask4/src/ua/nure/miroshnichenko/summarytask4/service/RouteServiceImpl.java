package ua.nure.miroshnichenko.summarytask4.service;

import java.util.ArrayList;
import java.util.List;

import ua.nure.miroshnichenko.summarytask4.db.dao.DAO;
import ua.nure.miroshnichenko.summarytask4.db.dao.DAOException;
import ua.nure.miroshnichenko.summarytask4.db.dao.DAOFactory;
import ua.nure.miroshnichenko.summarytask4.db.dao.PlaceDAO;
import ua.nure.miroshnichenko.summarytask4.db.entity.Place;
import ua.nure.miroshnichenko.summarytask4.db.entity.Route;

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
			DAO<Route> dao = (DAO<Route>) factoryDAO.getDAO("route");
			route = (Route) dao.find(id);

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
			DAO<Route> dao = (DAO<Route>) factoryDAO.getDAO("route");
			routes = dao.findAll();

			return routes;
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean save(Route entity) throws ServiceException {
		try {
			DAO<Route> dao = (DAO<Route>) factoryDAO.getDAO("route");
			boolean result = dao.save(entity);

			return result;
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean update(Route entity) throws ServiceException {
		try {
			DAO<Route> dao = (DAO<Route>) factoryDAO.getDAO("route");
			boolean result = dao.update(entity);

			return result;
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean delete(Route entity) throws ServiceException {
		try {
			DAO<Route> dao = (DAO<Route>) factoryDAO.getDAO("route");
			boolean result = dao.delete(entity);

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
}
