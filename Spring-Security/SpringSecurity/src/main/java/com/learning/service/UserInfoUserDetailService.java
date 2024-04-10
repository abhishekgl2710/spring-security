package com.learning.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.learning.entity.UserInfo;
import com.learning.repository.UserInfoRepository;

/**
 * This Class will interact with DB and fetch the user detail service for
 * authentication
 * 
 * @author Abhishek
 *
 */
@Component
public class UserInfoUserDetailService implements UserDetailsService {

	@Autowired
	private UserInfoRepository userInfoRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * This method will fetch Data from DB and map those values to User Details
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<UserInfo> userInfo = userInfoRepository.findByName(username);
		return userInfo.map(UserInfoUserDetails::new)
				.orElseThrow(() -> new UsernameNotFoundException("user not found"));
	}

	/**
	 * This method will insert user details to DB
	 * 
	 * @param userInfo
	 * @return String
	 */
	public String addUser(UserInfo userInfo) {
		userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
		UserInfo save = userInfoRepository.save(userInfo);
		return "User added to System... ";
		
	}

}
