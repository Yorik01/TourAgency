package ua.nure.miroshnichenko.touragency.web.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.miroshnichenko.touragency.db.entity.Transport;
import ua.nure.miroshnichenko.touragency.service.ServiceException;
import ua.nure.miroshnichenko.touragency.service.TransportService;
import ua.nure.miroshnichenko.touragency.web.Path;

public class DeleteTransportAction extends Action {

	private static final long serialVersionUID = 3651730525677853991L;

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException, ActionException {

		TransportService transportService = serviceFactory.getTransportService();
		
		int id = Integer.parseInt(req.getParameter("id"));
		
		
		Transport transport = new Transport();
		transport.setId(id);
		
		try {
			transportService.delete(transport);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ActionException(e);
		}
		
		res.sendRedirect(Path.getControllerPath("allTransports"));
		
		return Path.NO_PATH;
	}
}
