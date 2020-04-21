package com.amdocs.userprofile.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.amdocs.userprofile.model.UserProfile;
import com.amdocs.userprofile.repository.UserProfileRepository;
import com.amdocs.userprofile.service.impl.UserProfileServiceImpl;

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



}
