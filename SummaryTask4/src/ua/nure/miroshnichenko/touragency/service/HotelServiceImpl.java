package ua.nure.miroshnichenko.touragency.service;

import java.util.ArrayList;
import java.util.List;

import ua.nure.miroshnichenko.touragency.db.dao.DAOException;
import ua.nure.miroshnichenko.touragency.db.dao.DAOFactory;
import ua.nure.miroshnichenko.touragency.db.dao.HotelDAO;
import ua.nure.miroshnichenko.touragency.db.entity.Hotel;

class HotelServiceImpl implements HotelService {
	
	private DAOFactory factoryDAO = DAOFactory.getInstance();
	
	@Override
	public Hotel get(int id) throws ServiceException {
		Hotel hotel = null;

		try {
			HotelDAO hotelDAO = factoryDAO.getHotelDAO();
			hotel = hotelDAO.find(id);

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
			HotelDAO hotelDAO = factoryDAO.getHotelDAO();
			hotels = hotelDAO.findAll();

			return hotels;
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean save(Hotel hotel) throws ServiceException {
		try {
			HotelDAO hotelDAO = factoryDAO.getHotelDAO();
			if (isHotelUnique(hotel.getName())) {
				return hotelDAO.save(hotel);
			}
			
			throw new ServiceException("Hotel with this name already exist!");
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean update(Hotel hotel) throws ServiceException {
		try {
			HotelDAO hotelDAO = factoryDAO.getHotelDAO();
			if (isHotelUnique(hotel.getName())) {
				return hotelDAO.update(hotel);
			}
			
			throw new ServiceException("Hotel with this name already exist!");
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean delete(Hotel hotel) throws ServiceException {
		try {
			HotelDAO hotelDAO = factoryDAO.getHotelDAO();
			boolean result = hotelDAO.delete(hotel);

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
			hotel = dao.getHotelByName(name);

			return hotel;
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}
	
	private boolean isHotelUnique(String name) throws ServiceException {
		return getHotelByName(name) == null;
	}
}