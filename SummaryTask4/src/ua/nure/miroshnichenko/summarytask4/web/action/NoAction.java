package ua.nure.miroshnichenko.summarytask4.web.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.miroshnichenko.summarytask4.web.Path;

public class NoAction extends Action {

	private static final long serialVersionUID = -6707124208737496899L;

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException, ActionException {
		
		req.setAttribute("errorMessage", "Page not found");
		
		return Path.ERR_PAGE;
	}
}
