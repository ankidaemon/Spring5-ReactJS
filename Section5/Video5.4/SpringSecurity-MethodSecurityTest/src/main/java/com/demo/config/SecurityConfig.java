package com.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
/**
 * @author ankidaemon
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
//@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan(basePackages = "com.demo.config")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}
	
	private AuthenticationProvider authenticationProvider() {
		// TODO Auto-generated method stub
		DaoAuthenticationProvider authProvider=new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		return authProvider;
	}

	@Bean
	public UserDetailsService userDetailsService() {
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(User.withUsername("chief").password("password").roles("CHIEF").build());
		manager.createUser(User.withUsername("agent").password("userpassword").roles("AGENT").build());
		return manager;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
			//	.regexMatchers("/chief/.*").hasRole("CHIEF")
			//	.regexMatchers("/agent/.*").access("hasRole('AGENT')")
				.anyRequest().authenticated()
				.and().httpBasic()
				.and().requiresChannel().anyRequest().requiresInsecure();

		http.formLogin().loginPage("/login").permitAll();
		
		http.exceptionHandling().accessDeniedPage("/accessDenied");

		http
		.logout()                                             
			.logoutUrl("/customlogout")                                           
			.logoutSuccessUrl("/")                              
			.logoutSuccessHandler(new CustomLogoutSuccessHandler())                              
			.invalidateHttpSession(true) //true by default                              
			.addLogoutHandler(new SecurityContextLogoutHandler())                                      
			.deleteCookies("JSESSIONID");    
	}
	
}
