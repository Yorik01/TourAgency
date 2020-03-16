package ua.nure.miroshnichenko.summarytask4.service;

public class ServiceFactory {

	private static ServiceFactory instance;

	public static synchronized ServiceFactory getInstance() {
		if (instance == null) {
			instance = new ServiceFactory();
		}
		return instance;
	}

	public AuthentificationService getAuthentificationService() {
		return AuthentificationServiceImpl.getInstance();
	}

	public TourService geTourService() {
		return TourServiceImpl.getInstance();
	}

	public BanningService getBanningService() {
		return BanningServiceImpl.getInstance();
	}

	public DiscountService getDiscountService() {
		return DiscountServiceImpl.getInstance();
	}
	
	public HotelService getHotelService() {
		return HotelServiceImpl.getInstance();
	}
	
	public TransportService getTransportService() {
		return TransportServiceImpl.getInstance();
	}
}
