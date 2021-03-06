package ua.nure.miroshnichenko.touragency.db.entity;

import java.util.ArrayList;
import java.util.List;

import ua.nure.miroshnichenko.myorm.Entity;
import ua.nure.miroshnichenko.myorm.annotation.Column;
import ua.nure.miroshnichenko.myorm.annotation.PrimaryKey;
import ua.nure.miroshnichenko.myorm.annotation.Table;

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

	public static List<Facility> getFacilities(String[] names) {
		List<Facility> facilities = new ArrayList<>();
		if (names != null) {
			for (String name : names) {
				facilities.add(new Facility(name));
			}
		}
		return facilities;
	}

	@Override
	public int hashCode() {
		return 31 * name.hashCode();
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

		Facility facility = (Facility) obj;
		return name.equals(facility.name);
	}

	@Override
	public String toString() {
		return name;
	}
}
