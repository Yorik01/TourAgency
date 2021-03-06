package ua.nure.miroshnichenko.touragency.db.entity;

import ua.nure.miroshnichenko.myorm.Entity;
import ua.nure.miroshnichenko.myorm.annotation.Column;
import ua.nure.miroshnichenko.myorm.annotation.Enumerated;
import ua.nure.miroshnichenko.myorm.annotation.Generated;
import ua.nure.miroshnichenko.myorm.annotation.PrimaryKey;
import ua.nure.miroshnichenko.myorm.annotation.Table;

@Table("users")
public class User implements Entity {

	private static final long serialVersionUID = -3414487325065065030L;

	@PrimaryKey
	@Generated
	@Column("user_id")
	private Integer id;

	@Column("email")
	private String email;

	@Column("password")
	private String password;

	@Column("is_banned")
	private Integer banned = 0;

	@Column("discount_step")
	private Double discountStep = 0d;

	@Enumerated
	@Column("role_id")
	private Role role;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer isBanned() {
		return banned;
	}

	public void setBanned(Integer banned) {
		this.banned = banned;
	}

	public Double getDiscountStep() {
		return discountStep;
	}

	public void setDiscountStep(Double discountStep) {
		this.discountStep = discountStep;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
