package ua.nure.miroshnichenko.touragency.db.statistic;

import java.math.BigInteger;

import ua.nure.miroshnichenko.myorm.Entity;
import ua.nure.miroshnichenko.myorm.annotation.Column;

public class TourReservationsStatistic implements Entity {
	
	private static final long serialVersionUID = 6597141276960800807L;
	
	@Column("hotel_name")
	private String hotelName;
	
	@Column("reservations_count")
	private BigInteger reservationsCount;

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public BigInteger getReservationsCount() {
		return reservationsCount;
	}

	public void setReservationsCount(BigInteger reservationsCount) {
		this.reservationsCount = reservationsCount;
	}
}
