package ua.nure.miroshnichenko.touragency.db.dao.mysql;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import ua.nure.miroshnichenko.touragency.db.DBUtil;
import ua.nure.miroshnichenko.touragency.db.Queries;
import ua.nure.miroshnichenko.touragency.db.dao.DAOException;
import ua.nure.miroshnichenko.touragency.db.dao.FacilityDAO;
import ua.nure.miroshnichenko.touragency.db.dao.HotelDAO;
import ua.nure.miroshnichenko.touragency.db.dao.ServicingDAO;
import ua.nure.miroshnichenko.touragency.db.entity.Facility;
import ua.nure.miroshnichenko.touragency.db.entity.Hotel;
import ua.nure.miroshnichenko.touragency.db.entity.Servicing;
import ua.nure.miroshnichenko.myorm.core.transaction.Transaction;
import ua.nure.miroshnichenko.myorm.core.transaction.exception.TransactionException;
import ua.nure.miroshnichenko.myorm.core.transaction.exception.TransactionFactoryException;

public class MysqlHotelDAO implements HotelDAO {

	private ServicingDAO servicingDAO = new MysqlServicingDAO();

	private FacilityDAO facilityDAO = new MysqlFacilityDAO();

	private final Logger LOG = Logger.getLogger(MysqlHotelDAO.class);
	
	@Override
	public Hotel find(int id) throws DAOException {
		Transaction transaction = null;
		Hotel hotel = null;

		try {
			transaction = DBUtil.getTransaction();
			hotel = transaction.findByPK(Hotel.class, id);

			initHotel(transaction, hotel);

		} catch (TransactionFactoryException | TransactionException e) {
			LOG.error(e);
			throw new DAOException(e);
		} finally {
			try {
				DBUtil.close(transaction);
			} catch (TransactionException e) {
				LOG.error(e);
				throw new DAOException(e);
			}
		}
		return hotel;
	}

	@Override
	public List<Hotel> findAll() throws DAOException {
		Transaction transaction = null;
		List<Hotel> hotels = new ArrayList<>();

		try {
			transaction = DBUtil.getTransaction();
			hotels = transaction.findAll(Hotel.class);

			for (Hotel hotel : hotels) {
				initHotel(transaction, hotel);
			}

		} catch (TransactionFactoryException | TransactionException e) {
			LOG.error(e);
			throw new DAOException(e);
		} finally {
			try {
				DBUtil.close(transaction);
			} catch (TransactionException e) {
				LOG.error(e);
				throw new DAOException(e);
			}
		}
		return hotels;
	}

	@Override
	public boolean save(Hotel entity) throws DAOException {
		Transaction transaction = null;
		boolean result = false;

		try {
			transaction = DBUtil.getTransaction();
			result = transaction.insert(entity);

			for (Facility facility : entity.getFacilities()) {
				Facility facility2 = facilityDAO.getFacilityByName(facility.getName());
				result = result && transaction.customUpdate(
						Queries.SET_FACILITY_FOR_HOTEL, entity.getId(), facility2.getId());
			}

			for (Servicing servicing : entity.getServicings()) {
				Servicing servicing2 = servicingDAO.getServicingByName(servicing.getName());
				result = result && transaction.customUpdate(
						Queries.SET_SERVICING_FOR_HOTEL, entity.getId(), servicing2.getId());
			}

			transaction.commit();
		} catch (TransactionFactoryException | TransactionException e) {
			LOG.error(e);
			try {
				transaction.rollback();
			} catch (TransactionException e1) {
				e1.printStackTrace();
				throw new DAOException(e);
			}
			throw new DAOException(e);
		} finally {
			try {
				DBUtil.close(transaction);
			} catch (TransactionException e) {
				LOG.error(e);
				throw new DAOException(e);
			}
		}
		return result;
	}

	@Override
	public boolean update(Hotel entity) throws DAOException {
		Transaction transaction = null;
		boolean result = false;

		try {
			transaction = DBUtil.getTransaction();
			result = transaction.update(entity);
			
			Hotel oldHotel = find(entity.getId());
			Set<Facility> newFacilities = entity.getFacilities();
			Set<Facility> oldFacilities = oldHotel.getFacilities();
			
			for(Facility oldFacility : oldFacilities) {
				if(!newFacilities.contains(oldFacility)) {
					result = result && transaction.customUpdate(
							Queries.DELETE_FACILITY_FOR_HOTEL, oldFacility.getId(), entity.getId());
				} 
			}
			
			for(Facility newFacility : newFacilities) {
				if(!oldFacilities.contains(newFacility)) {
					Facility facility = facilityDAO.getFacilityByName(newFacility.getName());
					
					result = result && transaction.customUpdate(
							Queries.SET_FACILITY_FOR_HOTEL, entity.getId(), facility.getId());
				}
			}
			
			Set<Servicing> newServicings = entity.getServicings();
			Set<Servicing> oldServicings = oldHotel.getServicings();
			
			for(Servicing oldServicing : oldServicings) {
				if(!newServicings.contains(oldServicing)) {
					result = result && transaction.customUpdate(
							Queries.DELETE_SERVICING_FOR_HOTEL, oldServicing.getId(), entity.getId());
				} 
			}
			
			for(Servicing newServicing : newServicings) {
				if(!oldServicings.contains(newServicing)) {
					Servicing servicing = servicingDAO.getServicingByName(newServicing.getName());
					
					result = result && transaction.customUpdate(
							Queries.SET_SERVICING_FOR_HOTEL, entity.getId(), servicing	.getId());
				}
			}
			
			transaction.commit();
		} catch (TransactionFactoryException | TransactionException e) {
			LOG.error(e);
			try {
				transaction.rollback();
			} catch (TransactionException e1) {
				LOG.error(e1);
				throw new DAOException(e);
			}
			throw new DAOException(e);
		} finally {
			try {
				DBUtil.close(transaction);
			} catch (TransactionException e) {
				LOG.error(e);
				throw new DAOException(e);
			}
		}
		return result;
	}
	
	@Override
	public boolean delete(Hotel entity) throws DAOException {
		Transaction transaction = null;
		boolean result = false;

		try {
			transaction = DBUtil.getTransaction();
			result = transaction.delete(entity);
			transaction.commit();
		} catch (TransactionFactoryException | TransactionException e) {
			LOG.error(e);
			throw new DAOException(e);
		} finally {
			try {
				DBUtil.close(transaction);
			} catch (TransactionException e) {
				LOG.error(e);
				throw new DAOException(e);
			}
		}
		return result;
	}

	@Override
	public Hotel getHotelByName(String name) throws DAOException {
		Transaction transaction = null;
		Hotel hotel = null;

		try {
			transaction = DBUtil.getTransaction();
			List<Hotel> res = transaction.customQuery(Queries.HOTEL_BY_NAME, Hotel.class, name);
			if (!res.isEmpty()) {
				hotel = res.get(0);
			}

			if (hotel != null) {
				initHotel(transaction, hotel);
			}
		} catch (TransactionFactoryException | TransactionException e) {
			LOG.error(e);
			throw new DAOException(e);
		} finally {
			try {
				DBUtil.close(transaction);
			} catch (TransactionException e) {
				LOG.error(e);
				throw new DAOException(e);
			}
		}
		return hotel;
	}
	
	private void initHotel(Transaction transaction, Hotel hotel) throws TransactionException {
		List<Servicing> servicings = transaction.customQuery(Queries.HOTEL_SERVICES,
				Servicing.class, hotel.getId().toString());
		List<Facility> facilities = transaction.customQuery(Queries.HOTEL_FACILITIES,
				Facility.class, hotel.getId().toString());

		hotel.setFacilities(new HashSet<>(facilities));
		hotel.setServicings(new HashSet<>(servicings));
	}
}
