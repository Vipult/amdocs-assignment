package com.amdocs.authorization.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import javax.validation.Validation;
import javax.validation.Validator;

import com.amdocs.authorization.events.source.KafkaSourceBean;
import com.amdocs.authorization.model.UserProfile;
import com.amdocs.authorization.repository.AuthorizationRestConnector;
import com.amdocs.authorization.service.AuthorizationService;
import com.amdocs.authorization.exception.ResourceNotFoundException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@Service
public class AuthorizationServiceImpl implements AuthorizationService{

	private static final Logger logger = LoggerFactory.getLogger(AuthorizationServiceImpl.class);

	@Autowired
	private AuthorizationRestConnector authorizationRestConnector;

	@Autowired
	KafkaSourceBean kafkaSourceBean;

	@Override
	public ResponseEntity<UserProfile> saveUserProfile(UserProfile userProfile){
		validateEntity(userProfile);
		logger.info("Saving new User Profile through RESTconnector");
		ResponseEntity<UserProfile> user = authorizationRestConnector.saveUserProfile(userProfile);
		return user;

	}

	@Override 
	public UserProfile updateUserProfile(UserProfile userProfile) {
		validateEntity(userProfile);
		logger.info("Sending an UPDATE event");
		kafkaSourceBean.publishUserProfileChange("UPDATE",userProfile.getId(),userProfile.getAddress(),userProfile.getPhoneNumber());
		return userProfile;

	}


	@Override	  
	public void deleteUserProfile(Long id) {
		logger.info("Sending a DELETE event");
		kafkaSourceBean.publishUserProfileChange("DELETE",id,"","");
		
	}
	
	private void validateEntity(UserProfile userProfile) {
		List<String> errorMessage = new ArrayList<String>();
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
 
		Set<ConstraintViolation<UserProfile>> constraintViolations = validator.validate(userProfile);
 
		for (ConstraintViolation<UserProfile> constraintViolation : constraintViolations) {
		errorMessage.add(constraintViolation.getMessage());
		}
 
		if (errorMessage.size() > 0) {
		throw new ConstraintViolationException(constraintViolations);
		}
 
	}
	
	public UserProfile getUserProfileById(Long id) throws ResourceNotFoundException {
		UserProfile userProfile = authorizationRestConnector.getUserProfileById(id); 
		if(userProfile==null){
			throw new ResourceNotFoundException("User Profile with id '" + id + "' does not exist");
		}
		return userProfile;
	}





}
