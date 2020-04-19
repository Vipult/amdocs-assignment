package com.amdocs.userprofile.service;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.amdocs.userprofile.model.UserProfile;
import com.amdocs.userprofile.repository.UserProfileRepository;
import com.amdocs.userprofile.service.UserProfileService;
import com.amdocs.userprofile.service.UserProfileServiceImpl;

import static org.mockito.Mockito.when;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
	public void when_save_user_it_should_return_user(){
		
		UserProfile userProfile= new UserProfile();
    	userProfile.setAddress("Viman Nagar,Pune");
    	userProfile.setPhoneNumber("9080796050");
    	
    	when(userProfileRepository.save(userProfile)).thenReturn(userProfile);
    	
    	UserProfile newUserProfile= userProfileServiceImpl.saveUserProfile(userProfile);
    	assertEquals("Viman Nagar,Pune", newUserProfile.getAddress());
		assertEquals("9080796050",newUserProfile.getPhoneNumber());
		
		
	}

}
