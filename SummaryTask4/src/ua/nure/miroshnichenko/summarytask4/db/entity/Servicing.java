package ua.nure.miroshnichenko.summarytask4.db.entity;

import ua.nure.miroshnichenko.summarytask4.myorm.Entity;
import ua.nure.miroshnichenko.summarytask4.myorm.annotation.Column;
import ua.nure.miroshnichenko.summarytask4.myorm.annotation.Enumerated;
import ua.nure.miroshnichenko.summarytask4.myorm.annotation.Table;

@Table("service")
public class Servicing implements Entity {

	private static final long serialVersionUID = -5908493945535660577L;

	@Column("service_id")
	private Integer id;

	@Column("service_name")
	private String name;

	@Enumerated
	@Column("service_type_id")
	private TypeServicing type;

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
	
	@Override
	public int hashCode() {
		return 37 * id;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		if (getClass() != obj.getClass()) {
			return false;
		}

		Servicing servicing = (Servicing) obj;
		return id == servicing.id;
	}
}
