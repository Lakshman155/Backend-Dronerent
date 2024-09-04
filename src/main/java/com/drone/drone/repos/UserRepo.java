package com.drone.drone.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.drone.drone.models.Users;

public interface UserRepo extends JpaRepository<Users, Integer>{
	
	Users findByEmail(String email);

}
