package ua.nure.miroshnichenko.myorm.core.mapping;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import ua.nure.miroshnichenko.myorm.Entity;
import ua.nure.miroshnichenko.myorm.annotation.Column;
import ua.nure.miroshnichenko.myorm.annotation.Enumerated;
import ua.nure.miroshnichenko.myorm.annotation.Generated;
import ua.nure.miroshnichenko.myorm.annotation.PrimaryKey;
import ua.nure.miroshnichenko.myorm.annotation.Table;
import ua.nure.miroshnichenko.myorm.core.mapping.exception.MappingException;
import ua.nure.miroshnichenko.myorm.core.mapping.exception.MappingReflectiveException;
import ua.nure.miroshnichenko.myorm.core.mapping.exception.NotEntityException;

/**
 * The utility is abstraction level does converting between JDBC and classes
 * which implement {@link #ua.nure.miroshnichenko.myorm.Entity}. It has methods which convert
 * mapping object to sql query and methods which convert
 * {@link #java.sql.ResultSet} to java objects.
 * 
 * @author Miroshnichenko Y. D
 */
public final class Mapper {

	private Mapper() {
	}

	/**
	 * Return the string of sql query by the type of operation. It cannot be used
	 * for INSERT and UPDATE operations.
	 * 
	 * @param type      meta info of searched class
	 * @param operation type of sql operation
	 * @return string representation of sql query.
	 * @see #CrudOperation
	 */
	public static String getCrudQuery(Class<? extends Entity> type, CrudOperation operation) {
		if (isEntity(type)) {
			switch (operation) {
			case SELECT:
				return getSelectQuery(type);
			case INSERT:
				throw new IllegalArgumentException();
			case UPDATE:
				throw new IllegalArgumentException();
			case DELETE:
				return getDeleteQuery(type);
			default:
				throw new IllegalArgumentException();
			}
		} else {
			throw new NotEntityException();
		}
	}

	/**
	 * Return the string of sql query by the type of operation and condition. It
	 * cannot be used for INSERT and UPDATE operations.
	 * 
	 * @param type      meta info of searched class
	 * @param operation type of sql operation
	 * @param condition condition for the query is represented by {@ #java.util.Map}
	 *                  where keys are names of columns in the database.
	 * @return string representation of sql query.
	 * @see #CrudOperation
	 */
	public static String getCrudQuery(Class<? extends Entity> type, CrudOperation operation,
			Map<String, Object> condition) throws MappingReflectiveException {
		return getCrudQuery(type, operation) + getWhereStatement(condition);
	}

	/**
	 * Return the string of sql query by the class which implements
	 * {@link #ua.nure.miroshnichenko.myorm.Entity} of operation. It cannot be used for INSERT and
	 * UPDATE operations.
	 * 
	 * @param entity    class which implements {@link #ua.nure.miroshnichenko.myorm.Entity}
	 * @param operation type of sql operation
	 * @param condition condition for the query is represented by {@ #java.util.Map}
	 *                  where keys are names of columns in the database.
	 * @return string representation of sql query.
	 * @throws MappingReflectiveException
	 * @see #CrudOperation
	 */
	public static String getCrudQuery(Entity entity, CrudOperation operation) throws MappingReflectiveException {
		Class<? extends Entity> type = entity.getClass();

		if (isEntity(type)) {
			switch (operation) {
			case SELECT:
				return getSelectQuery(type);
			case INSERT:
				return getInsertQuery(entity);
			case UPDATE:
				return getUpdateQuery(entity);
			case DELETE:
				return getDeleteQuery(type);
			default:
				throw new IllegalArgumentException();
			}
		} else {
			throw new NotEntityException();
		}
	}

	/**
	 * Return the string of sql query by the class which implements
	 * {@link #ua.nure.miroshnichenko.myorm.Entity} of operation and condition. It cannot be used for
	 * INSERT and UPDATE operations.
	 * 
	 * @param entity    class which implements {@link #ua.nure.miroshnichenko.myorm.Entity}
	 * @param operation type of sql operation
	 * @param condition condition for the query is represented by {@ #java.util.Map}
	 *                  where keys are names of columns in the database.
	 * @return string representation of sql query.
	 * @throws MappingReflectiveException
	 * @see #CrudOperation
	 */
	public static String getCrudQuery(Entity entity, CrudOperation operation, Map<String, Object> condition)
			throws MappingReflectiveException {
		if (operation != CrudOperation.INSERT) {
			return getCrudQuery(entity, operation) + getWhereStatement(condition);
		} else {
			throw new IllegalArgumentException("INSERT Syntax does not support the WHERE clause!");
		}
	}

	/**
	 * Return list of names of primary keys in a database.
	 * 
	 * @param type meta info of searched class
	 * @return list of primary keys
	 */
	public static List<String> getPrimaryKeys(Class<? extends Entity> type) {
		List<String> keys = new ArrayList<>();

		if (isEntity(type)) {
			Field[] fields = type.getDeclaredFields();
			for (Field field : fields) {
				Column column = field.getAnnotation(Column.class);
				if (column != null && field.isAnnotationPresent(PrimaryKey.class)) {
					keys.add(column.value());
				}
			}

			return keys;
		} else {
			throw new NotEntityException();
		}
	}

	/**
	 * Return map of names of primary keys with values of the current entity.
	 * 
	 * @param entity class which implements {@link #ua.nure.miroshnichenko.myorm.Entity}
	 * @return list of primary keys
	 */
	public static Map<String, Object> getPrimaryKeysWithValues(Entity entity) throws MappingReflectiveException {
		Class<? extends Entity> type = entity.getClass();
		if (isEntity(type)) {
			Field[] fields = type.getDeclaredFields();

			Map<String, Object> primaryKeys = new HashMap<>();
			for (Field field : fields) {
				Column column = field.getAnnotation(Column.class);
				if (column != null && field.isAnnotationPresent(PrimaryKey.class)) {
					try {
						PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
						primaryKeys.put(column.value(), propertyDescriptor.getReadMethod().invoke(entity));
					} catch (IllegalAccessException | IntrospectionException | IllegalArgumentException
							| InvocationTargetException e) {
						throw new MappingReflectiveException(e);
					}
				}
			}
			return primaryKeys;
		} else {
			throw new NotEntityException();
		}
	}

	/**
	 * Create and return entity from {@link #java.sql.ResultSet}
	 * 
	 * @param resultSet result of query
	 * @param type      meta info of searched class
	 */
	public static Entity resultToEntity(ResultSet resultSet, Class<? extends Entity> type)
			throws MappingReflectiveException {
		try {
			Entity entity = type.newInstance();

			List<Field> fields = Arrays.asList(type.getDeclaredFields()).stream()
					.filter(x -> x.isAnnotationPresent(Column.class)).collect(Collectors.toList());

			PropertyDescriptor propertyDescriptor = null;
			for (Field field : fields) {
				Column column = field.getAnnotation(Column.class);

				Object value = resultSet.getObject(column.value());

				if (field.isAnnotationPresent(Enumerated.class)) {
					if (value instanceof Integer) {
						Integer index = (Integer) value;

						Object[] enumConstants = field.getType().getEnumConstants();
						if (enumConstants != null) {
							value = enumConstants[index - 1];
						} else {
							throw new MappingException("The field isn't enum!!!");
						}
					}
				}
					
				if(value instanceof Boolean) {
					value = (Boolean)value ? 1 : 0;
				}
				propertyDescriptor = new PropertyDescriptor(field.getName(), type);
				propertyDescriptor.getWriteMethod().invoke(entity, value);
			}
			return entity;
		} catch (SQLException | IntrospectionException | IllegalAccessException | InvocationTargetException
				| InstantiationException | SecurityException e) {
			throw new MappingReflectiveException(e);
		}
	}

	/**
	 * Check if class has annotation {@link ua.nure.miroshnichenko.myorm.annotation.Table}
	 */
	private static boolean isEntity(Class<? extends Entity> type) {
		return type.isAnnotationPresent(Table.class);
	}

	/**
	 * Return WHERE clause for query by condition
	 * 
	 * @param condition condition for the query is represented by {@ #java.util.Map}
	 *                  where keys are names of columns in the database.
	 */
	private static String getWhereStatement(Map<String, Object> condition) {
		StringBuilder statement = new StringBuilder(" WHERE ");
		Iterator<Entry<String, Object>> iterator = condition.entrySet().iterator();
		while (true) {
			Entry<String, Object> entry = iterator.next();
			statement.append(entry.getKey()).append(" = ").append('\'').append(entry.getValue()).append('\'');

			if (iterator.hasNext()) {
				statement.append(" AND ");
			} else {
				break;
			}
		}
		return statement.toString();
	}

	/**
	 * Return SELECT query by meta info.
	 */
	private static String getSelectQuery(Class<? extends Entity> type) {
		Table tableAnnotation = type.getAnnotation(Table.class);

		return "SELECT * FROM " + tableAnnotation.value();
	}

	/**
	 * Return INSERT query by class which implement {@link #ua.nure.miroshnichenko.myorm.Entity}.
	 */
	private static String getInsertQuery(Entity entity) throws MappingReflectiveException {
		Class<? extends Entity> type = entity.getClass();
		Table tableAnnotation = type.getAnnotation(Table.class);

		List<Field> fields = Arrays.asList(type.getDeclaredFields()).stream()
				.filter(x -> x.isAnnotationPresent(Column.class)).collect(Collectors.toList());
		
		StringBuilder valueStatement = new StringBuilder();
		
		Iterator<Field> iterator = fields.iterator();
		while (true) {
			Field field = iterator.next();
			
			PrimaryKey primaryKey = field.getAnnotation(PrimaryKey.class);
			if (primaryKey != null && primaryKey.autoIncrement()) {
				valueStatement.append("DEFAULT");
			} else {
				try {
					PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
					Object value = propertyDescriptor.getReadMethod().invoke(entity);

					if (field.isAnnotationPresent(Enumerated.class)) {
						Enum<?> en = Enum.valueOf((Class<? extends Enum>) field.getType(), value.toString());
						value = en.ordinal() + 1;
					}

					valueStatement.append('\'').append(value).append('\'');
				} catch (IllegalAccessException | IntrospectionException | InvocationTargetException e) {
					throw new MappingReflectiveException(e);
				}
			}
			if (!iterator.hasNext()) {
				break;
			}
			valueStatement.append(',');
		}

		return "INSERT INTO " + tableAnnotation.value() + " VALUES (" + valueStatement + ")";
	}

	/**
	 * Return UPDATE query by class which implement {@link #ua.nure.miroshnichenko.myorm.Entity}.
	 */
	private static String getUpdateQuery(Entity entity) throws MappingReflectiveException {
		Class<? extends Entity> type = entity.getClass();
		Table tableAnnotation = type.getAnnotation(Table.class);

		List<Field> fields = Arrays.asList(type.getDeclaredFields()).stream()
				.filter(x -> x.isAnnotationPresent(Column.class)).collect(Collectors.toList());
		
		StringBuilder valueStatement = new StringBuilder();
		
		Iterator<Field> iterator = fields.iterator();
		while (true) {
			Field field = iterator.next();
			
			Column column = field.getAnnotation(Column.class);
			if (column != null) {
				PrimaryKey primaryKey = field.getAnnotation(PrimaryKey.class);
				if (primaryKey == null || (primaryKey != null && !primaryKey.autoIncrement())) {
					try {
						PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
						Object value = propertyDescriptor.getReadMethod().invoke(entity);

						if (field.isAnnotationPresent(Enumerated.class)) {
							Enum<?> en = Enum.valueOf((Class<? extends Enum>) field.getType(), value.toString());
							value = en.ordinal() + 1;
						}

						valueStatement.append(column.value()).append(" = ").append('\'').append(value).append('\'');

					} catch (IllegalAccessException | IntrospectionException | IllegalArgumentException
							| InvocationTargetException e) {
						throw new MappingReflectiveException(e);
					}

					if (!iterator.hasNext()) {
						break;
					}
					valueStatement.append(',');
				}
			}
		}
		return "UPDATE " + tableAnnotation.value() + " SET " + valueStatement;
	}
	
	public static void setGneratedKeys(Entity entity, ResultSet resultSet) throws MappingReflectiveException {
		Class<? extends Entity> type = entity.getClass();
		List<Field> fields = Arrays.asList(type.getDeclaredFields()).stream()
				.filter(x -> x.isAnnotationPresent(Generated.class)).collect(Collectors.toList());
		
		if(isEntity(type)) {
			for(Field field : fields) {
				if(field.isAnnotationPresent(Column.class)) {
					try {
						PropertyDescriptor propertyDescriptor = 
								new PropertyDescriptor(field.getName(), type);
						propertyDescriptor.getWriteMethod().invoke(entity, resultSet.getInt(1));
					} catch (IntrospectionException | IllegalAccessException 
							| InvocationTargetException | SQLException e) {
						e.printStackTrace();
						throw new MappingReflectiveException(e);
					}
				} else {
					throw new MappingException("A field which has annotation 'Genarated' must have annotation 'Column'");
				}
			}
		} else {
			throw new NotEntityException();
		}
	}

	/**
	 * Return DELETE query by meta info.
	 */
	private static String getDeleteQuery(Class<? extends Entity> type) {
		Table tableAnnotation = type.getAnnotation(Table.class);

		return "DELETE FROM " + tableAnnotation.value();
	}
}