package ua.nure.miroshnichenko.summarytask4;

import java.util.List;

import ua.nure.miroshnichenko.summarytask4.db.dao.DAOException;
import ua.nure.miroshnichenko.summarytask4.db.dao.mysql.MysqlUserDAO;
import ua.nure.miroshnichenko.summarytask4.db.entity.User;

public class Demo {
	public static void main(String[] args) throws DAOException {
		MysqlUserDAO userDAO = new MysqlUserDAO();
		List<User> users = userDAO.findAll();
		System.out.println(users + "dsad");
	}
}
