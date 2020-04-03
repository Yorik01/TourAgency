package ua.nure.miroshnichenko.touragency.db.dao;

import java.util.List;

import ua.nure.miroshnichenko.touragency.db.entity.Bonus;

public interface BonusDAO extends DAO<Bonus> {

	List<Bonus> getUserBonuses(int userId) throws DAOException;
}
