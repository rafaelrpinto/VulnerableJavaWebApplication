package com.github.rafaelrpinto.vulnerablejavawebapp.repository;

import java.sql.PreparedStatement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.github.rafaelrpinto.vulnerablejavawebapp.model.User;

/**
 * 
 * Repository for Users.
 * 
 * @author Rafael
 *
 */
@Repository
public class UserRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static final RowMapper<User> ROW_MAPPER = (rs, n) -> {
		User user = new User();
		user.setId(rs.getInt("ID"));
		user.setName(rs.getString("NAME"));
		user.setEmail(rs.getString("EMAIL"));
		return user;
	};

	/**
	 * Creates a new user on the database.
	 */
	public void insert(User user) {
		String sql = "INSERT INTO USER(NAME, EMAIL, PASSWORD) VALUES (?, ?, ?)";
		KeyHolder holder = new GeneratedKeyHolder();
		
		this.jdbcTemplate.update((connection) -> {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getEmail().toLowerCase().trim());
			pstmt.setString(3, user.getPassword());
			return pstmt;
		}, holder);
		
		user.setId(holder.getKey().intValue());
	}

	/**
	 * @return The user with the provided ID.
	 */
	public User find(int id) {
		String sql = getBaseQuery() + " WHERE ID = ?";
		return this.jdbcTemplate.queryForObject(sql, new Object[] { id }, ROW_MAPPER);
	};
	
	/**
	 * @return The user with the provided e-mail.
	 */
	public User find(String email) {
		try {
			String sql = getBaseQuery() + " WHERE EMAIL = ?";
			return this.jdbcTemplate.queryForObject(sql, new Object[] { email.toLowerCase().trim() }, ROW_MAPPER);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	};

	/**
	 * @return the user if the provided credentials are correct.
	 */
	public User authenticate(String email, String password) {
		try {
			String sql = getBaseQuery() + " WHERE EMAIL = ? AND PASSWORD = ?";
			return this.jdbcTemplate.queryForObject(sql, new Object[] { email.toLowerCase().trim(), password }, ROW_MAPPER);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	};

	private String getBaseQuery() {
		return "SELECT ID, NAME, EMAIL FROM USER";
	}

}
