package com.drone.drone.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.drone.drone.models.UserPrincipal;
import com.drone.drone.models.Users;
import com.drone.drone.repos.UserRepo;
@Service
public class MyUserDetailsService implements UserDetailsService{
@Autowired
private UserRepo userrepo;


	public MyUserDetailsService() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		Users user=userrepo.findByEmail(username);
		if (user==null) {
			throw new UsernameNotFoundException("User not found");
			
		}
		return new UserPrincipal(user);
	}

}
