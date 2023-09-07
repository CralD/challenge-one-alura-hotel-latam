package com.alura.hotel.controller;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;
import com.alura.hotel.dao.GuestsDao;
import com.alura.hotel.factory.ConnectionFactory;
import com.alura.hotel.model.Guests;

public class GuestsController {
	private GuestsDao guestDao;

	public GuestsController() {
		Connection connection = new ConnectionFactory().recuperaConexion();
		this.guestDao = new GuestsDao(connection);

	}

	public void save(Guests guest) {
		this.guestDao.save(guest);

	}

	public void delete(Integer id) {
		this.guestDao.delete(id);
	}

	public void update(Integer id, String name, String lastName, Date birthDate, String nationality, String phone,
			Integer reserveId) {
		this.guestDao.update(id, name, lastName, birthDate, nationality, phone, reserveId);

	}

	public List<Guests> guestList() {
		return this.guestDao.guestList();

	}

	public List<Guests> guestListById(String id) {
		return this.guestDao.guestListById(id);

	}

	public List<Guests> guestListyByLastName(String lastName) {
		return this.guestDao.guestListByLastname(lastName);
	}
}
