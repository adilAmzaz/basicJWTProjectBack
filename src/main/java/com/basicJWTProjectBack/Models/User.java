package com.basicJWTProjectBack.Models;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.basicJWTProjectBack.Utils.Utils;

import javax.persistence.JoinColumn;


@Entity
public class User {
	
	/**
	 * 
	 */

	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	private String lastName;
	private String firstName;
	private boolean isFamele;
	private LocalDate birtheDate;
	private String password;
	private String phone;
	private String adress;
	private String zipCode;
	
	@Column(nullable=false,unique = true, length = 50)
	private String username;
	

	private String city;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "user_roles", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();
	public User() {
		super();
	}
	
	public Long getId() {
		return Id;
	}

	public void setId(Long Id) {
		this.Id = Id;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		if(roles != null) {
			this.roles.clear();
			this.roles.addAll(roles);
		}
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}
	
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public LocalDate getBirtheDate() {
		return birtheDate;
	}
	public void setBirtheDate(LocalDate birtheDate) {
		this.birtheDate = birtheDate;
	}
	
	public boolean getIsFamele() {
		return isFamele;
	}

	public void setIsFamele(boolean isFamele) {
		this.isFamele = isFamele;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}


	public String getPassword() {
		return password;
	}
    public void setPassword(String password) {
    	this.password = Utils.bcryptEncoder.encode(password);
    }
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return adress;
	}
	public void setAddress(String addresse) {
		this.adress = addresse;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [getLastName()=");
		builder.append(getLastName());
		builder.append(", getFirstName()=");
		builder.append(getFirstName());
		builder.append(", isFamele()=");
		builder.append(getIsFamele());
		builder.append(", getBirtheDate()=");
		builder.append(getBirtheDate());
		builder.append(", isAdminRights()=");
		builder.append(", getNumber()=");
		builder.append(", getEmail()=");
		builder.append(", getPassword()=");
		builder.append(getPassword());
		builder.append(", getPhone()=");
		builder.append(getPhone());
		builder.append(", getAddresse()=");
		builder.append(getAddress());
		builder.append(", getZipCode()=");
		builder.append(getZipCode());
		builder.append(", getCity()=");
		builder.append(getCity());
		builder.append("]");
		return builder.toString();
	}



	

}
