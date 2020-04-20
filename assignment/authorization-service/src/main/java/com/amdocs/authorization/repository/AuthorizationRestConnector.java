package com.amdocs.authorization.repository;

import java.util.List;

import com.amdocs.authorization.model.UserProfile;

public interface AuthorizationRestConnector {
	
	public void saveUserProfile(UserProfile userProfile);
	

}
