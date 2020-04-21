package com.amdocs.userprofile.controller;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.amdocs.userprofile.model.UserProfile;
import com.amdocs.userprofile.repository.UserProfileRepository;
import com.amdocs.userprofile.service.UserProfileService;
import com.fasterxml.jackson.databind.ObjectMapper;;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserProfileRestControllerTest {



	private static final ObjectMapper om = new ObjectMapper();

	@Autowired
	private MockMvc mockMvc;



	@MockBean
	private UserProfileService userProfileService;

	@MockBean
	private UserProfileRepository userProfileMockRepository;

	@InjectMocks
	private UserProfileRestController userProfileRestController;



	@Before
	public void setUp() throws Exception{

		MockitoAnnotations.initMocks(this);

	}

	@Test
	public void verifyCreateUser() throws Exception {

		UserProfile newUserProfile = new UserProfile(1L, "Viman Nagar,Pune", "9987736354");


		when(userProfileService.saveUserProfile(any(UserProfile.class))).thenReturn(newUserProfile);


		mockMvc.perform(post("/userprofilemgmt/userprofiles")
				.content(om.writeValueAsString(newUserProfile))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))                
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id", is(1)))
		.andExpect(jsonPath("$.address", is("Viman Nagar,Pune")))
		.andExpect(jsonPath("$.phoneNumber", is("9987736354")));

		verify(userProfileService, times(1)).saveUserProfile(any(UserProfile.class));

	}


	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}



}
