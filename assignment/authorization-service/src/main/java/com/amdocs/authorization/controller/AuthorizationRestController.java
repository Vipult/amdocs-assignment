package com.amdocs.authorization.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.amdocs.authorization.exception.ResourceNotFoundException;
import com.amdocs.authorization.model.UserProfile;
import com.amdocs.authorization.service.AuthorizationService;;

@RestController
@RequestMapping(value="/authorization")
public class AuthorizationRestController {

	private static final Logger logger = LoggerFactory.getLogger(AuthorizationRestController.class);

	@Autowired
	private AuthorizationService authorizationService;



	/**
	 * Create User Profile
	 * @param userProfile
	 */
	@PostMapping("/userprofiles")
	public ResponseEntity<UserProfile> saveUserProfile(@RequestBody UserProfile userProfile){	

		logger.info("Saving new User Profile in controller");

		return authorizationService.saveUserProfile(userProfile);  
	}

	/**
	 * Update User Profile
	 * @param newProfile
	 * @param id
	 */
	@PutMapping("/userprofiles/{id}")
	public UserProfile updateStatistic(@RequestBody UserProfile newProfile,@PathVariable(name="id")Long id){
		logger.info("Updating User Profile in Authorization Controller");
		return authorizationService.updateUserProfile(newProfile);
	}


	/**
	 * Delete User Profile
	 * @param id
	 */
	@DeleteMapping("/userprofiles/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public Map < String, Boolean > deleteUserProfile (@PathVariable(name="id")Long id) throws ResourceNotFoundException{
		logger.info("Deleting User in Authorization Controller");
		UserProfile userProfile = authorizationService.getUserProfileById(id);
		if(userProfile==null){
			throw new ResourceNotFoundException("User Profile with id '" + id + "' does no exist");
		}
		
		authorizationService.deleteUserProfile(id);
		Map < String, Boolean > response = new HashMap < > ();
        response.put("deleted", Boolean.TRUE);
        return response;

	}





}
