package ua.nure.miroshnichenko.touragency.service.impl;

import static org.mockito.Mockito.when;

import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;

import ua.nure.miroshnichenko.myorm.Entity;
import ua.nure.miroshnichenko.touragency.db.dao.CommentDAO;
import ua.nure.miroshnichenko.touragency.db.dao.DAOFactory;
import ua.nure.miroshnichenko.touragency.db.dao.FacilityDAO;
import ua.nure.miroshnichenko.touragency.db.dao.HotelDAO;
import ua.nure.miroshnichenko.touragency.db.dao.PlaceDAO;
import ua.nure.miroshnichenko.touragency.db.dao.ReservationDAO;
import ua.nure.miroshnichenko.touragency.db.dao.RouteDAO;
import ua.nure.miroshnichenko.touragency.db.dao.ServicingDAO;
import ua.nure.miroshnichenko.touragency.db.dao.StatisticDAO;
import ua.nure.miroshnichenko.touragency.db.dao.TourDAO;
import ua.nure.miroshnichenko.touragency.db.dao.TransportDAO;
import ua.nure.miroshnichenko.touragency.db.dao.UserDAO;

public abstract class ServiceTest {

	@Mock
	public DAOFactory factoryDAO;

	@Mock
	public UserDAO userDAO;
	
	@Mock
	public CommentDAO commentDAO;

	@Mock
	public FacilityDAO facilityDAO;
	
	@Mock
	public HotelDAO hotelDAO;
	
	@Mock
	public PlaceDAO placeDAO;
	
	@Mock
	public ReservationDAO reservationDAO;
	
	@Mock
	public RouteDAO routeDAO;
	
	@Mock
	public ServicingDAO servicingDAO;
	
	@Mock
	public StatisticDAO statisticDAO;
	
	@Mock
	public TourDAO tourDAO;
	
	@Mock
	public TransportDAO transportDAO;
	
	@Before
	public void init() throws Exception {
		MockitoAnnotations.initMocks(this);

		when(factoryDAO.getRouteDAO()).thenReturn(routeDAO);
		when(factoryDAO.getUserDAO()).thenReturn(userDAO);
		when(factoryDAO.getCommentDAO()).thenReturn(commentDAO);
		when(factoryDAO.getFacilityDAO()).thenReturn(facilityDAO);
		when(factoryDAO.getHotelDAO()).thenReturn(hotelDAO);
		when(factoryDAO.getPlaceDAO()).thenReturn(placeDAO);
		when(factoryDAO.getReservationDAO()).thenReturn(reservationDAO);
		when(factoryDAO.getServicingDAO()).thenReturn(servicingDAO);
		when(factoryDAO.getStatisticDAO()).thenReturn(statisticDAO);
		when(factoryDAO.getTransportDAO()).thenReturn(transportDAO);
		when(factoryDAO.geTourDAO()).thenReturn(tourDAO);
	}
	
	protected abstract Answer<? extends Entity> findByIdAnswer();
}
