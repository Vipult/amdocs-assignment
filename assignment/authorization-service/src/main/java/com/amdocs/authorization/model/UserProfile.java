package com.amdocs.authorization.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "USER_PROFILE")
public class UserProfile {



	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;




	@Column(name = "ADDRESS")
	private String address;	

	@Column(name = "PHONENUMBER")
	@Size(min = 10, max = 10, message = "PhoneNumber should be 10 digit")
	@Pattern(regexp="^[0-9]{10}$",message = "PhoneNumber should be numeric")
	private String phoneNumber;

	public UserProfile(){
		super();
	}

	public UserProfile(Long id, String address, String phoneNumber) {
		super();
		this.id = id;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}





}
