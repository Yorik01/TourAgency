package ua.nure.miroshnichenko.touragency.service;

import ua.nure.miroshnichenko.touragency.db.entity.Hotel;

public interface HotelService extends CRUDService<Hotel> {

	Hotel getHotelByName(String name) throws ServiceException;
}
