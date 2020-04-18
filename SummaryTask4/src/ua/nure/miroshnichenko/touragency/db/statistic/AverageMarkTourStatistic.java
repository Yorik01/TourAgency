package ua.nure.miroshnichenko.touragency.db.statistic;

import java.math.BigDecimal;

import ua.nure.miroshnichenko.myorm.Entity;
import ua.nure.miroshnichenko.myorm.annotation.Column;

public class AverageMarkTourStatistic implements Entity {

	private static final long serialVersionUID = -8331307021650711780L;
	
	@Column("hotel_name")
	private String hotelName;
	
	@Column("average_mark")
	private BigDecimal averageMark;

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public BigDecimal getAverageMark() {
		return averageMark;
	}

	public void setAverageMark(BigDecimal averageMark) {
		this.averageMark = averageMark;
	}
}