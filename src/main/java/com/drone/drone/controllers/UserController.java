package com.drone.drone.controllers;



import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.drone.drone.dto.LoginUser;
import com.drone.drone.jwt.JwtUtils;
import com.drone.drone.models.Users;
import com.drone.drone.repos.UserRepo;

import jakarta.servlet.http.HttpServlet;
//@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/users")

public class UserController {
	@Autowired
	 UserRepo userrepo;
	@Autowired
	BCryptPasswordEncoder encoder;
	@Autowired 
	JwtUtils jwtutils;
	
	
	
	@GetMapping("/getusers")
	public List<Users> getusers() {
		
		System.out.println("here all products are called");
		
		
		return userrepo.findAll();
		
	}
	
	@GetMapping("/getuser")
	public String Login(@RequestBody LoginUser user) {
		System.out.print("called getuse method");
		
		
		user.setPassword(encoder.encode(user.getPassword()));
		
		
						
					
			Users found=userrepo.findByEmail(user.getEmail());
			if (found==null) {
				return "User not exists";
			}

			
					
				
				
				
			
			
			
			
			return user.getEmail()+"Found";
	}
	
	
//	@GetMapping("/user-info")
    public UserDetails getUserInfo(@AuthenticationPrincipal UserDetails userDetails) {
    	
    	
        return userDetails;
    }
	
	@PostMapping("/adduser")
	public ResponseEntity<String> addUser(@RequestBody Users users){
		
		
		
		System.out.println("Added the user called"+users.getPassword()+users.getEmail());
		users.setPassword(encoder.encode(users.getPassword()));
		users.setCnfpswd(encoder.encode(users.getCnfpswd()));
		
		
		
		
		Users temp=userrepo.findByEmail(users.getEmail());
		if (temp!=null) {
			return ResponseEntity.badRequest().body("User Already exists");
		}
		
		
		
		userrepo.save(users);
		return ResponseEntity.status(HttpStatus.CREATED).body("user registered successfully");
		
		
		
	}
	
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginUser user){
		
		String email=user.getEmail();
		String password=user.getPassword();
		
			user.setPassword(encoder.encode(password));
		
		
		
//		System.out.println("here all login is called");
		Users other=userrepo.findByEmail(email);
		
		if (other!=null) {
			
			String token=jwtutils.generateToken(user.getEmail());
			return ResponseEntity.ok(token);
			
		}
		
		
		
		
		
	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid password");
		
		
		
	}
	

}
