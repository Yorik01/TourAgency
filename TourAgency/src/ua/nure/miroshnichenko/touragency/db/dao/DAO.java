package ua.nure.miroshnichenko.touragency.db.dao;

import java.util.List;

import ua.nure.miroshnichenko.myorm.Entity;

public interface DAO<T extends Entity> {
	T find(int id) throws DAOException;

	List<T> findAll() throws DAOException;

	boolean save(T entity) throws DAOException;

	boolean update(T entity) throws DAOException;

	boolean delete(T entity) throws DAOException;
}