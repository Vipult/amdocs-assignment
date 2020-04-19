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
import org.apache.commons.codec.binary.Base64;
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
	//Create resttemplate
	//RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());
	 
	//Override timeouts in request factory
	/*private SimpleClientHttpRequestFactory getClientHttpRequestFactory()
	{
		SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();		 
	    Proxy proxy= new Proxy(Proxy.Type.HTTP, new InetSocketAddress("10.0.75.1", 8085));
	    requestFactory.setProxy(proxy);
	   // requestFactory.setReadTimeout(30000);
	    //requestFactory.setConnectTimeout(30000);
	    return requestFactory;
		//HttpComponentsClientHttpRequestFactory clientRequestFactory = new HttpComponentsClientHttpRequestFactory();
		
		
		
	}
	
	private static HttpHeaders getHeaders(){
	        String plainCredentials="bill:abc123";
	        String base64Credentials = new String(Base64.encodeBase64(plainCredentials.getBytes()));
	         
	        HttpHeaders headers = new HttpHeaders();
	        headers.add("Authorization", "Basic " + base64Credentials);
	        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	        return headers;
	    }
		private final String endpointUrl="http://statisticservice:8080/statistics/";
		
		
		

	    @Override
	    public List<Statistic> findAllStatistics() {
	    	
	    	List<Statistic> statistics=null;	 
	       try{
	    	   logger.info("Before calling RestTemplate");
	    	   ResponseEntity<List<Statistic>> response = restTemplate.exchange(
	    			   "http://statisticservice:8080/statistics/",
	         		  HttpMethod.GET,
	         		  null,
	         		  new ParameterizedTypeReference<List<Statistic>>(){});
	         	statistics = response.getBody();
	         	logger.info("List Statistics ::"+statistics);
	         		return statistics;
	         		
	    	   URI endPoint= new URI(endpointUrl);		
	    	   UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(endpointUrl);
		    HttpHeaders headers = new HttpHeaders();
		    	headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		    	headers.setContentType(MediaType.APPLICATION_JSON);
		    	HttpEntity <String> request = new HttpEntity<String>(headers);
		    	logger.info("Request "+request);
		    	logger.info("Before calling exchange method");
		    	logger.info("URI "+builder.build().encode().toUri());
		    	ResponseEntity<List> response = restTemplate.exchange(endpointUrl+"/statistics", HttpMethod.GET, request, List.class);
		    	logger.info("after calling exchange method");
		        List<LinkedHashMap<String, Object>> statisticsMap = (List<LinkedHashMap<String, Object>>)response.getBody();
		        if(statisticsMap!=null){
		            for(LinkedHashMap<String, Object> map : statisticsMap){
		                logger.info("Statistic : name="+map.get("name")+", count="+map.get("count"));
		            }
		        }else{
		            logger.info("No statistics exist----------");
		        }
		         
	       }catch (ResourceAccessException e) {
	    	   logger.error("ResourceAccessException",e);
				//log.error("TmeOut error", e);
				//throw new TimeOutException(e.getMessage(), e);
			}catch (HttpClientErrorException e) {
				logger.trace("HttpClientErrorException",e);
				//log.error("Communication error", e);
				//throw new DataInvalidException("Unknown Data");
			}catch (RestClientException e) {
				logger.error("RestClientException",e);
				//log.error("Communication errors", e);
				//throw new DataAccessFailure(e.getMessage(), e);
			}catch (URISyntaxException e) {
				logger.error("URISyntaxException",e);
			}
	       return statistics;       
	        
	    }
	    
	        public void deleteAllStatistics() {
	       // HttpHeaders headers = new HttpHeaders();
	      //  headers.setContentType(MediaType.APPLICATION_JSON);
	        logger.info("Testing all delete Users API in Connector----------");	        
	      //  HttpEntity<String> request = new HttpEntity<String>(headers);
	        logger.info("Before calling delete method");
	        //restTemplate.exchange(endpointUrl+"/remove/", HttpMethod.DELETE, request, Statistic.class);
	        restTemplate.delete("http://statisticservice:8080/statistics/");
	    }

	   @Override
	    public Statistic findStatisticByName(String name) {
	        String findStatisticByNameSql = "SELECT * FROM STATISTIC WHERE name=? order by name";
	        Statistic statistic = (Statistic) jdbcTemplate.queryForObject(findStatisticByNameSql, new Object[]{name},new BeanPropertyRowMapper(Statistic.class));
	        return statistic;
	    }
	   
	   @Override
	    public Statistic findStatisticByName(String name) {
		   Statistic statistic=null;	 
	       try{
	    	  // URI endPoint= new URI(endpointUrl);		
	    	    UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(endpointUrl);
		    	HttpHeaders headers = new HttpHeaders();
		    	headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		    	headers.setContentType(MediaType.APPLICATION_JSON);
		    	HttpEntity <String> request = new HttpEntity<String>(headers);
		    	logger.info("Request "+request);
		    	logger.info("Before calling exchange method for findStatisticByName");
		    	logger.info("URI "+builder.build().encode().toUri());
		    	
		    	
		    	Map < String, String > params = new HashMap < String, String > ();
		        params.put("name", name);
		    	
		    	//statistic = restTemplate.getForObject(endpointUrl+"/statistic/{name}", Statistic.class,params);
	    	    
		    	ResponseEntity<Statistic> response = restTemplate.exchange("http://statisticservice:8080/statistics/statistic/{name}", HttpMethod.GET, null,Statistic.class,name);
		    	statistic =response.getBody();
		    	logger.info("Statistic with name "+statistic.getName()+" and count "+statistic.getCount());
		       
		         
	       }catch (ResourceAccessException e) {
	    	   logger.error("ResourceAccessException",e);
				//log.error("TmeOut error", e);
				//throw new TimeOutException(e.getMessage(), e);
			}catch (HttpClientErrorException e) {
				logger.trace("HttpClientErrorException",e);
				//log.error("Communication error", e);
				//throw new DataInvalidException("Unknown Data");
			}catch (RestClientException e) {
				logger.error("RestClientException",e);
				//log.error("Communication errors", e);
				//throw new DataAccessFailure(e.getMessage(), e);
			}catch (URISyntaxException e) {
				logger.error("URISyntaxException",e);
			}
	       return statistic;       
	        
	    }*/
	   
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
	   
	  /* @Override
	    public void updateStatistic(Statistic statistic){
		   logger.info("Saving a new statistic in connector");
		   logger.info("statistic with name "+statistic.getName()+" and count "+statistic.getCount());
		   
			HttpHeaders headers = new HttpHeaders();
	    	headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    	headers.setContentType(MediaType.APPLICATION_JSON);
		   String name=statistic.getName();
		   logger.info("name is "+name);
		   HttpEntity<Object> request = new HttpEntity<Object>(statistic, headers);
	       // URI uri = restTemplate.postForLocation("http://statisticservice:8080/statistics/", request, Statistic.class);
	       // restTemplate.put("http://statisticservice:8080/statistics/statistic/{name}",statistic);
		   ResponseEntity<Statistic> response = restTemplate.exchange("http://statisticservice:8080/statistics/statistic/{name}", HttpMethod.PUT, request, Statistic.class,name);
		   //restTemplate.exchange("http://statisticservice:8080/statistics/statistic/{name}", HttpMethod.GET, null,Statistic.class,name);
	        //System.out.println(response.getBody());
	    }
	  
	        
	    	
	    
	
	    @Override
	    public void deleteAllStatistics(){
	    	String deleteAllStatistics = "DELETE FROM STATISTIC";
	    	jdbcTemplate.update(deleteAllStatistics);
	    	
	    }
		
		@Override
	    public void saveStatistic(Statistic statistic){
			String updateSql = "INSERT INTO statistic(name,count)values(?,?)";
			String name=statistic.getName();
			int count=statistic.getCount();
	    	Object[] params = { name, count};
			int[] types = {Types.VARCHAR, Types.INTEGER};
			int rows = jdbcTemplate.update(updateSql, params, types);
        
	    }
		
		@Override
	    public void updateStatistic(Statistic statistic){
	    	String updateSql = "UPDATE Statistic SET count = ? WHERE name = ?";
			String name=statistic.getName();
			int count=statistic.getCount();
	    	Object[] params = { name, count};
			int[] types = {Types.VARCHAR, Types.INTEGER};
			int rows = jdbcTemplate.update(updateSql, params, types);
	    	
	    }*/

}