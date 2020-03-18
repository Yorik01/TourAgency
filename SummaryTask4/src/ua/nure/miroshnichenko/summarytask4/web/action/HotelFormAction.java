package ua.nure.miroshnichenko.summarytask4.web.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.miroshnichenko.summarytask4.web.Path;

public class HotelFormAction extends Action {

	private static final long serialVersionUID = -286717308487609514L;

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException, ActionException {
		System.out.println("assjhgfhjfsfsdsfsdfjk");
		req.setAttribute("form", Path.HOTEL_FORM);
		return Path.ADMIN_PAGE;
	}
}
