package com.drone.drone.models;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import jakarta.persistence.Entity;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Product {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	private String url;
	private float price;
	private String description;
	
	
	
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getUrl() {
		return url;
	}



	public void setUrl(String url) {
		this.url = url;
	}



	public float getPrice() {
		return price;
	}



	public void setPrice(float price) {
		this.price = price;
	}



	public String getDescription() {
		return description;
	}



	public void setDesc(String description) {
		this.description = description;
	}



	
	
	
	public Product(int id, String name, String url, float price, String description) {
		
		this.id = id;
		this.name = name;
		this.url = url;
		this.price = price;
		this.description = description;
	}
	
	protected Product() {
		
		
		
	}
	
	

	

}
