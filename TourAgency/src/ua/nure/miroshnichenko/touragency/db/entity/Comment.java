package ua.nure.miroshnichenko.touragency.db.entity;

import java.sql.Timestamp;

import ua.nure.miroshnichenko.myorm.Entity;
import ua.nure.miroshnichenko.myorm.annotation.Column;
import ua.nure.miroshnichenko.myorm.annotation.Generated;
import ua.nure.miroshnichenko.myorm.annotation.PrimaryKey;
import ua.nure.miroshnichenko.myorm.annotation.Table;

@Table("comment")
public class Comment implements Entity {

	private static final long serialVersionUID = -2778378195470085202L;

	@PrimaryKey
	@Generated
	@Column("comment_id")
	private Integer id;
	
	@Column("comment_date")
	private Timestamp date;
	
	@Column("comment_text")
	private String text;
	
	@Column("comment_mark")
	private Integer mark;
	
	@Column("user_id")
	private Integer userId;
	
	private User user;
	
	@Column("tour_id")
	private Integer tourId;
	
	private Tour tour;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Integer getMark() {
		return mark;
	}

	public void setMark(Integer mark) {
		if (mark >= 1 && mark <= 10) {
			this.mark = mark;
		} else {
			throw new IllegalArgumentException("The mark must belong to the range: [1, 10]");
		}
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
}
