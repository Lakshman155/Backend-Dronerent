package com.drone.drone.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.drone.drone.models.Product;

public interface ProductRepo extends JpaRepository<Product,Integer> {
	
	

}
