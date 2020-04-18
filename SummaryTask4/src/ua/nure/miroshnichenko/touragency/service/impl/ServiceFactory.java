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
	
	public CommentService getCommentService() {
		return new CommentServiceImpl();
	}

	public StatisticService getStatisticService() {
		return new StatisticServiceImpl();
	}
}
