package ua.nure.miroshnichenko.summarytask4.web.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.miroshnichenko.summarytask4.db.entity.Transport;
import ua.nure.miroshnichenko.summarytask4.service.ServiceException;
import ua.nure.miroshnichenko.summarytask4.service.TransportService;
import ua.nure.miroshnichenko.summarytask4.web.Path;

public class AllTransportsAction extends Action {

	private static final long serialVersionUID = -68790491746311415L;

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException, ActionException {
		
		TransportService transportService = serviceFactory.getTransportService();
		
		List<Transport> transports;
		try {
			transports = transportService.getAll();
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ActionException(e);
		}

		req.setAttribute("transports", transports);
		req.setAttribute("form", Path.TRANSPORTS_LIST);
		
		return Path.ADMIN_PAGE;
	}
}
