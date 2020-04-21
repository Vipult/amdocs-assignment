package com.amdocs.authorization.repository;

import org.springframework.http.ResponseEntity;

import com.amdocs.authorization.model.UserProfile;

public interface AuthorizationRestConnector {

	public ResponseEntity<UserProfile> saveUserProfile(UserProfile userProfile);
	
	public UserProfile getUserProfileById(Long id);


}
