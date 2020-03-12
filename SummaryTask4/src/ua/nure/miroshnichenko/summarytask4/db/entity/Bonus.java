package ua.nure.miroshnichenko.summarytask4.db.entity;

import ua.nure.miroshnichenko.summarytask4.myorm.Entity;
import ua.nure.miroshnichenko.summarytask4.myorm.annotation.Column;
import ua.nure.miroshnichenko.summarytask4.myorm.annotation.Table;

@Table("bonus")
public class Bonus implements Entity {

	private static final long serialVersionUID = 1075692988789175738L;

	@Column("bonus_id")
	private Integer id;

	@Column("discount")
	private Double discount;

	@Column("tour_id")
	private Integer tourId;

	private Tour tour;

	@Column("user_id")
	private Integer userId;

	private User user;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
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
