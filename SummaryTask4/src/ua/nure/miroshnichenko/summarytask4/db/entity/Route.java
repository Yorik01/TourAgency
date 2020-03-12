package ua.nure.miroshnichenko.summarytask4.db.entity;

import ua.nure.miroshnichenko.summarytask4.myorm.Entity;
import ua.nure.miroshnichenko.summarytask4.myorm.annotation.Column;
import ua.nure.miroshnichenko.summarytask4.myorm.annotation.Table;

@Table("route")
public class Route implements Entity {

	private static final long serialVersionUID = -3050538718795497861L;

	@Column("route_id")
	private Integer id;

	@Column("route_to_id")
	private Integer routeToId;

	@Column("route_from_id")
	private Integer routeFromId;

	private Place to;

	private Place from;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRouteToId() {
		return routeToId;
	}

	public void setRouteToId(Integer routeToId) {
		this.routeToId = routeToId;
	}

	public Integer getRouteFromId() {
		return routeFromId;
	}

	public void setRouteFromId(Integer routeFromId) {
		this.routeFromId = routeFromId;
	}

	public Place getTo() {
		return to;
	}

	public void setTo(Place to) {
		this.to = to;
	}

	public Place getFrom() {
		return from;
	}

	public void setFrom(Place from) {
		this.from = from;
	}
}
