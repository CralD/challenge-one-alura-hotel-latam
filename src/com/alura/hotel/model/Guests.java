package com.alura.hotel.model;

import java.sql.Date;
import java.time.LocalDate;

public class Guests {

	private Integer id;
	private String firstName;
	private String lastName;
	private Date birthDate;
	private String nationality;
	private String phone;
	private Integer reserveId;

	public Guests(String name, String lastName, Date birthDate, String nationality, String phone, Integer reserveId) {
		super();
		this.firstName = name;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.nationality = nationality;
		this.phone = phone;
		this.reserveId = reserveId;
	}

	public Guests(Integer id, String name, String lastName, Date birthDate, String nationality, String phone,
			Integer reserveId) {
		super();
		this.id = id;
		this.firstName = name;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.nationality = nationality;
		this.phone = phone;
		this.reserveId = reserveId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return firstName;
	}

	public void setName(String name) {
		this.firstName = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getReserveId() {
		return reserveId;
	}

	public void setReserveId(Integer reserveId) {
		this.reserveId = reserveId;
	}

}
