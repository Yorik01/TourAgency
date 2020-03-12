package ua.nure.miroshnichenko.summarytask4.service;

import ua.nure.miroshnichenko.summarytask4.db.entity.Tour;
import ua.nure.miroshnichenko.summarytask4.db.entity.User;

public interface DiscountService {

	boolean setMaxDiscount(Tour tour, int value) throws ServiceException;
	
	boolean riseDiscount(User user, Tour tour) throws ServiceException;
}
