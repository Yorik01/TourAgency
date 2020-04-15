package ua.nure.miroshnichenko.touragency.db.statistic;

import ua.nure.miroshnichenko.myorm.Entity;

public class RatingToursStatistic implements Entity {

	private static final long serialVersionUID = -8331307021650711780L;
	
	private String hotelName;
	
	private Double rating;

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}
}