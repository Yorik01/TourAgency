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
import ua.nure.miroshnichenko.touragency.db.entity.Comment;
import ua.nure.miroshnichenko.touragency.service.exception.ServiceException;

public class CommentServiceImplTest extends ServiceTest {

	private CommentServiceImpl service;
	
	private static final int testId = 1;
	
	private static final int testUserId = 1;
	
	private static final int testTourId = 1;
	
	@Override
	public void init() throws Exception {
		super.init();
		
		when(commentDAO.find(anyInt())).thenAnswer(findByIdAnswer());
		when(commentDAO.findAll()).thenReturn(new ArrayList<>());
		when(commentDAO.save(any(Comment.class))).thenReturn(true);
		when(commentDAO.update(any(Comment.class))).thenReturn(true);
		when(commentDAO.delete(any(Comment.class))).thenReturn(true);

		when(commentDAO.getTourComments(anyInt())).thenAnswer(getTourCommentsAnswer());
		when(commentDAO.getUserComments(anyInt())).thenAnswer(getUserCommentsAnswer());
		
		service = CommentServiceImpl.getInstance();
		service.setFactoryDAO(factoryDAO);
	}
	
	@Test
	public void getByIdTest() throws ServiceException, DAOException {
		Comment comment = service.get(testId);
		
		verify(factoryDAO, times(1)).getCommentDAO();
		verify(commentDAO, times(1)).find(testId);
		
		assertNotNull(comment);
	}
	
	@Test
	public void getUnrealById() throws DAOException, ServiceException {
		Comment comment = service.get(19);
		
		verify(factoryDAO, times(1)).getCommentDAO();
		verify(commentDAO, times(1)).find(19);
		
		assertNull(comment);
	}
	
	@Test
	public void getAllTest() throws ServiceException, DAOException {
		List<Comment> comments = service.getAll();

		verify(factoryDAO, times(1)).getCommentDAO();
		verify(commentDAO, times(1)).findAll();
		
		assertNotNull(comments);
	}
	
	@Test
	public void saveTest() throws ServiceException, DAOException {
		Comment comment = new Comment();
		
		boolean res = service.save(comment);

		verify(factoryDAO, times(1)).getCommentDAO();
		verify(commentDAO, times(1)).save(comment);
		
		assertTrue(res);
	}
	
	@Test
	public void updateTest() throws DAOException, ServiceException {
		Comment comment = new Comment();
		
		boolean res = service.update(comment);

		verify(factoryDAO, times(1)).getCommentDAO();
		verify(commentDAO, times(1)).update(comment);
		
		assertTrue(res);	
	}
	
	@Test
	public void deleteTest() throws ServiceException, DAOException {
		Comment comment = createTestComment();
		
		boolean res = service.delete(comment);
		
		verify(factoryDAO, times(1)).getCommentDAO();
		verify(commentDAO, times(1)).delete(comment);
		
		assertTrue(res);
	}
	
	@Test
	public void getUserComments() throws DAOException, ServiceException {
		List<Comment> comments = service.getUserComments(testUserId);
		
		verify(factoryDAO, times(1)).getCommentDAO();
		verify(commentDAO, times(1)).getUserComments(testUserId);

		assertTrue(comments != null && comments.size() > 0);
	}
	
	@Test
	public void getUserEmptyComments() throws DAOException, ServiceException {
		final int userId = 4;
		
		List<Comment> comments = service.getUserComments(userId);
		
		verify(factoryDAO, times(1)).getCommentDAO();
		verify(commentDAO, times(1)).getUserComments(userId);

		assertTrue(comments != null && comments.isEmpty());
	}
	
	@Test
	public void getTourComments() throws DAOException, ServiceException {
		List<Comment> comments = service.getTourComments(testTourId);
		
		verify(factoryDAO, times(1)).getCommentDAO();
		verify(commentDAO, times(1)).getTourComments(testTourId);

		assertTrue(comments != null && comments.size() > 0);
	}
	
	@Test
	public void getTourEmptyComments() throws DAOException, ServiceException {
		final int tourId = 4;
		
		List<Comment> comments = service.getTourComments(tourId);
		
		verify(factoryDAO, times(1)).getCommentDAO();
		verify(commentDAO, times(1)).getTourComments(tourId);

		assertTrue(comments != null && comments.isEmpty());
	}
	
	@Override
	protected Answer<? extends Entity> findByIdAnswer() {
		return new Answer<Comment>() {
			
			@Override
			public Comment answer(InvocationOnMock invocation) throws Throwable {
				int id = invocation.getArgument(0);
				
				return id == testId ? createTestComment() : null;
			}
		};
	}
	
	private Answer<List<Comment>> getUserCommentsAnswer() {
		return new Answer<List<Comment>>() {
			
			@Override
			public List<Comment> answer(InvocationOnMock invocation) throws Throwable {
				int userId = invocation.getArgument(0);
				
				List<Comment> comments = new ArrayList<>();
				if (userId == testUserId) {
					comments.add(createTestComment());
				}

				return comments;
			}
		};
	}
	
	private Answer<List<Comment>> getTourCommentsAnswer() {
		return new Answer<List<Comment>>() {
			
			@Override
			public List<Comment> answer(InvocationOnMock invocation) throws Throwable {
				int tourId = invocation.getArgument(0);
				
				List<Comment> comments = new ArrayList<>();
				if (tourId == testTourId) {
					comments.add(createTestComment());
				}

				return comments;
			}
		};
	}
	
	private Comment createTestComment() {
		Comment comment = new Comment();
		comment.setId(testId);
		comment.setUserId(testUserId);
		comment.setTourId(testTourId);
		
		return comment;
	}
}
