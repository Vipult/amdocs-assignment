package com.amdocs.authorization.service;

import com.amdocs.authorization.model.UserProfile;

import java.util.List;

public interface AuthorizationService {
	
	
	public void deleteUserProfile(Long id) ;
	
	public void saveUserProfile(UserProfile userProfile);
	public void updateUserProfile(UserProfile userprofile);
	 
	 

}
