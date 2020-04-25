package ua.nure.miroshnichenko.myorm.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import java.sql.CallableStatement;

/**
 * Implementation of {@link ua.myorm.core.DBConnection} interface.
 * 
 * @author Miroshnichenko Y. D
 */
class DBConnectionImpl implements DBConnection {

	/* Physical connection to database */
	private Connection sqlConnection;

	/* Statement of query */
	private Statement statement;

	/* Result of query */
	private ResultSet resultSet;

	/* Time when connections pool put this connection into a pool */
	private long lastUsedTime;
	
	static {
		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/* Constructor used only by connections pool to initialize this connection */
	DBConnectionImpl(String url, Properties info) throws DBConnectionException {
		try {
			sqlConnection = DriverManager.getConnection(url, info);
			sqlConnection.setAutoCommit(false);
			sqlConnection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			
			makeWait();
		} catch (SQLException e) {
			throw new DBConnectionException(e);
		}
	}

	/*
	 * Fix current time in milliseconds and close resultSet and statement. It is
	 * used only by a connections pool when one put this into a pool
	 */
	void makeWait() throws DBConnectionException {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
		} catch (SQLException e) {
			throw new DBConnectionException(e);
		}
		lastUsedTime = System.currentTimeMillis();
	}

	/*
	 * Get time when connection was put into a pool. It is used only by a
	 * connections pool.
	 */
	long getLastUsedTime() {
		return lastUsedTime;
	}

	@Override
	public ResultSet executeQuery(String query) throws DBConnectionException {
		try {
			statement = sqlConnection.createStatement();
			resultSet = statement.executeQuery(query);
			return resultSet;
		} catch (SQLException e) {
			throw new DBConnectionException(e);
		}
	}

	@Override
	public ResultSet executeQuery(String query, Object... parametrs) throws DBConnectionException {
		try {
			statement = prepareStatement(query, parametrs);
			resultSet = ((PreparedStatement) statement).executeQuery();
			return resultSet;
		} catch (SQLException e) {
			throw new DBConnectionException(e);
		}
	}

	@Override
	public boolean executeUpdate(String query) throws DBConnectionException {
		try {
			statement = sqlConnection.createStatement();
			return statement.executeUpdate(query) > 0;
		} catch (SQLException e) {
			throw new DBConnectionException(e);
		}
	}
	
	@Override
	public boolean executeUpdate(String query, Object... parametrs) throws DBConnectionException {
		try {
			statement = prepareStatement(query, parametrs);
			return ((PreparedStatement) statement).executeUpdate() > 0;
		} catch (SQLException e) {
			throw new DBConnectionException(e);
		}
	}
	
	@Override
	public ResultSet executeUpdateAndGenerateKeys(String query) throws DBConnectionException {
		try {
			statement = sqlConnection.createStatement();
			int affected = statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
	
			if(affected > 0) {
				resultSet = statement.getGeneratedKeys();
				return resultSet;
			}
			
			return null;
		} catch (SQLException e) {
			throw new DBConnectionException(e);
		}
	}
	
	@Override
	public boolean callProcedure(String query, Object... parametrs) throws DBConnectionException {
		try {
			statement = prepareCollableStatement(query, parametrs);
			return ((CallableStatement) statement).execute();
		} catch (SQLException e) {
			throw new DBConnectionException(e);
		}
	}

	@Override
	public void commit() throws DBConnectionException {
		try {
			sqlConnection.commit();
		} catch (SQLException e) {
			throw new DBConnectionException(e);
		}
	}

	@Override
	public void rollback() throws DBConnectionException {
		try {
			sqlConnection.rollback();
		} catch (SQLException e) {
			throw new DBConnectionException(e);
		}
	}

	private PreparedStatement prepareStatement(String query, Object... parametrs) throws SQLException {
		PreparedStatement statement = sqlConnection.prepareStatement(query);
		for (int i = 0; i < parametrs.length; i++) {
				statement.setString(i + 1, parametrs[i].toString());
		}
		return statement;
	}
	
	private CallableStatement prepareCollableStatement(String query, Object... parametrs) throws SQLException {
		CallableStatement statement = sqlConnection.prepareCall(query);
		for (int i = 0; i < parametrs.length; i++) {
			statement.setString(i + 1, parametrs[i].toString());
		}
		return statement;
	}

	@Override
	public void close() throws DBConnectionException {
		try {
			sqlConnection.close();
		} catch (SQLException e) {
			throw new DBConnectionException(e);
		}
	}
}
