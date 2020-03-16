package ua.nure.miroshnichenko.summarytask4.service;

import java.util.ArrayList;
import java.util.List;

import ua.nure.miroshnichenko.summarytask4.db.dao.DAO;
import ua.nure.miroshnichenko.summarytask4.db.dao.DAOException;
import ua.nure.miroshnichenko.summarytask4.db.dao.DAOFactory;
import ua.nure.miroshnichenko.summarytask4.db.dao.HotelDAO;
import ua.nure.miroshnichenko.summarytask4.db.entity.Hotel;
import ua.nure.miroshnichenko.summarytask4.db.entity.Tour;

class HotelServiceImpl implements HotelService {
	
	private static HotelService instance;
	
	private DAOFactory factoryDAO = DAOFactory.getInstance();
	
	public static synchronized HotelService getInstance() {
		if(instance == null) {
			instance = new HotelServiceImpl();
		}
		return instance;
	}

	@Override
	public Hotel get(int id) throws ServiceException {
		Hotel hotel = null;

		try {
			DAO<Hotel> dao = (DAO<Hotel>) factoryDAO.getDAO("hotel");
			hotel = (Hotel) dao.find(id);

			return hotel;
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Hotel> getAll() throws ServiceException {
		List<Hotel> hotels = new ArrayList<>();

		try {
			DAO<Hotel> dao = (DAO<Hotel>) factoryDAO.getDAO("hotel");
			hotels = dao.findAll();

			return hotels;
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean save(Hotel entity) throws ServiceException {
		try {
			DAO<Hotel> dao = (DAO<Hotel>) factoryDAO.getDAO("hotel");
			boolean result = dao.save(entity);

			return result;
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean update(Hotel entity) throws ServiceException {
		try {
			DAO<Hotel> dao = (DAO<Hotel>) factoryDAO.getDAO("hotel");
			boolean result = dao.update(entity);

			return result;
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean delete(Hotel entity) throws ServiceException {
		try {
			DAO<Hotel> dao = (DAO<Hotel>) factoryDAO.getDAO("hotel");
			boolean result = dao.delete(entity);

			return result;
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	@Override
	public Hotel getHotelByName(String name) throws ServiceException {
		Hotel hotel = null;

		try {
			HotelDAO dao = factoryDAO.getHotelDAO();
			hotel = (Hotel) dao.getHotelByName(name);

			return hotel;
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}
}
