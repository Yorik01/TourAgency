package ua.nure.miroshnichenko.touragency.service.impl;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import ua.nure.miroshnichenko.myorm.Entity;
import ua.nure.miroshnichenko.touragency.db.dao.DAOException;
import ua.nure.miroshnichenko.touragency.db.entity.Hotel;
import ua.nure.miroshnichenko.touragency.service.exception.ServiceException;

public class HotelServiceImplTest extends ServiceTest {

	private HotelServiceImpl service;
	
	private static final String testHotelName = "hotel";
	
	private static final int testId = 1;
	
	@Override
	public void init() throws Exception {
		super.init();
		
		when(hotelDAO.find(anyInt())).thenAnswer(findByIdAnswer());
		when(hotelDAO.findAll()).thenReturn(new ArrayList<>());
		when(hotelDAO.save(any(Hotel.class))).thenReturn(true);
		when(hotelDAO.update(any(Hotel.class))).thenReturn(true);
		when(hotelDAO.delete(any(Hotel.class))).thenReturn(true);

		when(hotelDAO.getHotelByName(anyString())).thenAnswer(getHotelByNameAnswer());
		
		service = HotelServiceImpl.getInstance();
		service.setFactoryDAO(factoryDAO);
	}
	
	@Test
	public void getByIdTest() throws ServiceException, DAOException {
		Hotel hotel = service.get(testId);
		
		verify(factoryDAO, times(1)).getHotelDAO();
		verify(hotelDAO, times(1)).find(testId);
		
		assertNotNull(hotel);
	}
	
	@Test
	public void getUnrealById() throws DAOException, ServiceException {
		Hotel hotel = service.get(19);
		
		verify(factoryDAO, times(1)).getHotelDAO();
		verify(hotelDAO, times(1)).find(19);
		
		assertNull(hotel);
	}
	
	@Test
	public void getAllTest() throws ServiceException, DAOException {
		List<Hotel> hotels = service.getAll();

		verify(factoryDAO, times(1)).getHotelDAO();
		verify(hotelDAO, times(1)).findAll();
		
		assertNotNull(hotels);
	}
	
	@Test
	public void saveTest() throws ServiceException, DAOException {
		Hotel hotel = new Hotel();
		hotel.setName("aaa");
		
		boolean res = service.save(hotel);

		verify(factoryDAO, times(2)).getHotelDAO();
		verify(hotelDAO, times(1)).getHotelByName(anyString());
		verify(hotelDAO, times(1)).save(hotel);
		
		assertTrue(res);
	}
	
	@Test(expected = ServiceException.class)
	public void saveDuplicateTest() throws ServiceException, DAOException {
		service.save(createTestHotel());
	}
	
	@Test
	public void updateTest() throws DAOException, ServiceException {
		Hotel hotel = new Hotel();
		hotel.setId(testId);
		hotel.setName("aaa");

		boolean res = service.update(hotel);

		verify(factoryDAO, times(2)).getHotelDAO();
		verify(hotelDAO, times(1)).getHotelByName(anyString());
		verify(hotelDAO, times(1)).update(hotel);
		
		assertTrue(res);
	}
	
	@Test(expected = ServiceException.class)
	public void updateDuplicateTest() throws ServiceException, DAOException {
		Hotel hotel = new Hotel();
		hotel.setId(3);
		hotel.setName(testHotelName);
		
		service.update(hotel);
	}
	
	@Test
	public void deleteTest() throws ServiceException, DAOException {
		Hotel hotel = createTestHotel();
		
		boolean res = service.delete(hotel);
		
		verify(factoryDAO, times(1)).getHotelDAO();
		verify(hotelDAO, times(1)).delete(hotel);
		
		assertTrue(res);
	}
	
	@Test
	public void getHotelByNameTest() throws DAOException, ServiceException {
		Hotel hotel = service.getHotelByName(testHotelName);
		
		verify(factoryDAO, times(1)).getHotelDAO();
		verify(hotelDAO, times(1)).getHotelByName(testHotelName);
	
		assertNotNull(hotel);
	}
	
	@Test
	public void getUnrealHotelByNameTest() throws DAOException, ServiceException {
		final String hotelName = "aaa";
		
		Hotel hotel = service.getHotelByName(hotelName);
		
		verify(factoryDAO, times(1)).getHotelDAO();
		verify(hotelDAO, times(1)).getHotelByName(hotelName);
	
		assertNull(hotel);
	}
	
	@Override
	protected Answer<? extends Entity> findByIdAnswer() {
		return new Answer<Hotel>() {
			
			@Override
			public Hotel answer(InvocationOnMock invocation) throws Throwable {
				int id = invocation.getArgument(0);
				
				return id == testId ? createTestHotel() : null;
			}
		};
	}
	
	private Answer<Hotel> getHotelByNameAnswer() {
		return new Answer<Hotel>() {
			
			@Override
			public Hotel answer(InvocationOnMock invocation) throws Throwable {
				String hotelName = invocation.getArgument(0);
				
				return testHotelName.equals(hotelName) ? createTestHotel() : null;
			}
		};
	}
	
	private Hotel createTestHotel() {
		Hotel hotel = new Hotel();
		hotel.setId(testId);
		hotel.setName(testHotelName);
		
		return hotel;
	}
}
