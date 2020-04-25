package ua.nure.miroshnichenko.touragency.db.entity;

import java.sql.Timestamp;

import ua.nure.miroshnichenko.myorm.Entity;
import ua.nure.miroshnichenko.myorm.annotation.Column;
import ua.nure.miroshnichenko.myorm.annotation.Enumerated;
import ua.nure.miroshnichenko.myorm.annotation.Generated;
import ua.nure.miroshnichenko.myorm.annotation.PrimaryKey;
import ua.nure.miroshnichenko.myorm.annotation.Table;

@Table("reservation")
public class Reservation implements Entity {

	private static final long serialVersionUID = 2091406430025116488L;

	@PrimaryKey
	@Generated
	@Column("reservation_id")
	private Integer id;

	@Column("reserve_date")
	private Timestamp resrveDate;

	@Column("people_count")
	private Integer peopleCount;
	
	@Column("discount")
	private Double discount;
	
	@Enumerated
	@Column("reservation_status_id")
	private ReservationStatus status;

	@Column("tour_id")
	private Integer tourId;

	private Tour tour;

	@Column("user_id")
	private Integer userId;

	private User user;
	
	@Column("manager_id")
	private Integer managerId;
	
	private User manager;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getResrveDate() {
		return resrveDate;
	}

	public void setResrveDate(Timestamp resrveDate) {
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
	
	public Double getDiscount() {
		return discount;
	}
	
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	
	public Integer getManagerId() {
		return managerId;
	}
	
	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}
	
	public User getManager() {
		return manager;
	}
	
	public void setManager(User manager) {
		this.manager = manager;
	}
}
