package ua.nure.miroshnichenko.touragency.db.dao;

import ua.nure.miroshnichenko.touragency.db.entity.Route;

public interface RouteDAO extends DAO<Route> {

	Route getRouteByPlaces(int placeFromId, int placeToId) throws DAOException;
}
