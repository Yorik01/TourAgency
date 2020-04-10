package ua.nure.miroshnichenko.touragency.web.filter;

import java.io.IOException;
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
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		
		HttpSession session = httpRequest.getSession();
		if (session.getAttribute("locale") == null) {
			Properties properties =
					(Properties) httpRequest.getServletContext().getAttribute("locales");
			
			session.setAttribute("locale", properties.get("main"));
		}
		
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		LOG.debug("Locale filter initialization starts");
	}

	@Override
	public void destroy() {
		LOG.debug("Locale filter has been destroyed!");
	}
}
