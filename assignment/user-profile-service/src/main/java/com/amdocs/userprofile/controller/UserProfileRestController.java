package com.amdocs.userprofile.controller;

import java.net.URI;

import com.amdocs.userprofile.service.UserProfileService;
import com.amdocs.userprofile.exception.ResourceNotFoundException;
import com.amdocs.userprofile.model.UserProfile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;

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
	
	@GetMapping("/userprofiles")
    public List < UserProfile > getAllUserProfiles() {
        return userProfileService.findAllUserProfiles();
    }
	  
	@ResponseStatus(HttpStatus.OK)
	 @PostMapping("/userprofiles")
	 public UserProfile  CreateUserProfile(@RequestBody UserProfile newProfile){		
		 
		 logger.info("New User saved successfully");
		 return userProfileService.saveUserProfile(newProfile);
		 
			 }  
	 

}
