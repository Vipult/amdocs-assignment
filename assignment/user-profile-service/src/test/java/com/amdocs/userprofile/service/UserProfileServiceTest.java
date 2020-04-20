package com.amdocs.userprofile.service;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.amdocs.userprofile.exception.ResourceNotFoundException;
import com.amdocs.userprofile.model.UserProfile;
import com.amdocs.userprofile.repository.UserProfileRepository;
import com.amdocs.userprofile.service.UserProfileService;
import com.amdocs.userprofile.service.UserProfileServiceImpl;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers.TimestampDeserializer;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import org.junit.runner.RunWith;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserProfileServiceTest {
	
	@Mock
	private UserProfileRepository userProfileRepository;
	
	@InjectMocks
	private UserProfileServiceImpl userProfileServiceImpl;
	
	@Test
	public void verifySaveUser(){
		
		UserProfile userProfile= new UserProfile();
    	userProfile.setAddress("Viman Nagar,Pune");
    	userProfile.setPhoneNumber("9080796050");
    	
    	when(userProfileRepository.save(userProfile)).thenReturn(userProfile);
    	
    	UserProfile newUserProfile= userProfileServiceImpl.saveUserProfile(userProfile);
    	assertEquals("Viman Nagar,Pune", newUserProfile.getAddress());
		assertEquals("9080796050",newUserProfile.getPhoneNumber());
		
		
	}
	
	@Test
	public void verifyUpdateUserForCorrectID(){
		
		UserProfile userProfile= new UserProfile();
    	userProfile.setAddress("Viman Nagar,Pune");
    	userProfile.setPhoneNumber("9080796050");
    	
    	Optional<UserProfile> uf = Optional.of(userProfile);
    	
    	
    	when(userProfileRepository.save(userProfile)).thenReturn(userProfile);
    	
    	
    	UserProfile newUserProfile= userProfileServiceImpl.updateUserProfile(userProfile);
    	assertEquals(userProfile.getAddress(), newUserProfile.getAddress());
		assertEquals(userProfile.getPhoneNumber(),newUserProfile.getPhoneNumber());
		
		
	}
	
	
	
	@Test
	public void verifyDeleteUserForCorrectID(){
		
		
    	Mockito.doNothing().when(userProfileRepository).deleteById(1L);
    	//Mockito.doNothing().when(userProfileRepository).findById(1L);
    	
    	
    	userProfileServiceImpl.deleteUserProfile(1L);
    	//verify(userProfileRepository, times(1)).findById(1L);
    	verify(userProfileRepository, times(1)).deleteById(1L);
		
		
	}


}
