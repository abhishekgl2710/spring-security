package com.learning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.learning.entity.UserInfo;
import com.learning.service.UserInfoUserDetailService;

/**
 * This is the Security Controller
 * 
 * @author Abhishek
 * @since 1.0.0
 *
 */
@RestController
public class SecurityController {

	@Autowired
	private UserInfoUserDetailService service;

	/**
	 * This method can be accessed by only The admin role
	 * 
	 * @return String
	 */
	@GetMapping("/getAdmin")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String getAdmin() {
		return "Welcome to Security Project Admin Console";
	}

	/**
	 * This method can be accessed by only The user role
	 * 
	 * @return String
	 */
	@GetMapping("/getUser")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public String getUser() {
		return "Welcome to Security Project User Console";

	}

	/**
	 * This is method will provde welcome message
	 * 
	 * @return String
	 */
	@GetMapping("/welcome")
	public String welcomePage() {
		return "Welcome to Spring Security Gate Way";
	}

	/**
	 * This method used to create new user details in DB
	 * 
	 * @param userInfo
	 * @return String
	 */
	@PostMapping("/new")
	public String addNewUser(@RequestBody UserInfo userInfo) {
		return service.addUser(userInfo);
	}
}
