package ua.nure.miroshnichenko.summarytask4.myorm.core;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ua.nure.miroshnichenko.summarytask4.myorm.Entity;
import ua.nure.miroshnichenko.summarytask4.myorm.core.mapping.CrudOperation;
import ua.nure.miroshnichenko.summarytask4.myorm.core.mapping.Mapper;
import ua.nure.miroshnichenko.summarytask4.myorm.core.mapping.exception.MappingReflectiveException;
import ua.nure.miroshnichenko.summarytask4.myorm.core.transaction.Transaction;
import ua.nure.miroshnichenko.summarytask4.myorm.core.transaction.TransactionFactory;
import ua.nure.miroshnichenko.summarytask4.myorm.core.transaction.TransactionStatus;
import ua.nure.miroshnichenko.summarytask4.myorm.core.transaction.exception.TransactionException;
import ua.nure.miroshnichenko.summarytask4.myorm.core.transaction.exception.TransactionFactoryException;

/**
 * Implementation of {@link #ua.nure.miroshnichenko.summarytask4.myorm.core.transaction.TransactionFactory}
 * 
 * @author Miroshnichenko Y. D
 */
class TransactionFactoryImpl implements TransactionFactory {
	private ConnectionsPool connectionsPool;

	public TransactionFactoryImpl(Settings settings) throws TransactionFactoryException {
		try {
			connectionsPool = settings.getConnectionsPool();
		} catch (ConnectionsPoolException e) {
			throw new TransactionFactoryException(e);
		}
	}

	@Override
	public Transaction createTransaction() throws TransactionFactoryException {
		try {
			return new TransactionImpl(connectionsPool.getDBConnection());
		} catch (ConnectionsPoolException e) {
			throw new TransactionFactoryException(e);
		}
	}

	@Override
	public void close() throws TransactionFactoryException {
		try {
			connectionsPool.close();
		} catch (Exception e) {
			throw new TransactionFactoryException(e);
		}
	}

	/**
	 * Implementation of {@link #ua.nure.miroshnichenko.summarytask4.myorm.core.transaction.Transaction}
	 * 
	 * @author Miroshnichenko Y. D
	 */
	private class TransactionImpl implements Transaction {
		private DBConnection connection;
		private List<Entity> result;
		private TransactionStatus status;

		public TransactionImpl(DBConnection connection) {
			this.connection = connection;
			status = TransactionStatus.STARTED;
		}

		@Override
		public List<Entity> customQuery(String query, Class<? extends Entity> type) throws TransactionException {
			try {
				ResultSet resultSet = connection.executeQuery(query);
				result = getEntities(resultSet, type);
				return result;
			} catch (DBConnectionException | MappingReflectiveException e) {
				throw new TransactionException(e);
			}
		}

		@Override
		public List<Entity> customQuery(String query, Class<? extends Entity> type, String... parametrs)
				throws TransactionException {

			try {
				ResultSet resultSet = connection.executeQuery(query, parametrs);
				result = getEntities(resultSet, type);
				return result;
			} catch (DBConnectionException | MappingReflectiveException e) {
				throw new TransactionException(e);
			}
		}

		@Override
		public boolean insert(Entity entity) throws TransactionException {
			try {
				String query = Mapper.getCrudQuery(entity, CrudOperation.INSERT);
				return connection.executeUpdate(query);
			} catch (DBConnectionException | MappingReflectiveException e) {
				throw new TransactionException(e);
			}
		}

		@Override
		public boolean update(Entity entity) throws TransactionException {
			try {
				Map<String, Object> condition = Mapper.getPrimaryKeysWithValues(entity);
				String query = Mapper.getCrudQuery(entity, CrudOperation.UPDATE, condition);

				return connection.executeUpdate(query);
			} catch (DBConnectionException | MappingReflectiveException e) {
				throw new TransactionException(e);
			}
		}

		@Override
		public Entity findByPK(Class<? extends Entity> type, Object... values) throws TransactionException {
			try {
				List<String> keys = Mapper.getPrimaryKeys(type);
				if (keys.size() == values.length) {
					Map<String, Object> condition = new HashMap<>();

					int i = 0;
					for (String key : keys) {
						condition.put(key, values[i++]);
					}

					String query = Mapper.getCrudQuery(type, CrudOperation.SELECT, condition);
					ResultSet resultSet = connection.executeQuery(query);
					resultSet.next();

					return Mapper.resultToEntity(resultSet, type);
				} else {
					throw new TransactionException("Number of values must equals number of primary keys!");
				}
			} catch (DBConnectionException | MappingReflectiveException | SQLException e) {
				throw new TransactionException(e);
			}
		}

		@Override
		public List<Entity> find(Class<? extends Entity> type, Map<String, Object> condition)
				throws TransactionException {
			try {
				String query = Mapper.getCrudQuery(type, CrudOperation.SELECT, condition);
				ResultSet resultSet = connection.executeQuery(query);

				result = getEntities(resultSet, type);
				return result;
			} catch (DBConnectionException | MappingReflectiveException e) {
				throw new TransactionException(e);
			}
		}

		@Override
		public List<Entity> findAll(Class<? extends Entity> type) throws TransactionException {
			try {
				String query = Mapper.getCrudQuery(type, CrudOperation.SELECT);
				ResultSet resultSet = connection.executeQuery(query);

				result = getEntities(resultSet, type);
				return result;
			} catch (DBConnectionException | MappingReflectiveException e) {
				throw new TransactionException(e);
			}
		}

		@Override
		public boolean delete(Entity entity) throws TransactionException {
			try {
				Map<String, Object> primaryKeys = Mapper.getPrimaryKeysWithValues(entity);
				String query = Mapper.getCrudQuery(entity, CrudOperation.DELETE, primaryKeys);

				return connection.executeUpdate(query);
			} catch (DBConnectionException | MappingReflectiveException e) {
				throw new TransactionException(e);
			}
		}

		@Override
		public boolean delete(Class<? extends Entity> type, Map<String, Object> condition) throws TransactionException {
			try {
				String query = Mapper.getCrudQuery(type, CrudOperation.DELETE, condition);
				return connection.executeUpdate(query);
			} catch (DBConnectionException | MappingReflectiveException e) {
				throw new TransactionException(e);
			}
		}

		@Override
		public boolean deleteAll(Class<? extends Entity> type) throws TransactionException {
			try {
				String query = Mapper.getCrudQuery(type, CrudOperation.DELETE);
				return connection.executeUpdate(query);
			} catch (DBConnectionException e) {
				throw new TransactionException(e);
			}
		}

		@Override
		public void close() {
			connectionsPool.free(connection);
			status = TransactionStatus.CLOSED;
		}

		@Override
		public TransactionStatus geTransactionStatus() {
			return status;
		}

		@Override
		public List<? extends Entity> getResult() {
			return result;
		}

		@Override
		public void commit() throws TransactionException {
			try {
				connection.commit();
				status = TransactionStatus.COMMITED;
			} catch (DBConnectionException e) {
				throw new TransactionException(e.getCause());
			}
		}

		@Override
		public void rollback() throws TransactionException {
			try {
				connection.rollback();
				status = TransactionStatus.ROLLED_BACK;
			} catch (DBConnectionException e) {
				throw new TransactionException(e.getCause());
			}
		}

		/* Get list of entities from resultSet */
		private List<Entity> getEntities(ResultSet resultSet, Class<? extends Entity> type)
				throws MappingReflectiveException, DBConnectionException {
			List<Entity> entities = new ArrayList<>();
			try {
				while (resultSet.next()) {
					entities.add(Mapper.resultToEntity(resultSet, type));
				}
			} catch (SQLException e) {
				throw new DBConnectionException(e);
			}
			return entities;
		}
	}
}
