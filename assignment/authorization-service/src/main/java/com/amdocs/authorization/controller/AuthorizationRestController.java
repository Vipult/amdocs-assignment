package com.amdocs.authorization.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;


import com.amdocs.authorization.service.AuthorizationService;
import com.amdocs.authorization.model.UserProfile;;

@RestController
@RequestMapping(value="/authorization")
public class AuthorizationRestController {
	
	private static final Logger logger = LoggerFactory.getLogger(AuthorizationRestController.class);
	
	@Autowired
	 private AuthorizationService authorizationService;
	
	
	
	@PostMapping("/userprofiles")
	 public void saveUserProfile(@RequestBody UserProfile userProfile){	
		
		logger.info("Saving new User Profile in controller");
		
		authorizationService.saveUserProfile(userProfile);  
	 }
	
	@PutMapping("/userprofiles/{id}")
	 public void updateStatistic(@RequestBody UserProfile newProfile,@PathVariable(name="id")Long id){
		logger.info("Updating User Profile in Authorization Controller");
		authorizationService.updateUserProfile(newProfile);
	  }
	
	
	@DeleteMapping("/userprofiles/{id}")
   @ResponseStatus(HttpStatus.NO_CONTENT)
	 public void deleteUserProfile (@PathVariable(name="id")Long id) {
		logger.info("Deleting User in Authorization Controller");
		authorizationService.deleteUserProfile(id);
		
	}
		
	
			 

}
