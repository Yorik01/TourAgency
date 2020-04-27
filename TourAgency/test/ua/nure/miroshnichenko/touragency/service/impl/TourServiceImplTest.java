package ua.nure.miroshnichenko.touragency.service.impl;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import ua.nure.miroshnichenko.myorm.Entity;
import ua.nure.miroshnichenko.touragency.db.dao.DAOException;
import ua.nure.miroshnichenko.touragency.db.entity.Reservation;
import ua.nure.miroshnichenko.touragency.db.entity.Tour;
import ua.nure.miroshnichenko.touragency.db.entity.User;
import ua.nure.miroshnichenko.touragency.service.exception.ServiceException;

public class TourServiceImplTest extends ServiceTest {

private TourServiceImpl service;
	
	private static final int testId = 1;
	
	private static final Date testStartDate = Date.valueOf("2020-04-01");
	
	private static final Date testEndDate = Date.valueOf("2020-04-10");
	
	private static final int testHotelId = 2;
	
	private static final int testReservationId = 1;
	
	private static final int testUserId = 1;
	
	@Override
	public void init() throws Exception {
		super.init();
		
		when(tourDAO.find(anyInt())).thenAnswer(findByIdAnswer());
		when(tourDAO.findAll()).thenReturn(new ArrayList<>());
		when(tourDAO.save(any(Tour.class))).thenReturn(true);
		when(tourDAO.update(any(Tour.class))).thenReturn(true);
		when(tourDAO.delete(any(Tour.class))).thenReturn(true);

		when(tourDAO.getTourByDateAndHotelId(
				any(Date.class), any(Date.class), anyInt()))
					.thenAnswer(getTourByDateAndHotelIdAnswer());

		when(reservationDAO.find(anyInt())).thenAnswer(getResrvationByIdAnswer());
		when(reservationDAO.getTourReservations(any(Tour.class)))
			.thenAnswer(getTourReservationsAnswer());
		when(reservationDAO.getUserReservations(any(User.class)))
			.thenAnswer(getUserReservationsAnswer());
		when(reservationDAO.findAll()).thenReturn(new ArrayList<>());
		when(reservationDAO.update(any(Reservation.class))).thenReturn(true);
		
		service = TourServiceImpl.getInstance();
		service.setFactoryDAO(factoryDAO);
	}
	
	@Test
	public void getByIdTest() throws ServiceException, DAOException {
		Tour tour = service.get(testId);
		
		verify(factoryDAO, times(1)).geTourDAO();
		verify(tourDAO, times(1)).find(testId);
		
		assertNotNull(tour);
	}
	
	@Test
	public void getUnrealById() throws DAOException, ServiceException {
		final int unrealId= 19;
		
		Tour tour = service.get(unrealId);
		
		verify(factoryDAO, times(1)).geTourDAO();
		verify(tourDAO, times(1)).find(unrealId);
		
		assertNull(tour);
	}
	
	@Test
	public void getAllTest() throws ServiceException, DAOException {
		List<Tour> tours = service.getAll();

		verify(factoryDAO, times(1)).geTourDAO();
		verify(tourDAO, times(1)).findAll();
		
		assertNotNull(tours);
	}
	
	@Test
	public void saveTest() throws ServiceException, DAOException {
		final int hotelId = 4;
		
		Tour tour = new Tour();
		tour.setHotelId(hotelId);
		tour.setStartDate(testStartDate);
		tour.setEndDate(testEndDate);
		
		boolean res = service.save(tour);

		verify(factoryDAO, times(2)).geTourDAO();
		verify(tourDAO, times(1)).getTourByDateAndHotelId(
				testStartDate, testEndDate, hotelId);
		verify(tourDAO, times(1)).save(tour);
		
		assertTrue(res);
	}
	
	@Test(expected = ServiceException.class)
	public void saveDuplicateTest() throws ServiceException, DAOException {
		service.save(createTestTour());
	}
	
	@Test
	public void updateTest() throws DAOException, ServiceException {
		final int hotelId = 4;
		
		Tour tour = new Tour();
		tour.setId(testId);
		tour.setHotelId(hotelId);
		tour.setStartDate(testStartDate);
		tour.setEndDate(testEndDate);

		boolean res = service.update(tour);

		verify(factoryDAO, times(2)).geTourDAO();
		verify(tourDAO, times(1)).getTourByDateAndHotelId(
				testStartDate, testEndDate, hotelId);
		verify(tourDAO, times(1)).update(tour);
		
		assertTrue(res);
	}
	
	@Test(expected = ServiceException.class)
	public void updateDuplicateTest() throws ServiceException, DAOException {
		Tour tour = new Tour();
		tour.setId(3);
		tour.setHotelId(testHotelId);
		tour.setStartDate(testStartDate);
		tour.setEndDate(testEndDate);
		
		service.update(tour);
	}
	
	@Test
	public void deleteTest() throws ServiceException, DAOException {
		Tour hotel = createTestTour();
		
		boolean res = service.delete(hotel);
		
		verify(factoryDAO, times(1)).geTourDAO();
		verify(tourDAO, times(1)).delete(hotel);
		
		assertTrue(res);
	}
	
	@Test
	public void getTourByDateAndHotelId() throws ServiceException, DAOException {
		Tour tour = service.getTourByDateAndHotelId(
				testStartDate, testEndDate, testHotelId);
		
		verify(factoryDAO, times(1)).geTourDAO();
		verify(tourDAO, times(1)).getTourByDateAndHotelId(
				testStartDate, testEndDate, testHotelId);
	
		assertNotNull(tour);
	}
	
	@Test
	public void getUnrealTourByDateAndHotelId() throws ServiceException, DAOException {
		Tour tour = service.getTourByDateAndHotelId(
				testStartDate, testEndDate, 9);
		
		verify(factoryDAO, times(1)).geTourDAO();
		verify(tourDAO, times(1)).getTourByDateAndHotelId(
				testStartDate, testEndDate, 9);
	
		assertNull(tour);
	}
	
	@Test
	public void getUserReservations() throws DAOException, ServiceException {
		final User user = new User();
		user.setId(testUserId);
		
		List<Reservation> reservations = service.getUserReservations(user);
		
		verify(factoryDAO, times(1)).getReservationDAO();
		verify(reservationDAO, times(1)).getUserReservations(user);

		assertTrue(reservations != null && reservations.size() > 0);
	}
	
	@Test
	public void getUserEmptyReservations() throws DAOException, ServiceException {
		final User user = new User();
		user.setId(4);
		
		List<Reservation> reservations = service.getUserReservations(user);
		
		verify(factoryDAO, times(1)).getReservationDAO();
		verify(reservationDAO, times(1)).getUserReservations(user);

		assertTrue(reservations != null && reservations.isEmpty());
	}
	
	@Test
	public void getTourReservations() throws DAOException, ServiceException {
		final Tour tour = new Tour();
		tour.setId(testId);
		
		List<Reservation> reservations = service.getTourReservations(tour);
		
		verify(factoryDAO, times(1)).getReservationDAO();
		verify(reservationDAO, times(1)).getTourReservations(tour);

		assertTrue(reservations != null && reservations.size() > 0);
	}
	
	@Test
	public void getTourEmptyReservations() throws DAOException, ServiceException {
		final Tour tour = new Tour();
		tour.setId(4);
		
		List<Reservation> reservations = service.getTourReservations(tour);
		
		verify(factoryDAO, times(1)).getReservationDAO();
		verify(reservationDAO, times(1)).getTourReservations(tour);

		assertTrue(reservations != null && reservations.isEmpty());
	}
	
	
	@Test
	public void getAllReservedTest() throws ServiceException, DAOException {
		List<Reservation> reservations = service.getAllReserved();
		
		verify(factoryDAO, times(1)).getReservationDAO();
		verify(reservationDAO, times(1)).findAll();
		
		assertNotNull(reservations);
	}
	
	@Test
	public void revokeTest() throws ServiceException, DAOException {
		boolean res = service.revoke(testReservationId);
		
		verifySetReservationStatus(testReservationId);
		
		assertTrue(res);
	}
	
	@Test
	public void payTest() throws DAOException, ServiceException {
		boolean res = service.revoke(testReservationId);
		
		verifySetReservationStatus(testReservationId);
		
		assertTrue(res);
	}
	
	@Test
	public void setTourStatusTest() throws ServiceException, DAOException {
		boolean res = service.setTourStatus(testId, 0);
		
		verifySetTourStatus(testId);
		
		assertTrue(res);
	}
	
	@Test(expected = ServiceException.class)
	public void setTourStatusForUnrealTest() throws ServiceException, DAOException {
		service.setTourStatus(9, 0);
	}
	
	@Test(expected = ServiceException.class)
	public void setTourUnrealStatusTest() throws ServiceException, DAOException {
		service.setTourStatus(testId, 8);
	}
	
	private void verifySetTourStatus(int tourId) throws DAOException {
		verify(factoryDAO, times(1)).geTourDAO();
		verify(tourDAO, times(1)).find(tourId);
		verify(tourDAO, times(1)).update(any(Tour.class));
	}
	
	private void verifySetReservationStatus(int reservationId) throws DAOException {
		verify(factoryDAO, times(1)).getReservationDAO();
		verify(reservationDAO, times(1)).find(reservationId);
		verify(reservationDAO, times(1)).update(any(Reservation.class));
	}
	
	@Override
	protected Answer<? extends Entity> findByIdAnswer() {
		return new Answer<Tour>() {
			
			@Override
			public Tour answer(InvocationOnMock invocation) throws Throwable {
				int id = invocation.getArgument(0);
				
				return id == testId ? createTestTour() : null;
			}
		};
	}
	
	private Answer<Tour> getTourByDateAndHotelIdAnswer() {
		return new Answer<Tour>() {
			
			@Override
			public Tour answer(InvocationOnMock invocation) throws Throwable {
				Date startDate = invocation.getArgument(0);
				Date endDate = invocation.getArgument(1);
				int hotelId = invocation.getArgument(2);
				
				return testStartDate.equals(startDate)
						&& testEndDate.equals(endDate)
						&& testHotelId == hotelId ? createTestTour() : null;
			}
		};
	}
	
	private Answer<Reservation> getResrvationByIdAnswer() {
		return new Answer<Reservation>() {
		
			@Override
			public Reservation answer(InvocationOnMock invocation) throws Throwable {
				int reservationId = invocation.getArgument(0);
				
				return reservationId == testReservationId ? createTestReservation() : null;
			}
		};
	}
	
	private Answer<List<Reservation>> getTourReservationsAnswer() {
		return new Answer<List<Reservation>>() {
			
			@Override
			public List<Reservation> answer(InvocationOnMock invocation) throws Throwable {
				Tour tour = invocation.getArgument(0);
				
				List<Reservation> reservations = new ArrayList<>();
				if (tour.getId() == testId) {
					reservations.add(createTestReservation());
				}
				return reservations;
			}
		};
	}
	
	private Answer<List<Reservation>> getUserReservationsAnswer() {
		return new Answer<List<Reservation>>() {
			
			@Override
			public List<Reservation> answer(InvocationOnMock invocation) throws Throwable {
				User user = invocation.getArgument(0);
				
				List<Reservation> reservations = new ArrayList<>();
				if (user.getId() == testUserId) {
					reservations.add(createTestReservation());
				}
				return reservations;
			}
		};
	}
	
	private Tour createTestTour() {
		Tour tour = new Tour();
		tour.setId(testId);
		tour.setHotelId(testHotelId);
		tour.setStartDate(testStartDate);
		tour.setEndDate(testEndDate);
		
		return tour;
	}
	
	private Reservation createTestReservation() {
		Reservation reservation = new Reservation();
		reservation.setId(testReservationId);
		reservation.setUserId(testUserId);
		reservation.setTourId(testId);
		
		return reservation;
	}
}
