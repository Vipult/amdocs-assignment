package com.amdocs.userprofile.repository;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.amdocs.userprofile.model.UserProfile;
import com.amdocs.userprofile.repository.UserProfileRepository;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class UserProfileRepositoryTest {
	
	@Autowired    
	 TestEntityManager entityManager;    
	 
	 @Autowired    
	 UserProfileRepository userProfileRespository;
	 
	 @Test    
	 public void verifySaveAtRepoLayer() 	 
	 {        
	 
	 UserProfile userProfile = new UserProfile();        
	 userProfile.setAddress("Viman Nagar,Pune");
	 userProfile.setPhoneNumber("9080796050");
	 userProfile = entityManager.persistAndFlush(userProfile);        
	 
	 assertEquals("Viman Nagar,Pune", userProfileRespository.findById(userProfile.getId()).get().getAddress());
 	assertEquals("9080796050",userProfileRespository.findById(userProfile.getId()).get().getPhoneNumber());
	 
	 }
	 
	
}
