package ua.nure.miroshnichenko.touragency.web;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.miroshnichenko.touragency.Util;
import ua.nure.miroshnichenko.touragency.web.action.Action;
import ua.nure.miroshnichenko.touragency.web.action.ActionException;
import ua.nure.miroshnichenko.touragency.web.action.ActionContainer;

@MultipartConfig(maxFileSize = 16177215)
public class Controller extends HttpServlet {

	private static final long serialVersionUID = -31201244504037211L;

	private final Logger LOG = Logger.getLogger(Controller.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	/**
	 * Main method of this controller.
	 */
	private void process(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		LOG.debug("Controller starts");
		// extract command name from the request
		String commandName = request.getParameter("action");

		LOG.trace("Request parameter: action --> " + commandName);
		// obtain command object by its name
		Action action = ActionContainer.get(commandName);

		LOG.trace("Obtained action --> " + commandName);

		if (action == null) {
			action = ActionContainer.get("noAction");
		}
		// execute command and get forward address
		// String forward = Path.PAGE_ERROR_PAGE;
		String forward = Path.ERR_PAGE;
		try {
			forward = action.execute(request, response);
			
			if (forward.startsWith("redirect:")) {
				response.sendRedirect(forward.substring(9));
			} else if (!forward.isEmpty()) {
				request.getRequestDispatcher(forward).forward(request, response);
			}
		} catch (ActionException e) {
			LOG.error(e.getMessage());
			
			request.setAttribute("errorMessage", getExceptionMessage(e));
			request.getRequestDispatcher(forward).forward(request, response);
		}
		LOG.trace("Forward address --> " + forward);

		LOG.debug("Controller finished, now go to forward address --> " + forward);

		// go to forward
	}
	
	private String getExceptionMessage(ActionException e) {
		String[] arr = e.getMessage().split("\\s");
		arr = Arrays.copyOfRange(arr, 1, arr.length);
		
		return Util.mergeStrings(arr, " ");
	}
}
