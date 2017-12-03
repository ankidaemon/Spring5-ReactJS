package com.demo.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.demo.config.ReactAndRestApp;
import com.demo.web.HomeController;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {ReactAndRestApp.class})
@WebAppConfiguration
public class MethodSecurityTest	 {
	@Autowired
	public HomeController homeController;
	
	@Test(expected = AuthenticationCredentialsNotFoundException.class)
	public void expectedTest() {
		homeController.findAll();
	}
	
	//Below Test will fail as no User with username-xyz exists with bean userDetailsService
	@Test
	@WithUserDetails(value="xyz", userDetailsServiceBeanName="adminUserDetailsService")
	public void withUserDetailsTest() {
	}
	
	@Test
	@WithMockUser(username = "admin",authorities={"ADMIN"})
	//@WithMockUser(username = "admin",roles={"ROLE_ADMIN"})
	public void withMockUserTest() {
		homeController.delete(1);
	}
	
	@Test
	@WithMockUser(roles={"ADMIN"})
	public void withMockUserOnlyRoleTest() {
		homeController.findAll();
	}
	
} 

