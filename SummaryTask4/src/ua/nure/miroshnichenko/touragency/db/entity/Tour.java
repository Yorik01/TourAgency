package ua.nure.miroshnichenko.touragency.db.entity;

import java.sql.Date;

import ua.nure.miroshnichenko.myorm.Entity;
import ua.nure.miroshnichenko.myorm.annotation.Column;
import ua.nure.miroshnichenko.myorm.annotation.Enumerated;
import ua.nure.miroshnichenko.myorm.annotation.Generated;
import ua.nure.miroshnichenko.myorm.annotation.PrimaryKey;
import ua.nure.miroshnichenko.myorm.annotation.Table;

@Table("tour")
public class Tour implements Entity {

	private static final long serialVersionUID = -8693081953079281619L;

	@PrimaryKey
	@Generated
	@Column("tour_id")
	private Integer id;

	@Column("agency_procent")
	private Integer agencyProcent;

	@Column("start_date")
	private Date startDate;

	@Column("end_date")
	private Date endDate;

	@Column("is_fired")
	private Integer fired;

	@Column("tour_max_discount")
	private Double maxDiscount;
	
	@Enumerated
	@Column("tour_type_id")
	private TourType type;

	@Column("hotel_id")
	private Integer hotelId;

	private Hotel hotel;

	@Column("transport_to_id")
	private Integer transportToId;

	private Transport transportTo;

	@Column("transport_back_id")
	private Integer transportBackId;

	private Transport transportBack;
	
	private Double price;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAgencyProcent() {
		return agencyProcent;
	}

	public void setAgencyProcent(Integer agencyProcent) {
		this.agencyProcent = agencyProcent;
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

	public Integer isFired() {
		return fired;
	}

	public void setFired(Integer fired) {
		this.fired = fired;
	}

	public TourType getType() {
		return type;
	}

	public void setType(TourType type) {
		this.type = type;
	}

	public Integer getHotelId() {
		return hotelId;
	}

	public void setHotelId(Integer hotelId) {
		this.hotelId = hotelId;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public Integer getTransportToId() {
		return transportToId;
	}

	public void setTransportToId(Integer transportToId) {
		this.transportToId = transportToId;
	}

	public Transport getTransportTo() {
		return transportTo;
	}

	public void setTransportTo(Transport transportTo) {
		this.transportTo = transportTo;
	}

	public Integer getTransportBackId() {
		return transportBackId;
	}

	public void setTransportBackId(Integer transportBackId) {
		this.transportBackId = transportBackId;
	}

	public Transport getTransportBack() {
		return transportBack;
	}

	@Override
	public int hashCode() {
		return 31 * id;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Tour other = (Tour) obj;
		return id == other.id;
	}

	public void setTransportBack(Transport transportBack) {
		this.transportBack = transportBack;
	}

	public Double getMaxDiscount() {
		return maxDiscount;
	}

	public void setMaxDiscount(Double maxDiscount) {
		this.maxDiscount = maxDiscount;
	}
	
	public Double getPrice() {
		return price;
	}
	
	public void setPrice(Double price) {
		this.price = price;
	}
	
	
}
