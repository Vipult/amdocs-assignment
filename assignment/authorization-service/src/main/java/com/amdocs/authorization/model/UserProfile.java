package com.amdocs.authorization.model;


public class UserProfile {
	
	

	private Long id;
	
	private String address;	
	
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
