package it.marco.digrigoli.entities;

import java.util.Collection;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name="user")
@ToString
public class User {
	
	@Id
	@GeneratedValue
	private Long id;
	@Pattern(regexp = "[a-zA-Z0-9]{1,50}", message = "username invalid")
	@NotNull
	private String username;
	private String twoFactorSecret;
	@Email
	private String email;
	@Pattern(regexp = "[a-zA-Z\\\\èàùìò\s]{1,50}", message = "name invalid")
	private String name;
	@Pattern(regexp = "[a-zA-Z\\\\èàùìò\s]{1,50}", message = "surname invalid")
	private String surname;
	private String password;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name="role_id"))
	private List<Role> roles;
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
	private List<UserLog> userLogs;
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
	private List<Geocoding> geocodings;
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
	private List<Forecast> forecasts;
	
	public List<Long> getRoleIds() {
		return roles.stream().map((role) -> {
			return role.getId();
		}).toList();
	}
	
	public boolean getTwoFactorEnabled() {
		return this.twoFactorSecret != null && !this.twoFactorSecret.trim().isBlank();
	}

}
