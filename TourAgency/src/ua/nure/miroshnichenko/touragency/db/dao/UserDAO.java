package ua.nure.miroshnichenko.touragency.db.dao;

import ua.nure.miroshnichenko.touragency.db.entity.User;

public interface UserDAO extends DAO<User>{
	
	User getUserByEmail(String email) throws DAOException;

	Double getDiscountStep() throws DAOException;
}
