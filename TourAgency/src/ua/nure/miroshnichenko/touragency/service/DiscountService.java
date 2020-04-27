package ua.nure.miroshnichenko.touragency.service;

import ua.nure.miroshnichenko.touragency.service.exception.ServiceException;

/**
 * Service control information about dsicounts.
 * 
 * @author Miroshnichenko Y. D.
 */
public interface DiscountService {

	/**
	 * Set a discount step for all users.
	 * 
	 * @param step value of discount step.
	 * 	
	 * @return true if discount step has been changed.
	 * 
	 * @throws ServiceException
	 */
	boolean setDiscountStep(double step) throws ServiceException;

	/**
	 * Set a max discount for a specific tour.
	 * 
	 * @param tourId id of tour.
	 * @param value value of max discount.	
	 * 
	 * @return true if max discount has been changed.
	 * 
	 * @throws ServiceException
	 */
	boolean setMaxDiscount(int tourId, double value) throws ServiceException;

	/**
	 * Get a current discount step of all users.
	 * 
	 * @return value of current discount step
	 * 
	 * @throws ServiceException
	 */
	double getDiscountStep() throws ServiceException;
}
