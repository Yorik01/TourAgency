package ua.nure.miroshnichenko.summarytask4.db.entity;

import ua.nure.miroshnichenko.summarytask4.myorm.Entity;
import ua.nure.miroshnichenko.summarytask4.myorm.annotation.Column;
import ua.nure.miroshnichenko.summarytask4.myorm.annotation.Enumerated;
import ua.nure.miroshnichenko.summarytask4.myorm.annotation.Table;

@Table("users")
public class User implements Entity {

	private static final long serialVersionUID = -3414487325065065030L;

	@Column("user_id")
	private Integer id;

	@Column("email")
	private String email;

	@Column("password")
	private String password;

	@Column("is_banned")
	private Boolean isBanned;

	@Column("discount_step")
	private Byte discountStep;

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

	public Boolean isBanned() {
		return isBanned;
	}

	public void setBanned(Boolean isBanned) {
		this.isBanned = isBanned;
	}

	public Byte getDiscountStep() {
		return discountStep;
	}

	public void setDiscountStep(Byte discountStep) {
		this.discountStep = discountStep;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
