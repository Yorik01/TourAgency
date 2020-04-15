package ua.nure.miroshnichenko.touragency.db.statistic;

import ua.nure.miroshnichenko.myorm.Entity;
import ua.nure.miroshnichenko.myorm.annotation.Column;

public class ManagerRevenueStatistic implements Entity {
	
	private static final long serialVersionUID = -1083010085363031466L;

	@Column("email")
	private String email;
	
	@Column("revenue")
	private Double revenue;

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Double getRevenue() {
		return revenue;
	}

	public void setRevenue(Double revenue) {
		this.revenue = revenue;
	}
}
