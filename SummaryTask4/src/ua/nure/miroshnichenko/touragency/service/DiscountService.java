package ua.nure.miroshnichenko.touragency.service;

public interface DiscountService {

	boolean setDiscountStep(double step) throws ServiceException;
	
	boolean setMaxDiscount(int tourId, double value) throws ServiceException;
}
