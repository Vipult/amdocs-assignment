package com.amdocs.userprofile.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name = "USER_PROFILE")
public class UserProfile {
	
	

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	
	
	@Column(name = "ADDRESS")
	private String address;	
	
	@Column(name = "PHONENUMBER")
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
