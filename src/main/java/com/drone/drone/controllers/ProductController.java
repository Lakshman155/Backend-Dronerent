package com.drone.drone.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.drone.drone.models.Product;
import com.drone.drone.repos.ProductRepo;

//@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/products")
public class ProductController {

	
		
		@Autowired
		private ProductRepo productrepository; 
		
		@GetMapping("/getProducts")
		public List<Product> getProducts(){
			
			List <Product> arr=productrepository.findAll();
			
			return arr;
			
		}
		
		@PostMapping("/addProduct")
		public ResponseEntity<Product> addProduct(@RequestBody Product product){
			
			System.out.println("Added the user called");
			product.setId(0);
			
			return new ResponseEntity<> (productrepository.save(product),HttpStatus.CREATED);
			
			
			
			
		}
		
		
		
		
	

}
