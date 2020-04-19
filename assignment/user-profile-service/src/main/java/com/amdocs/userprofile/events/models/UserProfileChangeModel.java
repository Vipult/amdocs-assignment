package com.amdocs.userprofile.events.models;

public class UserProfileChangeModel{
    private Long id;
    private String action;
   
    public UserProfileChangeModel(Long id, String action) {
		super();
		this.id = id;
		this.action = action;
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


    
  


}