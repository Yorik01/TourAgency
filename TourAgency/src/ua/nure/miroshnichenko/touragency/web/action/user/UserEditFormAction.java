package ua.nure.miroshnichenko.touragency.web.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.miroshnichenko.touragency.web.Path;
import ua.nure.miroshnichenko.touragency.web.action.Action;
import ua.nure.miroshnichenko.touragency.web.action.ActionException;

/**
 * The action to get a form to edit information about user.
 * 
 * @author Miroshnichenko Y. D.
 */
public class UserEditFormAction extends Action {

	private static final long serialVersionUID = 2631248514048265672L;
	
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException, ActionException {

		req.setAttribute("form", Path.PROFILE_EDIT_FORM);

		return Path.PROFILE_PAGE;
	}
}
