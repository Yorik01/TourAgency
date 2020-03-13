package ua.nure.miroshnichenko.summarytask4.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.miroshnichenko.summarytask4.service.ServiceException;
import ua.nure.miroshnichenko.summarytask4.web.action.Action;
import ua.nure.miroshnichenko.summarytask4.web.action.ActionException;
import ua.nure.miroshnichenko.summarytask4.web.action.ActionFactory;

public class Controller extends HttpServlet {

	private static final long serialVersionUID = -31201244504037211L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
	/**
	 * Main method of this controller.
	 */
	private void process(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		
	//	LOG.debug("Controller starts");

		// extract command name from the request
		String commandName = request.getParameter("command");
		//LOG.trace("Request parameter: command --> " + commandName);

		// obtain command object by its name
		Action command = ActionFactory.get(commandName);
		//LOG.trace("Obtained command --> " + command);

		// execute command and get forward address
		//String forward = Path.PAGE_ERROR_PAGE;
		String forward = Path.ERR_PAGE;
		try {
			forward = command.execute(request, response);
			request.getRequestDispatcher(forward).forward(request, response);
		} catch (ActionException ex) {
			//request.setAttribute("errorMessage", ex.getMessage());
			response.getWriter().print(ex.getMessage());
		}
	//	LOG.trace("Forward address --> " + forward);

		//LOG.debug("Controller finished, now go to forward address --> " + forward);
		
		// go to forward
	}
}
