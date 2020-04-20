package com.amdocs.authorization.service.impl;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.amdocs.authorization.events.source.SimpleSourceBean;
import com.amdocs.authorization.model.UserProfile;
import com.amdocs.authorization.repository.AuthorizationRestConnector;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.amdocs.authorization.service.AuthorizationService;

@Service
public class AuthorizationServiceImpl implements AuthorizationService{
	
	private static final Logger logger = LoggerFactory.getLogger(AuthorizationServiceImpl.class);
	
	@Autowired
	private AuthorizationRestConnector authorizationRestConnector;
	
	@Autowired
    SimpleSourceBean simpleSourceBean;
	
     @Override
	 public void saveUserProfile(UserProfile userProfile){
		 logger.info("Saving new User Profile in service");
		 authorizationRestConnector.saveUserProfile(userProfile);
 
}
	
	 @Override 
	  public void updateUserProfile(UserProfile userProfile) {
		 logger.info("Sending an UPDATE event");
		simpleSourceBean.publishUserProfileChange("UPDATE",userProfile.getId(),userProfile.getAddress(),userProfile.getPhoneNumber());

	 }

	 
	 @Override	  
	 public void deleteUserProfile(Long id){
		logger.info("Sending a DELETE event");
 simpleSourceBean.publishUserProfileChange("DELETE",id,"","");
	 }
	 
	
	 
	 

}
