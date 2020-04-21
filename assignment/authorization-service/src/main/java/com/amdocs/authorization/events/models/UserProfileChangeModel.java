package com.amdocs.authorization.events.models;

public class UserProfileChangeModel{
	private Long id;
	private String action;
	private String address;
	private String phoneNumber;
	public UserProfileChangeModel(Long id, String action, String address, String phoneNumber) {
		super();
		this.id = id;
		this.action = action;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
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