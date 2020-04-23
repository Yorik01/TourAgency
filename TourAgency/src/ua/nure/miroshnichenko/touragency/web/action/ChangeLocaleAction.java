package ua.nure.miroshnichenko.touragency.web.action;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.miroshnichenko.touragency.web.Path;

public class ChangeLocaleAction extends Action {

	private static final long serialVersionUID = 2420910525784163519L;

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException, ActionException {
		
		HttpSession session = req.getSession();
		
		Properties properties = 
				(Properties) req.getServletContext().getAttribute("locales");
		
		String locale = req.getParameter("locale");
		if (properties != null && properties.containsKey(locale)) {
			session.setAttribute("locale", locale);
		} else {
			throw new ActionException(
					String.format("The system doesn't support a such locale (%s)", locale));
		}	
		
		return Path.NO_PATH;
	}
}