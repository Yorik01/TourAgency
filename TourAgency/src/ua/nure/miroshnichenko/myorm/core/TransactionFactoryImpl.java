package ua.nure.miroshnichenko.myorm.core;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ua.nure.miroshnichenko.myorm.Entity;
import ua.nure.miroshnichenko.myorm.core.mapping.CrudOperation;
import ua.nure.miroshnichenko.myorm.core.mapping.Mapper;
import ua.nure.miroshnichenko.myorm.core.mapping.exception.MappingReflectiveException;
import ua.nure.miroshnichenko.myorm.core.pool.ConnectionsPool;
import ua.nure.miroshnichenko.myorm.core.pool.DBConnection;
import ua.nure.miroshnichenko.myorm.core.pool.exception.ConnectionsPoolException;
import ua.nure.miroshnichenko.myorm.core.pool.exception.DBConnectionException;
import ua.nure.miroshnichenko.myorm.core.transaction.Transaction;
import ua.nure.miroshnichenko.myorm.core.transaction.TransactionFactory;
import ua.nure.miroshnichenko.myorm.core.transaction.TransactionStatus;
import ua.nure.miroshnichenko.myorm.core.transaction.exception.TransactionException;
import ua.nure.miroshnichenko.myorm.core.transaction.exception.TransactionFactoryException;

/**
 * Implementation of {@link ua.nure.miroshnichenko.myorm.core.transaction.TransactionFactory}
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
	public synchronized Transaction createTransaction() throws TransactionFactoryException {
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
	 * Implementation of {@link ua.nure.miroshnichenko.myorm.core.transaction.Transaction}
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
		public <T extends Entity> List<T> customQuery(String query, Class<? extends T> type) throws TransactionException {
			try {
				ResultSet resultSet = connection.executeQuery(query);
				result = getEntities(resultSet, type);
				
				return (List<T>) result;
			} catch (DBConnectionException | MappingReflectiveException e) {
				throw new TransactionException(e);
			}
		}
		
		@Override
		public boolean customUpdate(String query, Object... parametrs)
				throws TransactionException {
			try {
				boolean result = connection.executeUpdate(query, parametrs);
				return result;
			} catch (DBConnectionException e) {
				throw new TransactionException(e);
			}
		}

		@Override
		public <T extends Entity> List<T> customQuery(String query, Class<? extends T> type, Object... parametrs)
				throws TransactionException {
			try {
				ResultSet resultSet = connection.executeQuery(query, parametrs);
				result = getEntities(resultSet, type);
				
				return (List<T>) result;
			} catch (DBConnectionException | MappingReflectiveException e) {
				throw new TransactionException(e);
			}
		}
		
		@Override
		public boolean callProcedure(String name, Object... parametrs) throws TransactionException {
			try {
				StringBuilder args = new StringBuilder();
				for(int i = 0; i < parametrs.length; i++) {
					args.append('?');
					if(i != parametrs.length - 1) {
						args.append(',');
					}
				}
			
				String query = String.format("{CALL %s(%s)}", name, args);
				return connection.callProcedure(query, parametrs);
			} catch (DBConnectionException e) {
				throw new TransactionException(e);
			}
		}

		@Override
		public boolean insert(Entity entity) throws TransactionException {
			try {
				String query = Mapper.getCrudQuery(entity, CrudOperation.INSERT);

				ResultSet resultSet = connection.executeUpdateAndGenerateKeys(query);
				boolean result = resultSet.next();

				Mapper.setGneratedKeys(entity, resultSet);
				
				return result;
			} catch (DBConnectionException | MappingReflectiveException | SQLException e) {
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
		public <T extends Entity> T findByPK(Class<? extends T> type, Object... values) throws TransactionException {
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

					return (T) Mapper.resultToEntity(resultSet, type);
				} else {
					throw new TransactionException("Number of values must equals number of primary keys!");
				}
			} catch (DBConnectionException | MappingReflectiveException | SQLException e) {
				throw new TransactionException(e);
			}
		}

		@Override
		public <T extends Entity> List<T> find(Class<? extends T> type, Map<String, Object> condition)
				throws TransactionException {
			try {
				String query = Mapper.getCrudQuery(type, CrudOperation.SELECT, condition);
				ResultSet resultSet = connection.executeQuery(query);

				result = getEntities(resultSet, type);
				return (List<T>) result;
			} catch (DBConnectionException | MappingReflectiveException e) {
				throw new TransactionException(e);
			}
		}
		
		@Override
		public Object getSingleValue(String query) throws TransactionException {
			try {
				ResultSet resultSet = connection.executeQuery(query);
				
				if (resultSet.next()) {
					return resultSet.getObject(1);
				}
				
				return null;
			} catch (DBConnectionException | SQLException e) {
				throw new TransactionException(e);
			}
		}

		@Override
		public <T extends Entity> List<T> findAll(Class<? extends T> type) throws TransactionException {
			try {
				String query = Mapper.getCrudQuery(type, CrudOperation.SELECT);
				ResultSet resultSet = connection.executeQuery(query);

				result = getEntities(resultSet, type);
				return (List<T>) result;
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
		public void close() throws TransactionException {
			try {
				connectionsPool.free(connection);
			} catch (ConnectionsPoolException e) {
				e.printStackTrace();
				throw new TransactionException(e);
			}
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
