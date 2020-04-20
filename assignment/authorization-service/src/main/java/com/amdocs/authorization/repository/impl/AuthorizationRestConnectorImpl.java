package com.amdocs.authorization.repository.impl;


import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.sql.Types;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URI;
import java.net.URISyntaxException;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import java.net.Proxy;
import java.net.InetSocketAddress;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.validation.Validator;

import com.amdocs.authorization.model.UserProfile;
import com.amdocs.authorization.repository.AuthorizationRestConnector;


@Repository
public class AuthorizationRestConnectorImpl implements AuthorizationRestConnector {
	
	private static final Logger logger = LoggerFactory.getLogger(AuthorizationRestConnectorImpl.class);
	
	@Autowired
	private RestTemplate restTemplate;	
		   
	   @Override
	    public void saveUserProfile(UserProfile userProfile){
		  
		   logger.info("Saving a new User Profile in connector");
		   logger.info("statistic with address "+userProfile.getAddress()+" and phonenumber "+userProfile.getPhoneNumber());
		   
			HttpHeaders headers = new HttpHeaders();
	    	headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    	headers.setContentType(MediaType.APPLICATION_JSON);
		   
		   HttpEntity<Object> request = new HttpEntity<Object>(userProfile, headers);
	        URI uri = restTemplate.postForLocation("http://userprofileservice:8080/userprofilemgmt/userprofiles", request, UserProfile.class);
	        //logger.info("Location : "+uri.toASCIIString());
       
	    }
	   
	
}