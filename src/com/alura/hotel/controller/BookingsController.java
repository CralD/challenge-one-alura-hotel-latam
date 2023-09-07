package com.alura.hotel.controller;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import com.alura.hotel.dao.BookingsDao;
import com.alura.hotel.dao.GuestsDao;
import com.alura.hotel.factory.ConnectionFactory;
import com.alura.hotel.model.Bookings;

public class BookingsController {
	private BookingsDao bookingsDao;
	private GuestsDao guestDao;

	public BookingsController() {
		Connection connection = new ConnectionFactory().recuperaConexion();
		this.bookingsDao = new BookingsDao(connection);
		this.guestDao = new GuestsDao(connection);
	}

	public void save(Bookings bookings) {
		this.bookingsDao.save(bookings);

	}

	public void delete(Integer id) {
		this.guestDao.deleteByBookingsId(id);
		this.bookingsDao.delete(id);
	}

	public void update(Integer id, Date entryDate, Date departureDate, String value, String payMethod) {
		this.bookingsDao.update(id, entryDate, departureDate, value, payMethod);

	}

	public List<Bookings> bookingList() {
		return this.bookingsDao.bookingList();

	}

	public List<Bookings> bookingsListById(String id) {
		return this.bookingsDao.bookingsListById(id);

	}
}
