package com.amdocs.authorization.service.impl;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.amdocs.authorization.events.source.SimpleSourceBean;
import com.amdocs.authorization.model.UserProfile;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.amdocs.authorization.service.AuthorizationService;
//import com.boxchain.restproxy.repository.RestProxyConnector;
//import com.boxchain.restproxy.service.RestProxyService;
//import com.boxchain.restproxy.model.Statistic;

@Service
public class AuthorizationServiceImpl implements AuthorizationService{
	
	private static final Logger logger = LoggerFactory.getLogger(AuthorizationServiceImpl.class);
	
	//@Autowired
	// private RestProxyConnector restProxyConnector;
	
	@Autowired
    SimpleSourceBean simpleSourceBean;
	
	 @Override 
	  public void updateUserProfile(UserProfile userProfile) {
		 logger.info("Sending an UPDATE event");
		 simpleSourceBean.publishUserProfileChange("UPDATE",userProfile.getId());

	 }

	 
	 @Override	  
	 public void deleteUserProfile(UserProfile userProfile){
		logger.info("Sending a DELETE event");
 simpleSourceBean.publishUserProfileChange("DELETE",userProfile.getId());
	 }
	 
	/* @Override
	 public void saveStatistic(Statistic statistic){
		 logger.info("Saving new Statistic in service");
		 restProxyConnector.saveStatistic(statistic);
  simpleSourceBean.publishStatsChange(statistic.getName(), statistic.getCount());
 }*/
	 
	 

}
