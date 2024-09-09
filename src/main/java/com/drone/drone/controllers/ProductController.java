package com.drone.drone.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
		
		@GetMapping("/getproducts")
		public List<Product> getProducts(){
			
			List <Product> arr=productrepository.findAll();
			
			return arr;
			
		}
		
		@PostMapping("/addproduct")
		public String addProduct(@RequestBody Product product){
			
			System.out.println("Added the product called");
//			product.setId(0);
			productrepository.save(product);
			
			return "Added the Product :";
			
			
			
			
		}
		 @PutMapping("/{id}")
		    public ResponseEntity<Product> updatePrice(@PathVariable int id, @RequestBody float price) {
//		        Optional<Product> updatedProduct = productService.updatePrice(id, price);
			 Optional<Product>  p=productrepository.findById(id);
			 
			 
			 if (p==null) {
				 return new ResponseEntity("Not found",HttpStatus.BAD_REQUEST);
			 }
			 Product product=p.get();
			 product.setPrice(price);
			 
			 return new ResponseEntity<>(product,HttpStatus.OK);
		    }
		 @DeleteMapping("/{id}")
		    public ResponseEntity<Void> deleteProduct(@PathVariable int id) {
			 if (productrepository.existsById(id)) {
		            productrepository.deleteById(id);
		            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		        } 
			 
			 else {
				 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		            
		        } 
		 
		    }
		
		
		
	

}
