package ua.nure.miroshnichenko.touragency.db.entity;

import ua.nure.miroshnichenko.myorm.Entity;
import ua.nure.miroshnichenko.myorm.annotation.Column;
import ua.nure.miroshnichenko.myorm.annotation.Generated;
import ua.nure.miroshnichenko.myorm.annotation.PrimaryKey;
import ua.nure.miroshnichenko.myorm.annotation.Table;

@Table("route")
public class Route implements Entity {

	private static final long serialVersionUID = -3050538718795497861L;

	@PrimaryKey
	@Generated
	@Column("route_id")
	private Integer id;

	@Column("route_from")
	private Integer routeFromId;
	
	@Column("route_to")
	private Integer routeToId;

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
	
	@Override
	public String toString() {
		return "{from: " + from.toString() + ", to: " + to + "}";
	}
}
