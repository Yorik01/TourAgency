package ua.nure.miroshnichenko.summarytask4.db.entity;

import ua.nure.miroshnichenko.summarytask4.myorm.Entity;
import ua.nure.miroshnichenko.summarytask4.myorm.annotation.Column;
import ua.nure.miroshnichenko.summarytask4.myorm.annotation.PrimaryKey;
import ua.nure.miroshnichenko.summarytask4.myorm.annotation.Table;

@Table("facility")
public class Facility implements Entity {

	private static final long serialVersionUID = -214975068663619873L;

	@PrimaryKey
	@Column("facility_id")
	private Integer id;

	@Column("facility_name")
	private String name;

	public Facility() {
		
	}
	
	public Facility(String name) {
		this.name = name;
	}
	
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

	@Override
	public int hashCode() {
		return 37 * name.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		if (getClass() != obj.getClass()) {
			return false;
		}

		Facility facility = (Facility) obj;
		return name.equals(facility.name);
	}
}
