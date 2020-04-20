package com.amdocs.userprofile.controller;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.amdocs.userprofile.model.UserProfile;
import com.amdocs.userprofile.repository.UserProfileRepository;
import com.amdocs.userprofile.service.UserProfileService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.amdocs.userprofile.exception.ResourceNotFoundException;;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserProfileRestControllerTest {
	
	private static final Logger logger = LoggerFactory.getLogger(UserProfileRestControllerTest.class);
	
	//private MockMvc mockMvc;
	
	//@Autowired
    //private WebApplicationContext wac;

	//@Before
	//public void setup() {
    //    this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

	//}
	private static final ObjectMapper om = new ObjectMapper();
	
	@Autowired
	private MockMvc mockMvc;
	
	//ObjectMapper mapper=new ObjectMapper();

    @MockBean
    private UserProfileService userProfileService;
    
    @MockBean
    private UserProfileRepository userProfileMockRepository;

    @InjectMocks
    private UserProfileRestController userProfileRestController;
    
   /* @Before
    public void init() {
    	//MockitoAnnotations.initMocks(this);
        UserProfile userProfile = new UserProfile(1L, "Viman Nagar,Pune", "9987765456");
        when(userProfileMockRepository.findById(1L)).thenReturn(Optional.of(userProfile));
    }*/

    @Before
    public void setUp() throws Exception{
    	
        MockitoAnnotations.initMocks(this);
       // UserProfileRestController controller = new UserProfileRestController(userProfileService);
       // mockMvc = MockMvcBuilders
       //         .standaloneSetup(userProfileRestController)               
       //         .build();
    }
    
    @Test
    public void verifyCreateUser() throws Exception {
    	
    	UserProfile newUserProfile = new UserProfile(1L, "Viman Nagar,Pune", "9987736354");
    	
       // when(userProfileMockRepository.save(any(UserProfile.class))).thenReturn(newUserProfile);
        when(userProfileService.saveUserProfile(any(UserProfile.class))).thenReturn(newUserProfile);
        
    	
    	mockMvc.perform(post("/userprofilemgmt/userprofiles")
                .content(om.writeValueAsString(newUserProfile))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                /*.andDo(print())*/
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.address", is("Viman Nagar,Pune")))
                .andExpect(jsonPath("$.phoneNumber", is("9987736354")));
    	        
    	verify(userProfileService, times(1)).saveUserProfile(any(UserProfile.class));
    	
    }
    @Test
    public void verifyUpdateUserForCorrectId() throws Exception {
    	
    	UserProfile newUserProfile = new UserProfile(1L, "Bibwewadi,Pune", "9987736354");
    	
       // when(userProfileMockRepository.save(any(UserProfile.class))).thenReturn(newUserProfile);
        when(userProfileService.getUserProfile(1L)).thenReturn(newUserProfile);
        when(userProfileService.updateUserProfile(any(UserProfile.class))).thenReturn(newUserProfile);
        
    	
    	mockMvc.perform(put("/userprofilemgmt/userprofiles/1")
                .content(om.writeValueAsString(newUserProfile))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                /*.andDo(print())*/
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.address", is("Bibwewadi,Pune")))
                .andExpect(jsonPath("$.phoneNumber", is("9987736354")));
    	        
    	verify(userProfileService, times(1)).getUserProfile(1L);
    	verify(userProfileService, times(1)).updateUserProfile(any(UserProfile.class));
    	
    }
    @Test
    public void verifyUpdateUserForWrongId() throws Exception {
    	
    	UserProfile newUserProfile = new UserProfile(1L, "Bibwewadi,Pune", "9987736354");
    	
       // when(userProfileMockRepository.save(any(UserProfile.class))).thenReturn(newUserProfile);
        
        when(userProfileService.getUserProfile(2L)).thenThrow(ResourceNotFoundException.class);
        
    	
    	mockMvc.perform(put("/userprofilemgmt/userprofiles/2")
                .content(om.writeValueAsString(newUserProfile))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                /*.andDo(print())*/
                .andExpect(status().isNotFound());
               
    	        
    	verify(userProfileService, times(1)).getUserProfile(2L);
    	
    }
    
    @Test
    public void verifyDeleteUser() throws Exception {
    	
    	UserProfile newUserProfile = new UserProfile(1L, "Bibwewadi,Pune", "9987736354");
    	
       // when(userProfileMockRepository.save(any(UserProfile.class))).thenReturn(newUserProfile);
        
    	when(userProfileService.getUserProfile(1L)).thenReturn(newUserProfile);    	
        Mockito.doNothing().when(userProfileService).deleteUserProfile(1L);
        
        
    	
    	mockMvc.perform(MockMvcRequestBuilders.delete("/userprofilemgmt/userprofiles/1")
               // .content(om.writeValueAsString(newUserProfile))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                /*.andDo(print())*/
                .andExpect(status().isNoContent());
               
    	        
    	verify(userProfileService, times(1)).getUserProfile(1L);
    	verify(userProfileService, times(1)).deleteUserProfile(1L);
    	
    }
    
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    

}
