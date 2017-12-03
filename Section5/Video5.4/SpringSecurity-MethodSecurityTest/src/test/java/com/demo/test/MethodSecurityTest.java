package com.demo.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.demo.config.WebConfig;
import com.demo.to.UserTo;
import com.demo.web.HomeController;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(classes = WebConfig.class)
@WebAppConfiguration
public class MethodSecurityTest	 {
	@Autowired
	public HomeController homeController;
	
	@Test(expected = AuthenticationCredentialsNotFoundException.class)
	public void expectedTest() {
		homeController.updateChiefPage();
	}
	
	@Test
	@WithMockUser(roles={"CHIEF"})
	public void withMockUserOnlyRoleTest() {
		homeController.updateChiefPage();
	}
	
	@Test
	@WithMockUser(username = "agent",authorities={"AGENT"})
	//@WithMockUser(username = "agent",roles={"ROLE_AGENT"})
	public void withMockUserTest() {
		UserTo userTo = new UserTo();
		userTo.setUserName("agent");
		homeController.withUserNameTest(userTo);
	}
	//Below Test will fail as no User with username-xyz exists with bean userDetailsService
	@Test
	@WithUserDetails(value="xyz", userDetailsServiceBeanName="userDetailsService")
	public void withUserDetailsTest() {
		homeController.withUserDetailTest();
	}
} 
