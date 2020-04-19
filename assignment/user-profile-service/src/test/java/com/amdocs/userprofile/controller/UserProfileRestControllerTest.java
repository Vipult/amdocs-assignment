package com.amdocs.userprofile.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amdocs.userprofile.Application;
import com.amdocs.userprofile.controller.UserProfileRestController;
import com.amdocs.userprofile.model.UserProfile;
import com.amdocs.userprofile.repository.UserProfileRepository;
import com.amdocs.userprofile.service.UserProfileService;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.http.HttpHeaders;
import static org.hamcrest.Matchers.is;

import java.util.Optional;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;

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
    public void it_should_return_created_user() throws Exception {
    	
    	UserProfile newUserProfile = new UserProfile(1L, "Viman Nagar,Pune", "9987736354");
    	
        when(userProfileMockRepository.save(any(UserProfile.class))).thenReturn(newUserProfile);
        
    	
    	mockMvc.perform(post("/userprofiles")
                .content(om.writeValueAsString(newUserProfile))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                /*.andDo(print())*/
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.address", is("Viman Nagar,Pune")))
                .andExpect(jsonPath("$.phoneNumber", is("9987736354")));
    	        
    	verify(userProfileMockRepository, times(1)).save(any(UserProfile.class));
    	
    }
    
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    

}
