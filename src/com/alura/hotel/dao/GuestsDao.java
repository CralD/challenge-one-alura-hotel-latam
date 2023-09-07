package com.alura.hotel.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.alura.hotel.model.Bookings;
import com.alura.hotel.model.Guests;

public class GuestsDao {

	private Connection connection;

	public GuestsDao(Connection connection) {
		this.connection = connection;
	}

	public void save(Guests guest) {
		try {
			String sql = "INSERT INTO guest(first_name,last_Name,birthdate,nationality,phone,reserve_id) VALUES(?,?,?,?,?,?)";
			try (PreparedStatement preparedStatement = connection.prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS)) {
				preparedStatement.setString(1, guest.getName());
				preparedStatement.setString(2, guest.getLastName());
				preparedStatement.setDate(3, guest.getBirthDate());
				preparedStatement.setString(4, guest.getNationality());
				preparedStatement.setString(5, guest.getPhone());
				preparedStatement.setInt(6, guest.getReserveId());
				preparedStatement.executeUpdate();
				try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
					while (resultSet.next()) {
						guest.setId(resultSet.getInt(1));
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void delete(Integer id) {
		try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM guest WHERE id = ?")) {
			preparedStatement.setInt(1, id);
			preparedStatement.execute();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void deleteByBookingsId(Integer id) {
		try (PreparedStatement preparedStatement = connection
				.prepareStatement("DELETE FROM guest WHERE reserve_id = ?")) {
			preparedStatement.setInt(1, id);
			preparedStatement.execute();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void update(Integer id, String name, String lastName, Date birthDate, String nationality, String phone,
			Integer reserveId) {
		try {
			String sql = "UPDATE guest SET first_name = ?, last_Name = ? , birthdate = ?, nationality = ?,phone = ?,reserve_id = ? WHERE id = ? ";
			try (PreparedStatement preparedStatement = connection.prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS)) {
				preparedStatement.setString(1, name);
				preparedStatement.setString(2, lastName);
				preparedStatement.setDate(3, birthDate);
				preparedStatement.setString(4, nationality);
				preparedStatement.setString(5, phone);
				preparedStatement.setInt(6, reserveId);
				preparedStatement.setInt(7, id);
				preparedStatement.execute();

			}
		} catch (SQLException e) {
			throw new RuntimeException(e);

		}

	}

	public List<Guests> guestList() {
		List<Guests> guests = new ArrayList<>();
		try {
			String sql = "SELECT id,first_name, last_Name, birthdate, nationality,phone,reserve_id  FROM guest";
			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				preparedStatement.execute();
				transformResultSetInBookings(guests, preparedStatement);
			}
			return guests;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Guests> guestListById(String criteria) {
		List<Guests> guest = new ArrayList<>();
		try {
			String sql = "SELECT id,first_name, last_Name, birthdate, nationality,phone,reserve_id  FROM guest WHERE id = ? or last_Name like ?   ";
			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				preparedStatement.setString(1, criteria);
				preparedStatement.setString(2, "%" + criteria + "%");
				preparedStatement.execute();
				transformResultSetInBookings(guest, preparedStatement);
			}
			return guest;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Guests> guestListByLastname(String lastName) {
		List<Guests> guest = new ArrayList<>();
		try {
			String sql = "SELECT id,first_name, last_Name, birthdate, nationality,phone,reserve_id  FROM guest WHERE lastName like ? ";
			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				preparedStatement.setString(1, "%" + lastName + "%");
				preparedStatement.execute();
				transformResultSetInBookings(guest, preparedStatement);
			}
			return guest;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private void transformResultSetInBookings(List<Guests> guests, PreparedStatement preparedStatement)
			throws SQLException {
		try (ResultSet resultSet = preparedStatement.getResultSet()) {
			while (resultSet.next()) {
				Guests product = new Guests(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getDate(4), resultSet.getString(5), resultSet.getString(6), resultSet.getInt(7));
				guests.add(product);
			}

		}
	}

}
