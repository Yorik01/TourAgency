package ua.nure.miroshnichenko.touragency.service;

import ua.nure.miroshnichenko.touragency.service.exception.ServiceException;

public interface DiscountService {

	boolean setDiscountStep(double step) throws ServiceException;
	
	boolean setMaxDiscount(int tourId, double value) throws ServiceException;
}
