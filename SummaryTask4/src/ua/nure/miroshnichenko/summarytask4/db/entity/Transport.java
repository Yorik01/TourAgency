package ua.nure.miroshnichenko.summarytask4.db.entity;

import java.sql.Date;

import ua.nure.miroshnichenko.summarytask4.myorm.Entity;
import ua.nure.miroshnichenko.summarytask4.myorm.annotation.Column;
import ua.nure.miroshnichenko.summarytask4.myorm.annotation.Enumerated;
import ua.nure.miroshnichenko.summarytask4.myorm.annotation.Table;

@Table("transport")
public class Transport implements Entity {

	private static final long serialVersionUID = -1093520271238694900L;

	@Column("transport_id")
	private Integer id;

	@Column("transport_code")
	private String code;

	@Column("takeoff_time")
	private Date takeoff;

	@Column("arrive_time")
	private Date arrive;

	@Column("max_places")
	private Integer maxPlaces;

	@Column("transport_price")
	private Double price;

	@Enumerated
	@Column("transport_type_id")
	private TransportType type;

	@Column("route_id")
	private Integer routeId;

	private Route route;

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getTakeoff() {
		return takeoff;
	}

	public void setTakeoff(Date takeoff) {
		this.takeoff = takeoff;
	}

	public Date getArrive() {
		return arrive;
	}

	public void setArrive(Date arrive) {
		this.arrive = arrive;
	}

	public Integer getMaxPlaces() {
		return maxPlaces;
	}

	public void setMaxPlaces(Integer maxPlaces) {
		this.maxPlaces = maxPlaces;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public TransportType getType() {
		return type;
	}

	public void setType(TransportType type) {
		this.type = type;
	}

	public Integer getRouteId() {
		return routeId;
	}

	public void setRouteId(Integer routeId) {
		this.routeId = routeId;
	}
}
