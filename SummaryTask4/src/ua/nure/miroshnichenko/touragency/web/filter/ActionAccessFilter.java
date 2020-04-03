package ua.nure.miroshnichenko.touragency.web.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ua.nure.miroshnichenko.touragency.db.entity.Role;
import ua.nure.miroshnichenko.touragency.db.entity.User;
import ua.nure.miroshnichenko.touragency.web.Path;

public class ActionAccessFilter implements Filter {

	private Map<Role, List<String>> accessMap = new HashMap<Role, List<String>>();

	private List<String> commons = new ArrayList<String>();
	private List<String> outOfControl = new ArrayList<String>();

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		if (accessAllowed(request)) {
		//	LOG.debug("Filter finished");
			chain.doFilter(request, response);
		} else {
			String errorMessasge = "You do not have permission to access the requested resource";
			
			request.setAttribute("errorMessage", errorMessasge);
			//LOG.trace("Set the request attribute: errorMessage --> " + errorMessasge);
			
			request.getRequestDispatcher(Path.ERR_PAGE)
					.forward(request, response);
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// LOG.debug("Filter initialization starts");

		// roles
		accessMap.put(Role.ADMIN, asList(filterConfig.getInitParameter("admin")));
		accessMap.put(Role.MANAGER, asList(filterConfig.getInitParameter("manager")));
		accessMap.put(Role.CLIENT, asList(filterConfig.getInitParameter("client")));
		// LOG.trace("Access map --> " + accessMap);

		// commons
		commons = asList(filterConfig.getInitParameter("common"));
		// LOG.trace("Common commands --> " + commons);

		// out of control
		outOfControl = asList(filterConfig.getInitParameter("out-of-control"));
		// LOG.trace("Out of control commands --> " + outOfControl);

		// LOG.debug("Filter initialization finished");
	}
	
	@Override
	public void destroy() {
		System.out.println("Filter has been destroyed!");
	}

	private boolean accessAllowed(ServletRequest request) {
		String commandName = request.getParameter("action");
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		
		if (commandName == null || commandName.isEmpty()) {
			return false;
		}
		
		if (outOfControl.contains(commandName)) {
			return true;
		}
		
		HttpSession session = httpRequest.getSession(false);
		if (session == null) { 
			return false;
		}
		
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return false;
		}
		
		Role userRole = user.getRole();

		return accessMap.get(userRole).contains(commandName)
				|| commons.contains(commandName);
	}

	/**
	 * Extracts parameter values from string.
	 * 
	 * @param str parameter values string.
	 * @return list of parameter values.
	 */
	private List<String> asList(String str) {
		List<String> list = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer(str);
		while (st.hasMoreTokens()) {
			list.add(st.nextToken());
		}
		return list;
	}
}
