package ua.nure.miroshnichenko.summarytask4.db.entity;

import java.util.Map;
import java.util.Set;

import ua.nure.miroshnichenko.summarytask4.myorm.Entity;
import ua.nure.miroshnichenko.summarytask4.myorm.annotation.Column;
import ua.nure.miroshnichenko.summarytask4.myorm.annotation.Enumerated;
import ua.nure.miroshnichenko.summarytask4.myorm.annotation.Generated;
import ua.nure.miroshnichenko.summarytask4.myorm.annotation.PrimaryKey;
import ua.nure.miroshnichenko.summarytask4.myorm.annotation.Table;

@Table("hotel")
public class Hotel implements Entity {

	private static final long serialVersionUID = -2001830352342554083L;

	@PrimaryKey
	@Generated
	@Column("hotel_id")
	private Integer id;

	@Column("hotel_name")
	private String name;

	@Column("hotel_info")
	private String info;

	@Column("hotel_address")
	private String address;

	@Column("hotel_name")
	private String tel;

	@Column("hotel_site")
	private String site;

	@Column("hotel_stars")
	private Byte stars;

	@Column("hotel_max_rooms")
	private Integer maxRooms;

	@Column("hotel_price")
	private Double price;

	@Enumerated
	@Column("hotel_type_id")
	private HotelType type;

	@Enumerated
	@Column("food_id")
	private Food food;

	@Enumerated
	@Column("beach_type_id")
	private Beach beach;

	private Set<Servicing> servicings;

	private Set<Facility> facilities;

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public Byte getStars() {
		return stars;
	}

	public void setStars(Byte stars) {
		this.stars = stars;
	}

	public Integer getMaxRooms() {
		return maxRooms;
	}

	public void setMaxRooms(Integer maxRooms) {
		this.maxRooms = maxRooms;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public HotelType getType() {
		return type;
	}

	public void setType(HotelType type) {
		this.type = type;
	}

	public Food getFood() {
		return food;
	}

	public void setFood(Food food) {
		this.food = food;
	}

	public Set<Servicing> getServicings() {
		return servicings;
	}
	
	public void setServicings(Set<Servicing> servicings) {
		this.servicings = servicings;
	}

	public Set<Facility> getFacilities() {
		return facilities;
	}

	public void setFacilities(Set<Facility> facilities) {
		this.facilities = facilities;
	}

	public Beach getBeach() {
		return beach;
	}

	public void setBeach(Beach beach) {
		this.beach = beach;
	}
}
