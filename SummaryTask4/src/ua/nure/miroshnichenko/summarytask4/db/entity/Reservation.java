package ua.nure.miroshnichenko.summarytask4.db.entity;

import java.sql.Date;

import ua.nure.miroshnichenko.summarytask4.myorm.Entity;
import ua.nure.miroshnichenko.summarytask4.myorm.annotation.Column;
import ua.nure.miroshnichenko.summarytask4.myorm.annotation.Enumerated;
import ua.nure.miroshnichenko.summarytask4.myorm.annotation.Table;

@Table("reservation")
public class Reservation implements Entity {

	private static final long serialVersionUID = 2091406430025116488L;

	@Column("reservation_id")
	private Integer id;

	@Column("reservation_date")
	private Date resrveDate;

	@Column("people_count")
	private Integer peopleCount;

	@Enumerated
	@Column("reservation_status_id")
	private ReservationStatus status;

	@Column("tour_id")
	private Integer tourId;

	private Tour tour;

	@Column("userId")
	private Integer userId;

	private User user;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getResrveDate() {
		return resrveDate;
	}

	public void setResrveDate(Date resrveDate) {
		this.resrveDate = resrveDate;
	}

	public Integer getPeopleCount() {
		return peopleCount;
	}

	public void setPeopleCount(Integer peopleCount) {
		this.peopleCount = peopleCount;
	}

	public ReservationStatus getStatus() {
		return status;
	}

	public void setStatus(ReservationStatus status) {
		this.status = status;
	}

	public Integer getTourId() {
		return tourId;
	}

	public void setTourId(Integer tourId) {
		this.tourId = tourId;
	}

	public Tour getTour() {
		return tour;
	}

	public void setTour(Tour tour) {
		this.tour = tour;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
