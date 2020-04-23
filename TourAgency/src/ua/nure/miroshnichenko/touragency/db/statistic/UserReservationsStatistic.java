package ua.nure.miroshnichenko.touragency.db.statistic;

import java.math.BigInteger;

import ua.nure.miroshnichenko.myorm.Entity;
import ua.nure.miroshnichenko.myorm.annotation.Column;

public class UserReservationsStatistic implements Entity {

	private static final long serialVersionUID = -1205940768284589756L;
	
	@Column("email")
	private String email;
	
	@Column("reservations_count")
	private BigInteger reservationsCount;

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public BigInteger getReservationsCount() {
		return reservationsCount;
	}

	public void setReservationsCount(BigInteger reservationsCount) {
		this.reservationsCount = reservationsCount;
	}
}
