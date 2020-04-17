package ua.nure.miroshnichenko.touragency.db.statistic;

import ua.nure.miroshnichenko.myorm.Entity;

public class AverageMarkTourStatistic implements Entity {

	private static final long serialVersionUID = -8331307021650711780L;
	
	private String hotelName;
	
	private Double averageMark;

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public Double getAverageMark() {
		return averageMark;
	}

	public void setAverageMark(Double averageMark) {
		this.averageMark = averageMark;
	}
}