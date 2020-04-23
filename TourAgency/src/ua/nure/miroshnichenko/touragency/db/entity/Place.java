package ua.nure.miroshnichenko.touragency.db.entity;

import ua.nure.miroshnichenko.myorm.Entity;
import ua.nure.miroshnichenko.myorm.annotation.Column;
import ua.nure.miroshnichenko.myorm.annotation.Generated;
import ua.nure.miroshnichenko.myorm.annotation.PrimaryKey;
import ua.nure.miroshnichenko.myorm.annotation.Table;

@Table("place")
public class Place implements Entity {

	private static final long serialVersionUID = -3243212697462964857L;

	@PrimaryKey
	@Generated
	@Column("place_id")
	private Integer id;

	@Column("place_country")
	private String country;

	@Column("place_city")
	private String city;

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "{country: " + country + ", city: " + city + "}";
	}
}
