package com.drone.drone.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import com.drone.drone.models.Rentproducts;
import com.drone.drone.repos.RentRepo;
import java.util.List;
import org.springframework.security.core.context.SecurityContextHolder;
//@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/rent")





public class RentController {

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
    @PostMapping("/addrentals")
    public Rentproducts createRent(@PathVariable int pid) {
    	
    	
    	Rentproducts rent=new Rentproducts();
    	
        return rentRepository.save(rent);
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
