package com.amdocs.userprofile.service;

import com.amdocs.userprofile.model.UserProfile;
import com.amdocs.userprofile.repository.UserProfileRepository;
import com.amdocs.userprofile.Application;
import com.amdocs.userprofile.events.models.UserProfileChangeModel;
import com.amdocs.userprofile.exception.ResourceNotFoundException;

import java.util.Optional;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;




@Service
public class UserProfileServiceImpl implements UserProfileService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserProfileServiceImpl.class);
	
	@Autowired
    private UserProfileRepository userProfileRepository;
	
	public List<UserProfile> findAllUserProfiles(){
		return userProfileRepository.findAll();
	}
	
	public UserProfile saveUserProfile(UserProfile profile){
       return userProfileRepository.save(profile);
    }

    public UserProfile updateUserProfile(UserProfile newProfile){
    	return userProfileRepository.save(newProfile);
    }

    public void deleteUserProfile(Long id){
    	userProfileRepository.deleteById(id);
    }
    
    @StreamListener(Sink.INPUT)
	  public void loggerSink(UserProfileChangeModel userProfileChange) {
        logger.info("Received an event for User Profile Change id {} and action {} and address {} and phonenumber {}", userProfileChange.getId(),userProfileChange.getAction(),userProfileChange.getAddress(),userProfileChange.getPhoneNumber());    	
    	UserProfile userProfile = new UserProfile();
    	userProfile.setId(userProfileChange.getId());
    	userProfile.setAddress(userProfileChange.getAddress());
    	userProfile.setPhoneNumber(userProfileChange.getPhoneNumber());
        logger.info("Created a UserProfile Object with id "+userProfile.getId()+" with address "+userProfile.getAddress()+" with Phonenumber "+userProfile.getPhoneNumber());        
        if(userProfileChange.getAction().equalsIgnoreCase("UPDATE")){
    		userProfileRepository.save(userProfile);
    	}
    	if(userProfileChange.getAction().equalsIgnoreCase("DELETE")){
    		userProfileRepository.deleteById(userProfile.getId());
    	}
	
	       
	    }
    
     
    public UserProfile getUserProfile(Long id) throws ResourceNotFoundException {
    	  UserProfile userprofile = userProfileRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User Profile with id '" + id + "' does no exist"));;
    	  return userprofile;
    	    }
}
