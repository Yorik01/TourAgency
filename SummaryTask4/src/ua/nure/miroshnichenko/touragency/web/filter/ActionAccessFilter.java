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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.miroshnichenko.touragency.db.entity.Role;
import ua.nure.miroshnichenko.touragency.db.entity.User;
import ua.nure.miroshnichenko.touragency.web.Path;

public class ActionAccessFilter implements Filter {
	
	private final Logger LOG = Logger.getLogger(ActionAccessFilter.class);

	private Map<Role, List<String>> accessMap = new HashMap<>();

	private List<String> adminManager = new ArrayList<>();
	private List<String> commons = new ArrayList<>();
	private List<String> outOfControl = new ArrayList<>();
	
	private final String NO_PERMISSION_MESSAGE = "You do not have permission to access the requested resource";
;

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		Access access = accessAllowed(request);
		switch (access) {
		case ALLOW:
			chain.doFilter(request, response);
			break;
		case EMPTY_REQUEST:
			request.getRequestDispatcher(Path.PAGE_404)
				.forward(request, response);
			break;
		case NO_LOGIN:
			((HttpServletResponse)response).sendRedirect(Path.LOGIN_PAGE);
			break;
		case NO_PERMISSIONS:
			request.setAttribute("errorMessage", NO_PERMISSION_MESSAGE);
			request.getRequestDispatcher(Path.ERR_PAGE)
				.forward(request, response);
			break;
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	    LOG.debug("Filter initialization starts");

		// roles
		accessMap.put(Role.ADMIN, asList(filterConfig.getInitParameter("admin")));
		accessMap.put(Role.MANAGER, asList(filterConfig.getInitParameter("manager")));
		accessMap.put(Role.CLIENT, asList(filterConfig.getInitParameter("client")));
		LOG.trace("Access map --> " + accessMap);

		// commons
		commons = asList(filterConfig.getInitParameter("common"));
		LOG.trace("Common commands --> " + commons);

		// out of control
		outOfControl = asList(filterConfig.getInitParameter("out-of-control"));
		LOG.trace("Out of control commands --> " + outOfControl);

		// admin-manager
		adminManager = asList(filterConfig.getInitParameter("admin-manager"));
		
		
		LOG.debug("Filter initialization finished");
	}
	
	@Override
	public void destroy() {
		System.out.println("Filter has been destroyed!");
	}

	private Access accessAllowed(ServletRequest request) {
		String commandName = request.getParameter("action");
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession();
		
		if (commandName == null || commandName.isEmpty()) {
			return Access.EMPTY_REQUEST;
		}
		
		if (outOfControl.contains(commandName)) {
			return Access.ALLOW;
		}
		
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return Access.NO_LOGIN;
		}
		
		Role userRole = user.getRole();

		if (accessMap.get(userRole).contains(commandName)
				|| commons.contains(commandName)
				|| (adminManager.contains(commandName)
				&& (userRole == Role.ADMIN || userRole == Role.MANAGER))) {
			return Access.ALLOW;
		}
		
		return Access.NO_PERMISSIONS;
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
