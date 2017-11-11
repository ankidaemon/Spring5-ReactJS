package com.demo.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author ankidaemon
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	BasicDataSource dataSource;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery("select username,password,enabled from t_credential where username=?")
				.authoritiesByUsernameQuery("select username,authority from t_credential_roles where username=?");
	}

	/*@Bean
	public UserDetailsService userDetailsService() {
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(User.withUsername("ankidaemon").password("password").roles("USER").build());
		manager.createUser(User.withUsername("test").password("test").roles("USER").build());
		return manager;
	}*/

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// Access control in Spring security
		http
		.authorizeRequests()
			.antMatchers("/create","/updatePage","/delete/*").access("hasRole('ADMIN')")
			.antMatchers("/all").hasAnyRole("LOCAL","ADMIN")
		.anyRequest().authenticated()
		.and()
		.requiresChannel().anyRequest().requiresInsecure();

		// Custom login Page
		http.formLogin()
				// .loginPage("/login")
				.permitAll().and().httpBasic();
		
		http.logout();                                                                
			//.logoutUrl("/customlogout")                                                 
			//.logoutSuccessUrl("/customSuccessPage")
		
		http.csrf().disable();
		
		//Access Denied Page
		http.exceptionHandling().accessDeniedPage("/accessDenied");

	}
}