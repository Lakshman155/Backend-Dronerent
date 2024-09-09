package com.drone.drone.dto;

import java.util.Date;

public class RentDates {
	 private Date rentDate;
	    private Date returnDate;

	public Date getRentDate() {
			return rentDate;
		}

		public void setRentDate(Date rentDate) {
			this.rentDate = rentDate;
		}

		public Date getReturnDate() {
			return returnDate;
		}

		public RentDates(Date rentDate, Date returnDate) {
			super();
			this.rentDate = rentDate;
			this.returnDate = returnDate;
		}

		public void setReturnDate(Date returnDate) {
			this.returnDate = returnDate;
		}

	public RentDates() {
		// TODO Auto-generated constructor stub
	}

}
