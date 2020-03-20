package ua.nure.miroshnichenko.summarytask4.web.action;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.miroshnichenko.summarytask4.service.ServiceFactory;

public abstract class Action implements Serializable {

	private static final long serialVersionUID = -7307441034600093272L;
	
	protected ServiceFactory serviceFactory = ServiceFactory.getInstance();

	public abstract String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException, ActionException;
	
	@Override
	public final String toString() {
		return getClass().getSimpleName();
	}
}
