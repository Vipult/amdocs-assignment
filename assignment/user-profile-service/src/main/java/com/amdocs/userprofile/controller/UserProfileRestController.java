package com.amdocs.userprofile.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.amdocs.userprofile.model.UserProfile;
import com.amdocs.userprofile.service.UserProfileService;

/**
 * User Profile API REST Controller
 * 
 * @author Vipul Tulsulkar
 * 
 */
@RestController
@RequestMapping(value="/userprofilemgmt")
public class UserProfileRestController {

	private static final Logger logger = LoggerFactory.getLogger(UserProfileRestController.class);

	@Autowired
	private UserProfileService userProfileService;

	/**
	 * Test Get Endpoint
	 * @return List<UserProfile>
	 */
	@GetMapping("/userprofiles")
	public List < UserProfile > getAllUserProfiles() {
		return userProfileService.findAllUserProfiles();
	}

	/**
	 * Create New User Profile
	 * @param newProfile
	 * @return
	 */
	@ResponseStatus(HttpStatus.OK)
	@PostMapping("/userprofiles")
	public UserProfile  CreateUserProfile(@RequestBody UserProfile newProfile){		

		logger.info("New User saved successfully");
		return userProfileService.saveUserProfile(newProfile);

	}  


}
