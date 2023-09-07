package com.alura.hotel.model;

import java.sql.Date;

public class Bookings {

	private Integer id;
	private Date entryDate;
	private Date departureDate;
	private String reserveValue;
	private String payMethod;

	public Bookings(Date entryDate, Date departureDate, String value, String payMethod) {
		super();
		this.entryDate = entryDate;
		this.departureDate = departureDate;
		this.reserveValue = value;
		this.payMethod = payMethod;
	}

	public Bookings(Integer id, Date entryDate, Date departureDate, String value, String payMethod) {
		super();
		this.id = id;
		this.entryDate = entryDate;
		this.departureDate = departureDate;
		this.reserveValue = value;
		this.payMethod = payMethod;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}

	public String getValue() {
		return reserveValue;
	}

	public void setValue(String value) {
		this.reserveValue = value;
	}

	public String getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}

}
