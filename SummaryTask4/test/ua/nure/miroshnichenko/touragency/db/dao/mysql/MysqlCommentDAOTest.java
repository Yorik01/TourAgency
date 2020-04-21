package ua.nure.miroshnichenko.touragency.db.dao.mysql;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import ua.nure.miroshnichenko.myorm.MyOrmException;
import ua.nure.miroshnichenko.touragency.db.dao.DAOException;
import ua.nure.miroshnichenko.touragency.db.entity.Comment;

public class MysqlCommentDAOTest {
	
	@Mock
	public MysqlCommentDAO commentDAO;
	
	@Before
	public void init() throws MyOrmException {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testSaveComment() throws DAOException {
		Comment comment = new Comment();
		
		when(commentDAO.save(any(Comment.class))).thenReturn(true);
	
		assertTrue(commentDAO.save(comment));
	}
	
	@Test
	public void testUpdateComment() throws DAOException {
		Comment comment = new Comment();
		comment.setId(1);
		
		when(commentDAO.update(comment)).thenAnswer(getUpdateAnswer());
		
		assertTrue(commentDAO.update(comment));
	}
	
	@Test
	public void testUpdateCommentNotReal() throws DAOException {
		Comment comment = new Comment();
		comment.setId(3);
		
		when(commentDAO.update(comment)).thenAnswer(getUpdateAnswer());
		
		assertFalse(commentDAO.update(comment));
	}
	
	@Test
	public void testFindComment() throws DAOException {
		int commentId = 1;
		
		when(commentDAO.find(commentId)).thenAnswer(getFindAnswer());
		
		Comment comment = commentDAO.find(commentId);
		
		assertTrue(comment != null && comment.getId() == commentId);
	}
	
	@Test
	public void testFindCommentNotReal() throws DAOException {
		int commentId = 2;
		
		when(commentDAO.find(commentId)).thenAnswer(getFindAnswer());
		
		Comment comment = commentDAO.find(commentId);
		
		assertNull(comment);
	}
	
	@Test
	public void testFindAll() throws DAOException {
		when(commentDAO.findAll()).thenAnswer(getFindAllAnswer());
		
		List<Comment> comments = commentDAO.findAll();
		
		assertNotNull(comments);
	}
	
	@Test
	public void testGetTourComments() throws DAOException {
		int tourId = 1;
		
		when(commentDAO.getTourComments(tourId)).thenAnswer(getTourCommentsAnswer());
		
		List<Comment> comments = commentDAO.getTourComments(tourId);
		
		assertTrue(comments != null && !comments.isEmpty());
	}
	
	@Test
	public void testGetUserCommentsWithoutComments() throws DAOException {
		int userId = 2;
		
		when(commentDAO.getUserComments(userId)).thenAnswer(getUserCommentsAnswer());
		
		List<Comment> comments = commentDAO.getTourComments(userId);
		
		assertTrue(comments != null && comments.isEmpty());
	}
	
	@Test
	public void testGetUserComments() throws DAOException {
		int userId = 1;
		
		when(commentDAO.getUserComments(userId)).thenAnswer(getUserCommentsAnswer());
		
		List<Comment> comments = commentDAO.getUserComments(userId);
		
		assertTrue(comments != null && !comments.isEmpty());
	}
	
	@Test
	public void testGetTourCommentsWithoutComments() throws DAOException {
		int tourId = 2;
		
		when(commentDAO.getTourComments(tourId)).thenAnswer(getTourCommentsAnswer());
		
		List<Comment> comments = commentDAO.getUserComments(tourId);
		
		assertTrue(comments != null && comments.isEmpty());
	}
	
	private Answer<List<Comment>> getTourCommentsAnswer() {
		return new Answer<List<Comment>>() {
			
			@Override
			public List<Comment> answer(InvocationOnMock invocation) throws Throwable {
				int tourId = invocation.getArgument(0);
				
				List<Comment> comments = new ArrayList<>();
				if (tourId == 1) {
					comments.add(new Comment());
				}
				
				return comments;
			}
		};
	}
	
	private Answer<List<Comment>> getUserCommentsAnswer() {
		return new Answer<List<Comment>>() {
			
			@Override
			public List<Comment> answer(InvocationOnMock invocation) throws Throwable {
				int userId = invocation.getArgument(0);
				
				List<Comment> comments = new ArrayList<>();
				if (userId == 1) {
					comments.add(new Comment());
				}
				
				return comments;
			}
		};
	}
	
	private Answer<Comment> getFindAnswer() {
		return new Answer<Comment>() {
			
			@Override
			public Comment answer(InvocationOnMock invocation) throws Throwable {
				int commentId = invocation.getArgument(0);
				
				Comment comment = null;
				if (commentId == 1) {
					comment = new Comment();
					comment.setId(commentId);
				}
				return comment;
			}
		};
	}
	
	private Answer<Boolean> getUpdateAnswer() {
		return new Answer<Boolean>() {
			
			@Override
			public Boolean answer(InvocationOnMock invocation) throws Throwable {
				Comment comment = invocation.getArgument(0);
				return comment.getId() == 1;
			}
		};
	}
	
	private Answer<List<Comment>> getFindAllAnswer() {
		return new Answer<List<Comment>>() {
			
			@Override
			public List<Comment> answer(InvocationOnMock invocation) throws Throwable {
				List<Comment> comments = new ArrayList<Comment>();
				
				return comments;
			}
		};
	}
}