package ua.nure.miroshnichenko.touragency.web.filter;

import java.io.IOException;
import java.util.Locale;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

public class LocaleFilter implements Filter {

	private final Logger LOG = Logger.getLogger(LocaleFilter.class);
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//		HttpServletRequest httpRequest = (HttpServletRequest) request;
//
//		HttpSession session = httpRequest.getSession();
//		
//		Object locale = session.getAttribute("locale");
//		if (locale == null) {
//			Properties locales = 
//					(Properties) httpRequest.getServletContext().getAttribute("locales");
//			
//			locale = locales.get("main");
//			
//			session.setAttribute("locale", locale);
//		}
//		
//		response.setLocale(new Locale((String) locale));
//		System.out.println(response.getLocale().getCountry());
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		LOG.debug("Locale filter initialization finished");
	}

	@Override
	public void destroy() {
		LOG.debug("Locale filter has benn destroyed!");
	}
}