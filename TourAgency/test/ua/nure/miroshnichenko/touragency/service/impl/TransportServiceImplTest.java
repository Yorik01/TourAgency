package ua.nure.miroshnichenko.touragency.service.impl;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import ua.nure.miroshnichenko.myorm.Entity;
import ua.nure.miroshnichenko.touragency.db.dao.DAOException;
import ua.nure.miroshnichenko.touragency.db.entity.Transport;
import ua.nure.miroshnichenko.touragency.db.entity.TransportType;
import ua.nure.miroshnichenko.touragency.service.exception.ServiceException;

public class TransportServiceImplTest extends ServiceTest {
	
	private TransportServiceImpl service;
	
	private final int testId = 1;
	
	private final String testCode = "abc123";
	
	private final TransportType testType = TransportType.AIRPLANE;
	
	@Before
	@Override
	public void init() throws Exception {
		super.init();
		
		when(transportDAO.find(anyInt())).thenAnswer(findByIdAnswer());
		when(transportDAO.findAll()).thenReturn(new ArrayList<>());
		when(transportDAO.save(any(Transport.class))).thenReturn(true);
		when(transportDAO.update(any(Transport.class))).thenReturn(true);
		when(transportDAO.delete(any(Transport.class))).thenReturn(true);
		
		when(transportDAO.getTransportsByCode(anyString())).thenAnswer(getTransportsByCode());
		when(transportDAO.geTransportByCodeAndType(anyString(), any(TransportType.class)))
		.thenAnswer(getTransportByCodeAndType());
		
		service = TransportServiceImpl.getInstance();
		service.setFactoryDAO(factoryDAO);
	}
	
	@Test
	public void getByIdTest() throws ServiceException, DAOException {
		Transport transport = service.get(testId);
		
		verify(factoryDAO, times(1)).getTransportDAO();
		verify(transportDAO, times(1)).find(testId);
		
		assertNotNull(transport);
	}
	
	@Test
	public void getUnrealById() throws DAOException, ServiceException {
		Transport transport = service.get(19);
		
		verify(factoryDAO, times(1)).getTransportDAO();
		verify(transportDAO, times(1)).find(19);
		
		assertNull(transport);
	}
	
	@Test
	public void getAllTest() throws ServiceException, DAOException {
		List<Transport> transports = service.getAll();

		verify(factoryDAO, times(1)).getTransportDAO();
		verify(transportDAO, times(1)).findAll();
		
		assertNotNull(transports);
	}
	
	@Test
	public void saveTest() throws ServiceException, DAOException {
		Transport transport = new Transport();
		transport.setCode("nkkdkd");
		transport.setType(TransportType.AIRPLANE);

		boolean res = service.save(transport);

		verify(factoryDAO, times(2)).getTransportDAO();
		verify(transportDAO, times(1))
			.geTransportByCodeAndType(anyString(), any(TransportType.class));
		verify(transportDAO, times(1)).save(transport);
		
		assertTrue(res);
	}
	
	@Test(expected = ServiceException.class)
	public void saveDuplicateTest() throws ServiceException, DAOException {
		service.save(createTestTransport());
	}
	
	@Test
	public void updateTest() throws DAOException, ServiceException {
		Transport transport = new Transport();
		transport.setCode("nkkdkd");
		transport.setType(TransportType.AIRPLANE);

		boolean res = service.update(transport);

		verify(factoryDAO, times(2)).getTransportDAO();
		verify(transportDAO, times(1))
			.geTransportByCodeAndType(anyString(), any(TransportType.class));
		verify(transportDAO, times(1)).update(transport);
		
		assertTrue(res);
	}
	
	@Test(expected = ServiceException.class)
	public void updateDuplicateTest() throws ServiceException, DAOException {
		Transport transport = new Transport();
		transport.setId(3);
		transport.setCode(testCode);
		transport.setType(testType);
		
		service.update(transport);
	}
	
	@Test
	public void deleteTest() throws ServiceException, DAOException {
		Transport transport = createTestTransport();
		
		boolean res = service.delete(transport);
		
		verify(factoryDAO, times(1)).getTransportDAO();
		verify(transportDAO, times(1)).delete(transport);
		
		assertTrue(res);
	}
	
	@Override
	protected Answer<? extends Entity> findByIdAnswer() {
		return new Answer<Entity>() {
			
			@Override
			public Entity answer(InvocationOnMock invocation) throws Throwable {
				int id = invocation.getArgument(0);
				
				return id == testId ? createTestTransport() : null;
			}
		};
	}
	
	private Answer<List<Transport>> getTransportsByCode() {
		return new Answer<List<Transport>>() {
			
			@Override
			public List<Transport> answer(InvocationOnMock invocation) throws Throwable {
				String code = invocation.getArgument(0);
				
				if (testCode.equals(code)) {
					List<Transport> transports = new ArrayList<>();
					transports.add(createTestTransport());
					
					return transports;
				}
				
				return new ArrayList<>();
			}
		};
	}
	
	private Answer<Transport> getTransportByCodeAndType() {
		return new Answer<Transport>() {
			
			@Override
			public Transport answer(InvocationOnMock invocation) throws Throwable {
				String code = invocation.getArgument(0);
				TransportType type = invocation.getArgument(1);
				
				return testCode.equals(code) && type.equals(testType)
						? createTestTransport() : null;
			}
		};
	}
	
	private Transport createTestTransport() {
		Transport transport = new Transport();
		transport.setId(testId);
		transport.setCode(testCode);
		transport.setType(testType);
		
		return transport;
	}
}
