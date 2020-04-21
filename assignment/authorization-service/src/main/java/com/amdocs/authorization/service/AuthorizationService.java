package com.amdocs.authorization.service;

import org.springframework.http.ResponseEntity;

import com.amdocs.authorization.exception.ResourceNotFoundException;
import com.amdocs.authorization.model.UserProfile;

public interface AuthorizationService {


	public void deleteUserProfile(Long id) ;

	public ResponseEntity<UserProfile> saveUserProfile(UserProfile userProfile);
	public UserProfile updateUserProfile(UserProfile userprofile);
	


}
