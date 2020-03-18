package ua.nure.miroshnichenko.summarytask4.web.action;

import java.util.HashMap;
import java.util.Map;

public class ActionFactory {
	// private static final Logger LOG = Logger.getLogger(ActionFactory.class);

	private static Map<String, Action> actions = new HashMap<>();

	static {
		// common actions

		// LOG.debug("action container was successfully initialized");
//		LOG.trace("Number of actions --> " + actions.size());
		actions.put("login", new LoginAction());
		actions.put("signup", new SignupAction());
		actions.put("addHotel", new AddHotelAction());
		actions.put("addTour", new AddTourAction());
		actions.put("addRoute", new AddRouteAction());
		actions.put("addTransport", new AddTransportAction());
		actions.put("allTours", new AllToursAction());
		actions.put("filterTour", new FilterTourAction());
		actions.put("hotelForm", new HotelFormAction());
		actions.put("routeForm", new RouteFormAction());
		actions.put("tourForm", new TourFormAction());
		actions.put("transportForm", new TransportFormAction());
	}

	/**
	 * Returns action object with the given name.
	 * 
	 * @param actionName Name of the action.
	 * @return action object.
	 */
	public static Action get(String actionName) {
		if (actionName == null || !actions.containsKey(actionName)) {
			//LOG.trace("action not found, name --> " + actionName);
			return actions.get("noaction");
		}

		return actions.get(actionName);
	}
}
