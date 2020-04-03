package ua.nure.miroshnichenko.myorm.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * The annotation defines mapping between field and column in a table.
 * 
 * @author Miroshnichenko Y. D
 */
@Retention(RUNTIME)
@Target(FIELD)
public @interface Column {
	/**
	 * The name of column in table.
	 */
	String value();
}
