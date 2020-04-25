package ua.nure.miroshnichenko.touragency.db.statistic;

import java.sql.Date;

import ua.nure.miroshnichenko.myorm.Entity;
import ua.nure.miroshnichenko.myorm.annotation.Column;

public class TourReservationsStatistic implements Entity {
	
	private static final long serialVersionUID = 6597141276960800807L;
	
	@Column("tour_id")
	private Integer tourId;
	
	@Column("hotel_name")
	private String hotelName;
	
	@Column("start_date")
	private Date startDate;
	
	@Column("end_date")
	private Date endDate;
	
	@Column("reservations_count")
	private Long reservationsCount;
	
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

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public Long getReservationsCount() {
		return reservationsCount;
	}

	public void setReservationsCount(Long reservationsCount) {
		this.reservationsCount = reservationsCount;
	}
}
