package ua.nure.miroshnichenko.touragency.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.miroshnichenko.touragency.db.dao.DAOException;
import ua.nure.miroshnichenko.touragency.db.dao.DAOFactory;
import ua.nure.miroshnichenko.touragency.db.dao.HotelDAO;
import ua.nure.miroshnichenko.touragency.db.entity.Hotel;
import ua.nure.miroshnichenko.touragency.service.HotelService;
import ua.nure.miroshnichenko.touragency.service.exception.ServiceException;
import ua.nure.miroshnichenko.touragency.service.impl.constants.ExceptionMessages;
import ua.nure.miroshnichenko.touragency.service.impl.constants.LogginMessages;

class HotelServiceImpl implements HotelService {
	
	private Logger LOG = Logger.getLogger(HotelServiceImpl.class);
	
	private DAOFactory factoryDAO = DAOFactory.getInstance();
	
	private static HotelServiceImpl instance;
	
	private HotelServiceImpl () {
		factoryDAO = DAOFactory.getInstance();
	}
	
	public static synchronized HotelServiceImpl getInstance() {
		if(instance == null) {
			instance = new HotelServiceImpl();
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
	public Hotel get(int id) throws ServiceException {
		Hotel hotel = null;

		try {
			HotelDAO hotelDAO = factoryDAO.getHotelDAO();
			hotel = hotelDAO.find(id);

			return hotel;
		} catch (DAOException e) {
			LOG.error(e);
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
			LOG.error(e);
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean save(Hotel hotel) throws ServiceException {
		try {
			HotelDAO hotelDAO = factoryDAO.getHotelDAO();
			if (isHotelUnique(hotel.getName())) {
				boolean res = hotelDAO.save(hotel);
				
				if (res) {
					LOG.trace(LogginMessages.HOTEL_CREATED);
				}
				return res;
			}
			
			throw new ServiceException(ExceptionMessages.HOTEL_EXIST);
		} catch (DAOException e) {
			LOG.error(e);
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean update(Hotel hotel) throws ServiceException {
		try {
			HotelDAO hotelDAO = factoryDAO.getHotelDAO();
			
			Hotel oldHotel = getHotelByName(hotel.getName());
			
			if (oldHotel == null || oldHotel.getId() == hotel.getId()) {
				boolean res = hotelDAO.update(hotel);
				
				if (res) {
					LOG.trace(String.format(
							LogginMessages.HOTEL_UPDATE, hotel.getId()));
				}
				return res;
			}
			
			throw new ServiceException(ExceptionMessages.HOTEL_EXIST);
		} catch (DAOException e) {
			LOG.error(e);
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean delete(Hotel hotel) throws ServiceException {
		try {
			HotelDAO hotelDAO = factoryDAO.getHotelDAO();
			boolean res = hotelDAO.delete(hotel);

			if (res) {
				LOG.trace(String.format(
						LogginMessages.HOTEL_DELETE, hotel.getId()));
			}
			
			return res;
		} catch (DAOException e) {
			LOG.error(e);
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
			LOG.error(e);
			throw new ServiceException(e);
		}
	}
	
	private boolean isHotelUnique(String name) throws ServiceException {
		return getHotelByName(name) == null;
	}
}