package com.tistory.tazz009;

import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.tistory.tazz009.rest.service.ApiResult;
import com.tistory.tazz009.rest.service.LoginService;
import com.tistory.tazz009.rest.service.UserVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"servlet-context.xml"})
@WebAppConfiguration
public class LoginControllerTest {

	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	private MockMvc mockMvc;
	
//	@Autowired
	private LoginService service;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		service = mock(LoginService.class);
	}

	@Test
	public void loginTest() throws Exception {
		mockMvc.perform(get("/login"))
		 		.andExpect(status().isOk())
		 		.andExpect(forwardedUrl("/WEB-INF/views/home.jsp"));
	}
	
	@Test
	public void loginActionTest() throws Exception {
		UserVO user = new UserVO();
		user.setId("tazz");
		user.setPwd("1234");
		
		ApiResult result = new ApiResult();
		result.setStatus("SUCCESS");
		result.setData(user);
		result.setMsg("한글테스트");
		
//		when(service.loginAction(user)).thenReturn(result); 
		
		MvcResult andReturn = mockMvc.perform(post("/loginAction").param("id", "tazz").param("pwd", "1234"))
				.andExpect(status().isOk()).andReturn();
		
//		verify(service, times(0)).loginAction(user);
//		verifyZeroInteractions(service);
		System.out.println("***" + andReturn.getResponse());
	}
}
