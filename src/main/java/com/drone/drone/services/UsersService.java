package com.drone.drone.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.drone.drone.repos.UserRepo;

public class UsersService implements UserDetailsService{
	
	@Autowired
	private MyUserDetailsService usedetailsservice;
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		
		System.out.println("called the authentication for user"+username);
		
		return null;
		
		
	}

	public UsersService() {
		
		
		
		// TODO Auto-generated constructor stub
	}

}
