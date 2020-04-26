package ua.nure.miroshnichenko.touragency.service.impl;

import ua.nure.miroshnichenko.touragency.service.AuthentificationService;
import ua.nure.miroshnichenko.touragency.service.BanningService;
import ua.nure.miroshnichenko.touragency.service.CommentService;
import ua.nure.miroshnichenko.touragency.service.DiscountService;
import ua.nure.miroshnichenko.touragency.service.HotelService;
import ua.nure.miroshnichenko.touragency.service.RouteService;
import ua.nure.miroshnichenko.touragency.service.StatisticService;
import ua.nure.miroshnichenko.touragency.service.TourService;
import ua.nure.miroshnichenko.touragency.service.TransportService;

public class ServiceFactory {

	private static ServiceFactory instance;
	
	private ServiceFactory() {
	}
	
	public static synchronized ServiceFactory getInstance() {
		if (instance == null) {
			instance = new ServiceFactory();
		}
		return instance;
	}

	public AuthentificationService getAuthentificationService() {
		return AuthentificationServiceImpl.getInstance();
	}

	public TourService getTourService() {
		return TourServiceImpl.getInstance();
	}

	public BanningService getBanningService() {
		return new BanningServiceImpl();
	}

	public DiscountService getDiscountService() {
		return new DiscountServiceImpl();
	}
	
	public HotelService getHotelService() {
		return HotelServiceImpl.getInstance();
	}
	
	public TransportService getTransportService() {
		return TransportServiceImpl.getInstance();
	}
	
	public RouteService getRouteService() {
		return RouteServiceImpl.getInstance();
	}
	
	public CommentService getCommentService() {
		return CommentServiceImpl.getInstance();
	}

	public StatisticService getStatisticService() {
		return new StatisticServiceImpl();
	}
}
