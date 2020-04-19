package com.amdocs.authorization.service;

import com.amdocs.authorization.model.UserProfile;
import java.util.List;

public interface AuthorizationService {
	
	//public List<Statistic> retrieveStatistics();	
	public void deleteUserProfile(UserProfile userprofile);
	//public Statistic getStatistic(String name);
	//public void saveStatistic(Statistic statistic);
	public void updateUserProfile(UserProfile userprofile);
	 
	 

}
