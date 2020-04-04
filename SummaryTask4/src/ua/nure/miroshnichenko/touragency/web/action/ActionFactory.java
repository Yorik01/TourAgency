package ua.nure.miroshnichenko.touragency.web.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

public class ActionFactory {
	
	private static final Logger LOG = Logger.getLogger(ActionFactory.class);

	private static Map<String, Action> actions = new HashMap<>();

	static {
		// all actions

		LOG.debug("action container was successfully initialized");
		LOG.trace("Number of actions --> " + actions.size());
		
		actions.put("login", new LoginAction());
		actions.put("signup", new SignupAction());
		actions.put("saveHotel", new SaveHotelAction());
		actions.put("saveTour", new SaveTourAction());
		actions.put("saveRoute", new SaveRouteAction());
		actions.put("saveTransport", new SaveTransportAction());
		actions.put("allTours", new AllToursAction());
		actions.put("filterTour", new FilterTourAction());
		actions.put("hotelForm", new HotelFormAction());
		actions.put("routeForm", new RouteFormAction());
		actions.put("tourForm", new TourFormAction());
		actions.put("transportForm", new TransportFormAction());
		actions.put("allRoutes", new AllRoutesAction());
		actions.put("allHotels", new AllHotelsAction());
		actions.put("allTransports", new AllTransportsAction());
		actions.put("filterTour", new FilterTourAction());
		actions.put("filterForm", new FilterToursFormAction());
		actions.put("tourInfo", new TourInfoAction());
		actions.put("userReservations", new UserReservationsAction());
		actions.put("editUser", new EditUserAction());
		actions.put("userEditForm", new UserEditFormAction());
		actions.put("reserveTour", new ReserveTourAction());
		actions.put("revokeReservation", new RevokeReservationAction());
		actions.put("allReservations", new AllReservationsAction());
		actions.put("adminPage", new AdminPageAction());
		actions.put("setMaxDiscount", new SetMaxDiscountAction());
		actions.put("setDiscountStepForAllUsers", new SetDiscountStepForAllUsers());
		actions.put("createManager", new CreateManagerAction());
	}

	/**
	 * Rdeturns action object with the given name.
	 * 
	 * @param actionName Name of the action.
	 * @return action object.
	 */
	public static Action get(String actionName) {
		if (actionName == null || !actions.containsKey(actionName)) {
			LOG.trace("action not found, name --> " + actionName);
			return actions.get("noaction");
		}

		return actions.get(actionName);
	}
}
