package ua.nure.miroshnichenko.summarytask4.db.dao;

import ua.nure.miroshnichenko.summarytask4.db.entity.User;

public interface UserDAO extends DAO<User>{
	
	User getUserByEmail(String email) throws DAOException;
}
