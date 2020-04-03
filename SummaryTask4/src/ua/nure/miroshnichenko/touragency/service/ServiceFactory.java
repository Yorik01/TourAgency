package ua.nure.miroshnichenko.touragency.service;

public class ServiceFactory {

	private static ServiceFactory instance;

	public static synchronized ServiceFactory getInstance() {
		if (instance == null) {
			instance = new ServiceFactory();
		}
		return instance;
	}

	public AuthentificationService getAuthentificationService() {
		return new AuthentificationServiceImpl();
	}

	public TourService getTourService() {
		return new TourServiceImpl();
	}

	public BanningService getBanningService() {
		return new BanningServiceImpl();
	}

	public DiscountService getDiscountService() {
		return new DiscountServiceImpl();
	}
	
	public HotelService getHotelService() {
		return new HotelServiceImpl();
	}
	
	public TransportService getTransportService() {
		return new TransportServiceImpl();
	}
	
	public RouteService getRouteService() {
		return new RouteServiceImpl();
	}
}
