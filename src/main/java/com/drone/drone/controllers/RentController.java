package com.drone.drone.controllers;
import java.time.LocalDate;
import java.util.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.drone.drone.dto.RentDates;
import com.drone.drone.models.Product;
import com.drone.drone.models.Rentproducts;
import com.drone.drone.models.Users;
import com.drone.drone.repos.ProductRepo;
import com.drone.drone.repos.RentRepo;
import com.drone.drone.repos.UserRepo;
import com.drone.drone.services.Customservice;
import com.drone.drone.services.UsersService;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
//@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/rent")





public class RentController {
	@Autowired 
	ProductRepo prodrepo;
	@Autowired
	UserRepo userrepo;
	@Autowired
	Customservice customservice;

    @Autowired

    private RentRepo rentRepository;

    // Get all rent records
    @GetMapping("/getallrentals")
    public List<Rentproducts> getAllRents() {
        return rentRepository.findAll();
    }

    // Get a rent record by ID
    @GetMapping("/{id}")
    public Rentproducts getRentById(@PathVariable int id) {
        return rentRepository.findById(id).orElse(null);
    }

    // Create a new rent record
    
    @PostMapping("/addrentals/{pid}")
    public ResponseEntity<Rentproducts> createRent(@PathVariable int pid,@RequestBody RentDates rentdates) {
        // Find the product by ID
       Optional<Product> productOptional = prodrepo.findById(pid);

        if (!productOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        
        Users user=customservice.processUserDetails();
        
        Product product = productOptional.get();

        Rentproducts rentedProduct = new Rentproducts();
        System.out.println("called the addmethod");
        rentedProduct.setProduct(product);
        
        rentedProduct.setReturnDate(rentdates.getReturnDate());
        rentedProduct.setRentDate(rentdates.getRentDate());
        
        rentedProduct.setUser(user);
        Rentproducts savedRentedProduct = rentRepository.save(rentedProduct);
        
        return ResponseEntity.ok(null);
    }

    // Update an existing rent record
    @PutMapping("/{id}")
    public Rentproducts updateRent(@PathVariable int id, @RequestBody Rentproducts rentDetails) {
        return rentRepository.findById(id).map(rent -> {
            rent.setRentDate(rentDetails.getRentDate());
            rent.setReturnDate(rentDetails.getReturnDate());
            rent.setProduct(rentDetails.getProduct());
//            rent.setUser(rentDetails.getUser());
            return rentRepository.save(rent);
        }).orElseGet(() -> {
            rentDetails.setId(id);
            return rentRepository.save(rentDetails);
        });
    }

    // Delete a rent record
    @DeleteMapping("/{id}")
    public void deleteRent(@PathVariable int id) {
        rentRepository.deleteById(id);
    }
}
