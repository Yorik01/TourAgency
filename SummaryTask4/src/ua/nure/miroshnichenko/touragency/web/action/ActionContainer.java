package ua.nure.miroshnichenko.touragency.web.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import ua.nure.miroshnichenko.touragency.web.action.comment.DeleteCommentAction;
import ua.nure.miroshnichenko.touragency.web.action.comment.SaveCommentAction;
import ua.nure.miroshnichenko.touragency.web.action.comment.UserCommentsAction;
import ua.nure.miroshnichenko.touragency.web.action.discount.SetDiscountStepForAllUsers;
import ua.nure.miroshnichenko.touragency.web.action.discount.SetMaxDiscountAction;
import ua.nure.miroshnichenko.touragency.web.action.hotel.AllHotelsAction;
import ua.nure.miroshnichenko.touragency.web.action.hotel.DeleteHotelAction;
import ua.nure.miroshnichenko.touragency.web.action.hotel.HotelFormAction;
import ua.nure.miroshnichenko.touragency.web.action.hotel.SaveHotelAction;
import ua.nure.miroshnichenko.touragency.web.action.reservation.AllReservationsAction;
import ua.nure.miroshnichenko.touragency.web.action.reservation.PayReservationAction;
import ua.nure.miroshnichenko.touragency.web.action.reservation.ReserveTourAction;
import ua.nure.miroshnichenko.touragency.web.action.reservation.RevokeReservationAction;
import ua.nure.miroshnichenko.touragency.web.action.reservation.UserReservationsAction;
import ua.nure.miroshnichenko.touragency.web.action.route.AllRoutesAction;
import ua.nure.miroshnichenko.touragency.web.action.route.DeleteRouteAction;
import ua.nure.miroshnichenko.touragency.web.action.route.RouteFormAction;
import ua.nure.miroshnichenko.touragency.web.action.route.SaveRouteAction;
import ua.nure.miroshnichenko.touragency.web.action.statistic.AverageMarkTourStatisticAction;
import ua.nure.miroshnichenko.touragency.web.action.statistic.ManagerRevenueStatisticAction;
import ua.nure.miroshnichenko.touragency.web.action.statistic.TourReservationsStatisticAction;
import ua.nure.miroshnichenko.touragency.web.action.statistic.UserReservationsStatisticAction;
import ua.nure.miroshnichenko.touragency.web.action.tour.AllToursAction;
import ua.nure.miroshnichenko.touragency.web.action.tour.DeleteTourAction;
import ua.nure.miroshnichenko.touragency.web.action.tour.FilterTourAction;
import ua.nure.miroshnichenko.touragency.web.action.tour.FilterToursFormAction;
import ua.nure.miroshnichenko.touragency.web.action.tour.SaveTourAction;
import ua.nure.miroshnichenko.touragency.web.action.tour.SetTourFiredAction;
import ua.nure.miroshnichenko.touragency.web.action.tour.TourFormAction;
import ua.nure.miroshnichenko.touragency.web.action.tour.TourInfoAction;
import ua.nure.miroshnichenko.touragency.web.action.transport.AllTransportsAction;
import ua.nure.miroshnichenko.touragency.web.action.transport.DeleteTransportAction;
import ua.nure.miroshnichenko.touragency.web.action.transport.SaveTransportAction;
import ua.nure.miroshnichenko.touragency.web.action.transport.TransportFormAction;
import ua.nure.miroshnichenko.touragency.web.action.user.AdminPageAction;
import ua.nure.miroshnichenko.touragency.web.action.user.AllUsersAction;
import ua.nure.miroshnichenko.touragency.web.action.user.BannUserAction;
import ua.nure.miroshnichenko.touragency.web.action.user.CreateManagerAction;
import ua.nure.miroshnichenko.touragency.web.action.user.EditUserAction;
import ua.nure.miroshnichenko.touragency.web.action.user.LoginAction;
import ua.nure.miroshnichenko.touragency.web.action.user.SignupAction;
import ua.nure.miroshnichenko.touragency.web.action.user.UnbannUserAction;
import ua.nure.miroshnichenko.touragency.web.action.user.UserEditFormAction;

public class ActionContainer {
	
	private static final Logger LOG = Logger.getLogger(ActionContainer.class);

	private static final Map<String, Action> actions = new HashMap<>();

	static {
		// all actions

		LOG.debug("action container was successfully initialized");
		LOG.trace("Number of actions --> " + actions.size());
		
		actions.put("no", new NoAction());
		actions.put("login", new LoginAction());
		actions.put("signup", new SignupAction());
		actions.put("saveHotel", new SaveHotelAction());
		actions.put("saveTour", new SaveTourAction());
		actions.put("saveRoute", new SaveRouteAction());
		actions.put("saveTransport", new SaveTransportAction());
		actions.put("deleteRoute", new DeleteRouteAction());
		actions.put("deleteTransport", new DeleteTransportAction());
		actions.put("deleteHotel", new DeleteHotelAction());
		actions.put("deleteTour", new DeleteTourAction());
		actions.put("allTours", new AllToursAction());
		actions.put("filterTour", new FilterTourAction());
		actions.put("hotelForm", new HotelFormAction());
		actions.put("routeForm", new RouteFormAction());
		actions.put("tourForm", new TourFormAction());
		actions.put("transportForm", new TransportFormAction());
		actions.put("allRoutes", new AllRoutesAction());
		actions.put("allHotels", new AllHotelsAction());
		actions.put("allTransports", new AllTransportsAction());
		actions.put("filterTours", new FilterTourAction());
		actions.put("filterForm", new FilterToursFormAction());
		actions.put("tourInfo", new TourInfoAction());
		actions.put("userReservations", new UserReservationsAction());
		actions.put("editUser", new EditUserAction());
		actions.put("userEditForm", new UserEditFormAction());
		actions.put("reserveTour", new ReserveTourAction());
		actions.put("revokeReservation", new RevokeReservationAction());
		actions.put("payReservation", new PayReservationAction());
		actions.put("allReservations", new AllReservationsAction());
		actions.put("adminPage", new AdminPageAction());
		actions.put("setMaxDiscount", new SetMaxDiscountAction());
		actions.put("setDiscountStepForAllUsers", new SetDiscountStepForAllUsers());
		actions.put("createManager", new CreateManagerAction());
		actions.put("allUsers", new AllUsersAction());
		actions.put("bannUser", new BannUserAction());
		actions.put("unbannUser", new UnbannUserAction());
		actions.put("setTourFired", new SetTourFiredAction());
		actions.put("changeLocale", new ChangeLocaleAction());
		actions.put("saveComment", new SaveCommentAction());
		actions.put("deleteComment", new DeleteCommentAction());
		actions.put("userComments", new UserCommentsAction());
		actions.put("averageMarkTourStatistic", new AverageMarkTourStatisticAction());
		actions.put("managerRevenueStatistic", new ManagerRevenueStatisticAction());
		actions.put("tourReservationsStatistic", new TourReservationsStatisticAction());
		actions.put("userReservationsStatistic", new UserReservationsStatisticAction());
	}

	/**
	 * Returns action object with the given name.
	 * 
	 * @param actionName Name of the action.
	 * @return action object.
	 */
	public static Action get(String actionName) {
		if (actionName == null || !actions.containsKey(actionName)) {
			LOG.trace("action not found, name --> " + actionName);
			return actions.get("no");
		}

		return actions.get(actionName);
	}
}
