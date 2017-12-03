package com.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.demo.model.Admin;
import com.demo.repository.AdminRepository;

@Component
public class AdminUserDetailsService implements UserDetailsService {

	private final AdminRepository repository;

	@Autowired
	public AdminUserDetailsService(AdminRepository repository) {
		this.repository = repository;
	}

	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		Admin admin = this.repository.findByUserName(name);
		return new User(admin.getUserName(), admin.getPassword(), AuthorityUtils.createAuthorityList(admin.getRoles()));
	}

	/*
	 * public List<GrantedAuthority> getAuthorities() { List<GrantedAuthority>
	 * authList = new ArrayList<GrantedAuthority>(); authList.add(new
	 * SimpleGrantedAuthority("ROLE_USER")); authList.add(new
	 * SimpleGrantedAuthority("ROLE_ADMIN")); return authList; }
	 */

}