package com.drone.drone.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.drone.drone.models.Rentproducts;
//import com.drone.drone.models.User;

public interface RentRepo extends JpaRepository<Rentproducts, Integer>{
	
	

}
