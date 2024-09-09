package com.drone.drone.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.drone.drone.models.Users;
import com.drone.drone.repos.UserRepo;
@Service
public class Customservice {
	@Autowired
	UserRepo userrepo;

	public Customservice() {
		// TODO Auto-generated constructor stub
	}

	
	

	
	
	
	public Users processUserDetails() {
	    // Get the authentication object from the security context
	  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    
	    if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
	        // Extract UserDetails (which could be CustomUserDetails)
	        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
	        
	        // Use userDetails for intermediate processing
	        
	        Users user=userrepo.findByEmail(userDetails.getUsername());
	        System.out.println("Username: " + userDetails.getUsername()+" "+user.getId());
	        return user;
	        // Further processing...
	    } else {
	        // Handle the case where authentication is null or UserDetails are not available
	        System.out.println("No user details found");
	        return null;
	    }
	    
	}
}
