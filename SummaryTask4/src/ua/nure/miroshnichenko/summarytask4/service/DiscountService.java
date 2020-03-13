package ua.nure.miroshnichenko.summarytask4.service;

public interface DiscountService {

	boolean setMaxDiscount(int tourId, double value) throws ServiceException;
	
	boolean riseDiscount(int userId, int tourId) throws ServiceException;
}
