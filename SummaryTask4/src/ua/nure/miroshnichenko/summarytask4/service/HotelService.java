package ua.nure.miroshnichenko.summarytask4.service;

import ua.nure.miroshnichenko.summarytask4.db.entity.Hotel;

public interface HotelService extends CRUDService<Hotel> {

	Hotel getHotelByName(String name) throws ServiceException;
}
