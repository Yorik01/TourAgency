package ua.nure.miroshnichenko.summarytask4.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.miroshnichenko.summarytask4.web.action.Action;
import ua.nure.miroshnichenko.summarytask4.web.action.ActionException;
import ua.nure.miroshnichenko.summarytask4.web.action.ActionFactory;

@MultipartConfig(maxFileSize = 16177215)
public class Controller extends HttpServlet {

	private static final long serialVersionUID = -31201244504037211L;

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

		// LOG.debug("Controller starts");
		// extract command name from the request
		String commandName = request.getParameter("action");
		// LOG.trace("Request parameter: command --> " + commandName);
		// obtain command object by its name
		Action action = ActionFactory.get(commandName);
		// LOG.trace("Obtained command --> " + command);
		if (action == null) {
			action = ActionFactory.get("noAction");
		}
		// execute command and get forward address
		// String forward = Path.PAGE_ERROR_PAGE;
		String forward = Path.ERR_PAGE;
		try {
			forward = action.execute(request, response);
			if (!forward.isEmpty()) {
				request.getRequestDispatcher(forward).forward(request, response);
			}
		} catch (ActionException ex) {
			request.setAttribute("errorMessage", ex.getMessage());
			request.getRequestDispatcher(forward).forward(request, response);
		}
		// LOG.trace("Forward address --> " + forward);

		// LOG.debug("Controller finished, now go to forward address --> " + forward);

		// go to forward
	}
}
