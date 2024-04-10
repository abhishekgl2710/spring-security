package com.learning.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.learning.service.UserInfoUserDetailService;

import org.springframework.security.core.userdetails.User;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SpringSecurityConfiguration {

	// Authorization
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.csrf(AbstractHttpConfigurer::disable)
	            .authorizeHttpRequests(auth ->
	                    auth.requestMatchers("/welcome","/new").permitAll()
	                            .requestMatchers("/getAdmin","/getUser")
	                            .authenticated()
	            )
	            .httpBasic(Customizer.withDefaults()).build();
	}

	// Authentication
	@Bean
	public UserDetailsService userDetailsService() {
		
//		// Below are the Hard coded methods but i want to load details from DB directly
//		// so creating the new Service to hand hower the values
//		UserDetails admin = User.withUsername("Abhi").password(encoder.encode("Abhi")).roles("ADMIN").build();
//		UserDetails user = User.withUsername("Sree").password(encoder.encode("Sree")).roles("USER").build();
//
//		return new InMemoryUserDetailsManager(admin,user);
		return new UserInfoUserDetailService();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationProvider authentiationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(userDetailsService());
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		return daoAuthenticationProvider;

	}
}
