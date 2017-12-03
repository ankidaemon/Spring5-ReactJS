package com.demo.test;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.logout;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.servlet.Filter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.demo.config.ReactAndRestApp;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = { ReactAndRestApp.class })
@WebAppConfiguration
public class RequestSecurityTest {
	@Autowired
	private WebApplicationContext context;

	@Autowired
	private Filter springSecurityFilterChain;

	private MockMvc mvc;

	@Before
	public void setup() {
		mvc = MockMvcBuilders.webAppContextSetup(context).addFilters(springSecurityFilterChain).build();
	}

	@Test
	public void performLoginDefault() throws Exception {
		mvc.perform(formLogin()).andExpect((redirectedUrl("/login?error")));
	}

	@Test
	public void performLoginWithUserPassword() throws Exception {
		mvc.perform(formLogin("/login").user("chief").password("chiefPassword")).andExpect((redirectedUrl("/")));
	}

	@Test
	public void performLoginWithParameterSet() throws Exception {
		mvc.perform(formLogin("/login").user("user", "admin").password("pass", "adminPassword"))
				.andExpect((redirectedUrl("/login?error")));
	}

	@Test
	public void performLogout() throws Exception {
		mvc.perform(logout("/logout")).andExpect((redirectedUrl("/login?logout")));
	}
		
	@Test
    public void getHome() throws Exception {
    	mvc.perform(get("/").with(httpBasic("admin","adminPassword")))
    	.andExpect((status().isOk()));
    }   
}
