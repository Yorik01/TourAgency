package ua.nure.miroshnichenko.touragency.db.statistic;

import ua.nure.miroshnichenko.myorm.Entity;

public class UserReservationsStatistic implements Entity {

	private static final long serialVersionUID = -1205940768284589756L;
	
	private String email;
	
	private Integer reservationsCount;

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Integer getReservationsCount() {
		return reservationsCount;
	}

	public void setReservationsCount(Integer reservationsCount) {
		this.reservationsCount = reservationsCount;
	}
}
