package ua.nure.miroshnichenko.touragency.service.impl;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import ua.nure.miroshnichenko.touragency.db.dao.DAOException;
import ua.nure.miroshnichenko.touragency.db.entity.User;
import ua.nure.miroshnichenko.touragency.service.exception.IncorrectLoginException;
import ua.nure.miroshnichenko.touragency.service.exception.ServiceException;

public class AuthentificationServiceImplTest extends ServiceTest {

	private AuthentificationServiceImpl service;

	private final int testId = 1;
	
	private final String testEmail = "testEmail@gmail.com";

	//SHA-256 hash for '1234' password
	private final String testPassHash = "3ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4";
	
	private final String testPass = "1234";
	
	@Before
	@Override
	public void init() throws Exception {
		super.init();
		
		when(userDAO.find(anyInt())).thenAnswer(findByIdAnswer());
		when(userDAO.findAll()).thenReturn(new ArrayList<>());
		when(userDAO.save(any(User.class))).thenReturn(true);
		when(userDAO.update(any(User.class))).thenReturn(true);
		when(userDAO.delete(any(User.class))).thenReturn(true);

		when(userDAO.getUserByEmail(anyString())).thenAnswer(getUserByEmailAnswer());
		
		service = AuthentificationServiceImpl.getInstance();
		service.setFactoryDAO(factoryDAO);
	}
	
	@Test
	public void loginTest() throws ServiceException, DAOException {
		User user = service.login(testEmail, testPass);
		
		verifyGetUserByEmail(testEmail);
		
		assertTrue(user != null && user.getId() == testId);
	}
	
	@Test(expected = IncorrectLoginException.class)
	public void incorrectEmailTest() throws ServiceException, DAOException {
		service.login("aaa", testPass);
	}
	
	@Test(expected = IncorrectLoginException.class)
	public void incorrectPasswordTest() throws ServiceException, DAOException {
		service.login(testEmail, "aaaaa");
	}
	
	@Test
	public void signupTest() throws ServiceException, DAOException {
		final String email = "aaaa";
		
		User user = new User();
		user.setEmail(email);
		user.setPassword(testPass);
		
		boolean result = service.signup(user);
		
		verifyGetUserByEmail(email);
		
		assertTrue(result);
	}
	
	@Test(expected = ServiceException.class)
	public void duplicateSignup() throws ServiceException, DAOException {		
		User user = new User();
		user.setEmail(testEmail);
		user.setPassword(testPass);
		
		service.signup(user);
	}
	
	@Test
	public void editUserTest() throws ServiceException, DAOException {
		final String newEmail = "newUser";
		
		User user = new User();
		user.setEmail(newEmail);
		user.setPassword("aaaa123");
		
		boolean res = service.editUser(user);
		
		verifyGetUserByEmail(newEmail);
		
		assertTrue(res);
	}
	
	@Test
	public void getUserInfoTest() throws DAOException, ServiceException {
		User user = service.getUserInfo(testId);
				
		verify(factoryDAO, times(1)).getUserDAO();
		verify(userDAO, times(1)).find(testId);
		
		assertNotNull(user);
	}
	
	@Test
	public void getUnrealUserInfoTest() throws DAOException, ServiceException {
		final int id = 2;
		
		User user = service.getUserInfo(id);
		
		verify(factoryDAO, times(1)).getUserDAO();
		verify(userDAO, times(1)).find(id);
		
		assertNull(user);
	}
	
	@Test
	public void getAllUsersTest() throws DAOException, ServiceException {
		List<User> users = service.getAllUsers();
		
		verify(factoryDAO, times(1)).getUserDAO();
		verify(userDAO, times(1)).findAll();
		
		assertNotNull(users);
	}
	
	private void verifyGetUserByEmail(String email) throws DAOException {
		verify(factoryDAO, times(1)).getUserDAO();
		verify(userDAO, times(1)).getUserByEmail(email);
	}
	
	@Override
	protected Answer<User> findByIdAnswer() {
		return new Answer<User>() {
		
			@Override
			public User answer(InvocationOnMock invocation) throws Throwable {
				int id = invocation.getArgument(0);
				
				return id == testId ? createTestUser() : null;
			}
		};
	}
	
	private Answer<User> getUserByEmailAnswer() {
		return new Answer<User>() {

			@Override
			public User answer(InvocationOnMock invocation) throws Throwable {
				String email = invocation.getArgument(0);
				
				return testEmail.equals(email) ? createTestUser() : null;
			}
		};
	}
	
	private User createTestUser() {
		User user = new User();
		user.setEmail(testEmail);
		user.setId(testId);
		user.setPassword(testPassHash);
		
		return user;
	}
}
