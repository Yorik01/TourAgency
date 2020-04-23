package ua.nure.miroshnichenko.touragency.web.listener;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import ua.nure.miroshnichenko.myorm.core.transaction.exception.TransactionFactoryException;
import ua.nure.miroshnichenko.touragency.db.DBUtil;

public class ContextListener implements ServletContextListener {

	private static final Logger LOG = Logger.getLogger(ContextListener.class);
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		log("Servlet context destruction starts");
		try {
			DBUtil.closeConnection();
		} catch (TransactionFactoryException e) {
			LOG.error(e);
		}
		log("Servlet context destruction finished");
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		log("Servlet context initialization starts");

		ServletContext servletContext = event.getServletContext();
		
		initLog4J(servletContext);
		initActionFactory();
		initLocales(servletContext);

		log("Servlet context initialization finished");
	}

	/**
	 * Initializes log4j framework.
	 * 
	 * @param servletContext
	 */
	private void initLog4J(ServletContext servletContext) {
		log("Log4J initialization started");
		try {
			PropertyConfigurator.configure(servletContext.getRealPath("WEB-INF/log4j.properties"));
			LOG.debug("Log4j has been initialized");
		} catch (Exception ex) {
			log("Cannot configure Log4j");
			ex.printStackTrace();
		}
		log("Log4J initialization finished");
	}

	/**
	 * Initializes CommandContainer.
	 * 
	 * @param servletContext
	 */
	private void initActionFactory() {

		// initialize commands container
		// just load class to JVM
		try {
			Class.forName("ua.nure.miroshnichenko.touragency.web.action.ActionContainer");
		} catch (ClassNotFoundException ex) {
			throw new IllegalStateException("Cannot initialize Action Factory");
		}
	}
	
	private void initLocales(ServletContext context) {
    	String localesFileName = context.getInitParameter("locales");
    	
    	// obtain reale path on server
    	String localesFileRealPath = context.getRealPath(localesFileName);
    	
    	// locad descriptions
    	Properties locales = new Properties();
    	try {
			locales.load(new FileInputStream(localesFileRealPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
    	// save descriptions to servlet context
    	context.setAttribute("locales", locales);
    	locales.list(System.out);
	}

	private void log(String msg) {
		System.out.println("[ContextListener] " + msg);
	}
}