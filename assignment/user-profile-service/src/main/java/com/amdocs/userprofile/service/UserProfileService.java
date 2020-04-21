package com.amdocs.userprofile.service;

import java.util.List;

import com.amdocs.userprofile.exception.ResourceNotFoundException;
import com.amdocs.userprofile.model.UserProfile;

public interface UserProfileService {

	public UserProfile getUserProfile(Long id) throws ResourceNotFoundException;

	public UserProfile saveUserProfile(UserProfile userProfile);	  

	public List<UserProfile> findAllUserProfiles();
}
