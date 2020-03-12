package ua.nure.miroshnichenko.summarytask4.myorm.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

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

	/* Constructor used only by connections pool to initialize this connection */
	DBConnectionImpl(String url, Properties info) throws DBConnectionException {
		try {
			sqlConnection = DriverManager.getConnection(url, info);
			sqlConnection.setAutoCommit(false);
			makeWait();
		} catch (SQLException e) {
			throw new DBConnectionException(e.getMessage(), e.getCause());
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
			throw new DBConnectionException(e.getCause());
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
			throw new DBConnectionException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public ResultSet executeQuery(String query, String... parametrs) throws DBConnectionException {
		try {
			statement = prepareStatement(query, parametrs);
			resultSet = ((PreparedStatement) statement).executeQuery();
			return resultSet;
		} catch (SQLException e) {
			throw new DBConnectionException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public boolean executeUpdate(String query) throws DBConnectionException {
		try {
			statement = sqlConnection.createStatement();
			return statement.executeUpdate(query) > 0;
		} catch (SQLException e) {
			throw new DBConnectionException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public boolean executeUpdate(String query, String... parametrs) throws DBConnectionException {
		try {
			statement = prepareStatement(query, parametrs);
			return ((PreparedStatement) statement).executeUpdate() > 0;
		} catch (SQLException e) {
			throw new DBConnectionException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public void commit() throws DBConnectionException {
		try {
			sqlConnection.commit();
		} catch (SQLException e) {
			throw new DBConnectionException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public void rollback() throws DBConnectionException {
		try {
			sqlConnection.rollback();
		} catch (SQLException e) {
			throw new DBConnectionException(e.getMessage(), e.getCause());
		}
	}

	private PreparedStatement prepareStatement(String query, String... parametrs) throws SQLException {
		PreparedStatement statement = sqlConnection.prepareStatement(query);
		for (int i = 0; i < parametrs.length; i++) {
			statement.setString(i, parametrs[i]);
		}
		return statement;
	}

	@Override
	public void close() throws DBConnectionException {
		try {
			sqlConnection.close();
		} catch (SQLException e) {
			throw new DBConnectionException(e.getMessage(), e.getCause());
		}
	}
}
