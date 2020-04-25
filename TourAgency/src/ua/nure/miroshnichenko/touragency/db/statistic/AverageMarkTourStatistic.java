package ua.nure.miroshnichenko.touragency.db.statistic;

import java.math.BigDecimal;
import java.sql.Date;

import ua.nure.miroshnichenko.myorm.Entity;
import ua.nure.miroshnichenko.myorm.annotation.Column;

public class AverageMarkTourStatistic implements Entity {

	private static final long serialVersionUID = -8331307021650711780L;
	
	@Column("tour_id")
	private Integer tourId;
	
	@Column("hotel_name")
	private String hotelName;
	
	@Column("start_date")
	private Date startDate;
	
	@Column("end_date")
	private Date endDate;
	
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

	public Integer getTourId() {
		return tourId;
	}

	public void setTourId(Integer tourId) {
		this.tourId = tourId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}