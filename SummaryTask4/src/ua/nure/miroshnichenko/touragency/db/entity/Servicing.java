package ua.nure.miroshnichenko.touragency.db.entity;

import java.util.ArrayList;
import java.util.List;

import ua.nure.miroshnichenko.myorm.Entity;
import ua.nure.miroshnichenko.myorm.annotation.Column;
import ua.nure.miroshnichenko.myorm.annotation.Enumerated;
import ua.nure.miroshnichenko.myorm.annotation.Generated;
import ua.nure.miroshnichenko.myorm.annotation.PrimaryKey;
import ua.nure.miroshnichenko.myorm.annotation.Table;

@Table("service")
public class Servicing implements Entity {

	private static final long serialVersionUID = -5908493945535660577L;

	@PrimaryKey
	@Generated
	@Column("service_id")
	private Integer id;

	@Column("service_name")
	private String name;

	@Enumerated
	@Column("service_type_id")
	private TypeServicing type;

	public Servicing() {

	}

	public Servicing(String name) {
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

	public TypeServicing getType() {
		return type;
	}

	public void setType(TypeServicing type) {
		this.type = type;
	}

	public static List<Servicing> getServicings(String[] names) {
		List<Servicing> servicings = new ArrayList<>();
		if (names != null) {
			for (String name : names) {
				servicings.add(new Servicing(name));
			}
		}
		return servicings;
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

		Servicing servicing = (Servicing) obj;
		return name.equals(servicing.name);
	}

	@Override
	public String toString() {
		return name;
	}
}
