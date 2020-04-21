package com.amdocs.authorization.repository.impl;


import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

import com.amdocs.authorization.model.UserProfile;
import com.amdocs.authorization.repository.AuthorizationRestConnector;


@Repository
public class AuthorizationRestConnectorImpl implements AuthorizationRestConnector {

	private static final Logger logger = LoggerFactory.getLogger(AuthorizationRestConnectorImpl.class);

	@Autowired
	private RestTemplate restTemplate;	

	
	@Override
	public ResponseEntity<UserProfile> saveUserProfile(UserProfile userProfile){

		logger.info("Saving a new User Profile in connector");
		logger.info("statistic with address "+userProfile.getAddress()+" and phonenumber "+userProfile.getPhoneNumber());

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<Object> request = new HttpEntity<Object>(userProfile, headers);
		ResponseEntity<UserProfile> result = restTemplate.postForEntity("http://userprofileservice:8080/userprofilemgmt/userprofiles", request, UserProfile.class);
		return result;
	}
	
	@Override
	public UserProfile getUserProfileById(Long id){
		
		final String uri = "http://userprofileservice:8080/userprofilemgmt/userprofiles/{id}";
		
		Map<String, Long> params = new HashMap<String, Long>();
	    params.put("id",id);
		
		UserProfile user = restTemplate.getForObject(uri, UserProfile.class,params);
		return user;
	}


}