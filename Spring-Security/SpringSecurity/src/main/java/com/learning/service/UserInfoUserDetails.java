package com.learning.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.learning.entity.UserInfo;

/**
 * This class is the template t assign User Details methods
 * 
 * @author Abhishek
 *
 */
public class UserInfoUserDetails implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4061172718245157911L;
	private String name;
	private String password;
	private List<GrantedAuthority> authorties;

	public UserInfoUserDetails(UserInfo userinfo) {
		name = userinfo.getName();
		password = userinfo.getPassword();

		authorties = Arrays.stream(userinfo.getRoles().split(",")).map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorties;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
