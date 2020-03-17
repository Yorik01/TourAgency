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
