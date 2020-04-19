package com.amdocs.authorization.repository;

import java.util.List;

import com.amdocs.authorization.model.UserProfile;

public interface AuthorizationRestConnector {
	
	/*public List<Statistic> findAllStatistics();	  
	public void deleteAllStatistics();
	public Statistic findStatisticByName(String name);*/
	public void saveUserProfile(UserProfile userProfile);
	/*public void updateStatistic(Statistic statistic);
	public void deleteAllStatistics();
	public void saveStatistic(Statistic statistic);
	public void updateStatistic(Statistic statistic);*/
	

}
