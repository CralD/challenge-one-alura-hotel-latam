package com.alura.hotel.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;

import com.alura.hotel.model.Bookings;

public class BookingsDao {

	private Connection connection;

	public BookingsDao(Connection connection) {
		this.connection = connection;
	}

	public void save(Bookings bookings) {
		try {
			String sql = "INSERT INTO bookings(entry_date,departure_date,reserve_value,pay_method) VALUES(?,?,?,?)";
			try (PreparedStatement preparedStatement = connection.prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS)) {
				preparedStatement.setDate(1, bookings.getEntryDate());
				preparedStatement.setDate(2, bookings.getDepartureDate());
				preparedStatement.setString(3, bookings.getValue());
				preparedStatement.setString(4, bookings.getPayMethod());
				preparedStatement.executeUpdate();
				try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
					while (resultSet.next()) {
						bookings.setId(resultSet.getInt(1));
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void delete(Integer id) {
		try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM bookings WHERE id = ?")) {
			preparedStatement.setInt(1, id);
			preparedStatement.execute();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void update(Integer id, Date entryDate, Date departureDate, String value, String payMethod) {
		try {
			String sql = "UPDATE bookings SET entry_date = ?, departure_date= ? , reserve_value = ?, pay_method = ? WHERE id = ? ";
			try (PreparedStatement preparedStatement = connection.prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS)) {
				preparedStatement.setDate(1, entryDate);
				preparedStatement.setDate(2, departureDate);
				preparedStatement.setString(3, value);
				preparedStatement.setString(4, payMethod);
				preparedStatement.setInt(5, id);
				preparedStatement.execute();

			}
		} catch (SQLException e) {
			throw new RuntimeException(e);

		}

	}

	public List<Bookings> bookingList() {
		List<Bookings> bookings = new ArrayList<>();
		try {
			String sql = "SELECT id, entry_date, departure_date, reserve_value, pay_method FROM bookings";
			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				preparedStatement.execute();
				transformResultSetInBookings(bookings, preparedStatement);
			}
			return bookings;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Bookings> bookingsListById(String id) {
		List<Bookings> bookings = new ArrayList<>();
		try {
			String sql = "SELECT id, entry_date, departure_date, reserve_value, pay_method FROM bookings WHERE id = ? ";
			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				preparedStatement.setString(1, id);
				preparedStatement.execute();
				transformResultSetInBookings(bookings, preparedStatement);
			}
			return bookings;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private void transformResultSetInBookings(List<Bookings> bookings, PreparedStatement preparedStatement)
			throws SQLException {
		try (ResultSet resultSet = preparedStatement.getResultSet()) {
			while (resultSet.next()) {
				Bookings product = new Bookings(resultSet.getInt(1), resultSet.getDate(2), resultSet.getDate(3),
						resultSet.getString(4), resultSet.getString(5));
				bookings.add(product);
			}
		}
	}
}
