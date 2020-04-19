package com.amdocs.userprofile.service;

import com.amdocs.userprofile.model.UserProfile;
import com.amdocs.userprofile.repository.UserProfileRepository;
import com.amdocs.userprofile.exception.ResourceNotFoundException;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;



@Service
public class UserProfileServiceImpl implements UserProfileService {
	
	@Autowired
    private UserProfileRepository userProfileRepository;
	
	public UserProfile saveUserProfile(UserProfile profile){
       return userProfileRepository.save(profile);
    }

    public UserProfile updateUserProfile(UserProfile newProfile){
    	return userProfileRepository.save(newProfile);
    }

    public void deleteUserProfile(Long id){
    	userProfileRepository.deleteById(id);
    }
    
     
    public UserProfile getUserProfile(Long id) throws ResourceNotFoundException {
    	  UserProfile userprofile = userProfileRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User Profile with id '" + id + "' does no exist"));;
    	  return userprofile;
    	    }
}
