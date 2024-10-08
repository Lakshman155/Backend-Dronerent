package com.drone.drone.models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import java.util.Date;

	
		
		
		
		

	@Entity
	public class Rentproducts {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;
	    private Date rentDate;
	    private Date returnDate;
	    

	    @ManyToOne
		@JoinColumn(name = "product_id", nullable = false)
	    private Product product;

	    @ManyToOne
	    @JoinColumn(name = "user_id", nullable = false)
	    private Users user;

	    public Users getUser() {
			return user;
		}

		public void setUser(Users user) {
			this.user = user;
		}

		// Getters and setters
	    public int getId() {
	        return id;
	    }
	    
	    public void setId(int id) {
	        this.id = id;
	    }

	    public Date getRentDate() {
	        return rentDate;
	    }

	    public void setRentDate(Date rentDate) {
	        this.rentDate = rentDate;
	    }

	    public Date getReturnDate() {
	        return returnDate;
	    }

	    public void setReturnDate(Date returnDate) {
	        this.returnDate = returnDate;
	    }

	    public Product getProduct() {
	        return this.product;
	    }

	    public void setProduct(Product product) {
	        this.product = product;
	    	
	    }

		public Rentproducts(Date rentDate, Date returnDate,Product prodduct,Users user) {
			super();
			this.rentDate = rentDate;
			this.returnDate = returnDate;
			this.product=product;
			this.user=user;
		}
		
		

//		@Override
//		public String toString() {
//			return "Rentproducts [id=" + id + ", rentDate=" + rentDate + ", returnDate=" + returnDate + ", product="
//					+ product + "]";
//		}

		public Rentproducts() {
			
		}
		

	}

	



