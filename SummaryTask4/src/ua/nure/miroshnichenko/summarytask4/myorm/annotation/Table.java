package ua.nure.miroshnichenko.summarytask4.myorm.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * The annotation defines mapping between class and table in a database.
 * 
 * @author Miroshnichenko Y. D
 */
@Retention(RUNTIME)
@Target(TYPE)
public @interface Table {
	/**
	 * The name of table in a database.
	 */
	String value();
}