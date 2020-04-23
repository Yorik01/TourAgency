package ua.nure.miroshnichenko.myorm.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * The annotation defines that current field is mapped to primary key in a table.
 * Fields which have ua.myorm.annotation.PrimaryKey annotation must have ua.myorm.annotation.Column annotation.
 * 
 * @author Miroshnichenko Y. D
 * @see {@link ua.myorm.annotation.Column}
 */
@Retention(RUNTIME)
@Target(FIELD)
public @interface PrimaryKey {
	/**
	 * The flag means if a current primary key is "autoincremented" in a database.
	 * If this flag is enabled the process of adding entity ignores a value of this field.
	 */
	boolean autoIncrement() default true;
}
