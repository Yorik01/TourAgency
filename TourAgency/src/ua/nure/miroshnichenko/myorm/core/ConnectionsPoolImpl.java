package ua.nure.miroshnichenko.myorm.core;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Properties;
import java.util.Queue;

import ua.nure.miroshnichenko.myorm.core.pool.ConnectionsPool;
import ua.nure.miroshnichenko.myorm.core.pool.DBConnection;
import ua.nure.miroshnichenko.myorm.core.pool.exception.ConnectionsPoolException;
import ua.nure.miroshnichenko.myorm.core.pool.exception.DBConnectionException;

/**
 * Implementation of {@link ua.myorm.core.ConnectionPool} interface.
 * 
 * @author Miroshnichenko Y. D
 */
class ConnectionsPoolImpl implements ConnectionsPool {
	/* Default max size of connections pool. */
	private final int DEFAULT_LIMIT = 20;

	/* Default min size of connections pool. */
	private final int DEFAULT_START_SIZE = 10;

	/*
	 * Default time interval for working of internal collector which removes unused
	 * connections every 1000 milliseconds.
	 */
	private final long DEFAULT_COLLECTOR_SCHEDULE = 1000;

	/* Default allowed time which every connection can be unused. */
	private final long DEFAULT_ALLOWED_WAITING_TIME = 2000;

	/* The queue of unused connections. */
	private Queue<DBConnectionImpl> freePool;

	/* The queue of used connections. */
	private Queue<DBConnectionImpl> usedPool;

	/*
	 * The flag implies if the current connections pool has limited size and been
	 * blocked.
	 */
	private boolean isBlocked;

	/* The max size of connections pool. */
	private int limit;

	/* The min size of connections pool. */
	private int startSize;

	/**
	 * The time interval for working of internal collector which removes unused
	 * connections every {@value #collectorSchedule} milliseconds.
	 */
	private long collectorSchedule;

	/* Default allowed time which every connection can be unused. */
	private volatile long allowedWaitingTime;

	/* The pool collector which is removed unused connections. */
	private PoolCollector poolCollector = new PoolCollector();

	/* physical address to database. */
	private String url;

	/* Properties with username and password of database. */
	private Properties properties;

	/**
	 * The implementation of collector which removes unused connections every
	 * {@value #collectorSchedule} millisecond if a connections was in
	 * {@value #freePool} queue more then {@value #allowedWaitingTime}.
	 */
	private class PoolCollector extends Thread {
		/** Check {@value #freePool} queue and remove unused */
		private void removeUseless() {
			Iterator<DBConnectionImpl> iterator = freePool.iterator();

			while (iterator.hasNext()) {
				DBConnectionImpl connection = iterator.next();

				if (freePool.size() > startSize) {
					if (Math.abs(System.currentTimeMillis() - connection.getLastUsedTime()) > allowedWaitingTime) {
						try {
							connection.close();
						} catch (DBConnectionException e) {
							e.printStackTrace();
						}
						iterator.remove();
					} else {
						break;
					}
				}
			}
		}

		/**
		 * Work every {@value #collectorSchedule} milliseconds while it isn't
		 * interrupted
		 */
		@Override
		public void run() {
			while (!isInterrupted()) {
				synchronized (ConnectionsPoolImpl.this) {
					removeUseless();
				}

				try {
					sleep(collectorSchedule);
				} catch (InterruptedException e) {
					return;
				}
			}
		}
	}

	ConnectionsPoolImpl(String url) throws ConnectionsPoolException {
		limit = DEFAULT_LIMIT;
		collectorSchedule = DEFAULT_COLLECTOR_SCHEDULE;
		allowedWaitingTime = DEFAULT_ALLOWED_WAITING_TIME;
		startSize = DEFAULT_START_SIZE;

		this.url = url;

		createPool(DEFAULT_START_SIZE);
		poolCollector.start();
	}
	
	/**
	 * Constructor sets basic properties and other properties use default values. It
	 * used only by {@link #TransactionFactoryImpl}
	 */
	ConnectionsPoolImpl(String url, Properties properties) throws ConnectionsPoolException {
		limit = Integer.parseInt(properties.getProperty("db.pool.limit"));
		collectorSchedule = Long.parseLong(properties.getProperty("db.pool.collector.schedule"));
		allowedWaitingTime = Long.parseLong(properties.getProperty("db.pool.allowed"));
		startSize = Integer.parseInt(properties.getProperty("db.pool.start.size"));
System.out.println("pool " + startSize);
		this.url = url;
		this.properties = properties;

		createPool(startSize);
		poolCollector.start();
	}

	/**
	 * Return and remove a connections from {@value #freePool} if it isn't empty
	 * else this method creates a new connection and returns it if the general size
	 * isn't greater then {@value #limit} else it makes {@value #isBlocked} true and
	 * the current thread becomes waited on this monitor until the
	 * {@value #freePool} has at least one connection.
	 */
	@Override
	public synchronized DBConnection getDBConnection() throws ConnectionsPoolException {
		DBConnectionImpl connection = null;
		if (freePool.size() == 0) {
			try {
				if (size() < limit) {
					connection = new DBConnectionImpl(url, properties);
					usedPool.add(connection);

					return connection;
				} else {
					isBlocked = true;
					wait();
				}
			} catch (DBConnectionException | InterruptedException e) {
				throw new ConnectionsPoolException(e.getMessage(), e.getCause());
			}
		}

		connection = freePool.poll();
		usedPool.add(connection);

		return connection;
	}

	/**
	 * Remove connection from {@value #usedPool} and put it into {@value #freePool}.
	 * If this pool has been blocked it wakes up all threads
	 */
	@Override
	public synchronized void free(DBConnection connection) throws ConnectionsPoolException {
		usedPool.remove(connection);
		freePool.add((DBConnectionImpl) connection);

		try {
			((DBConnectionImpl)connection).makeWait();
		} catch (DBConnectionException e) {
			e.printStackTrace();
			throw new ConnectionsPoolException(e);
		}
		
		if (isBlocked) {
			notifyAll();
			isBlocked = false;
		}
	}

	/* close all connections physically and interrupt the pool collector */
	@Override
	public synchronized void close() throws ConnectionsPoolException {
		try {
			for (DBConnection connection : freePool) {
				connection.close();
			}

			for (DBConnection connection : usedPool) {
				connection.close();
			}

			freePool.clear();
			usedPool.clear();

			poolCollector.interrupt();
		} catch (Exception e) {
			throw new ConnectionsPoolException(e);
		}
	}

	@Override
	public int size() {
		return freePool.size() + usedPool.size();
	}
	
	@Override
	public int getLimit() {
		return limit;
	}

	@Override
	public synchronized void setLimit(int limit) {
		this.limit = limit;
	}

	@Override
	public long getCollectorSchedule() {
		return collectorSchedule;
	}

	@Override
	public synchronized void setCollectorSchedule(long interval) {
		collectorSchedule = interval;
	}

	@Override
	public long getAllowedWaitingTime() {
		return allowedWaitingTime;
	}

	@Override
	public synchronized void setAllowedWAitingTime(long time) {
		allowedWaitingTime = time;
	}

	/* Initialize the pool */
	private void createPool(int size) throws ConnectionsPoolException {
		freePool = new LinkedList<>();
		usedPool = new LinkedList<>();
		try {
			for (int i = 0; i < size; i++) {
				freePool.add(new DBConnectionImpl(url, properties));
			}
		} catch (DBConnectionException e) {
			throw new ConnectionsPoolException(e);
		}
	}
}