package com.github.rafaelrpinto.vulnerablejavawebapp.repository;

import java.sql.PreparedStatement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.github.rafaelrpinto.vulnerablejavawebapp.model.Advertisement;
import com.github.rafaelrpinto.vulnerablejavawebapp.model.User;

/**
 * 
 * Repository for advertisements.
 * 
 * @author Rafael
 *
 */
@Repository
public class AdvertisementRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final RowMapper<Advertisement> ROW_MAPPER = (rs, n) -> {
		Advertisement ad = new Advertisement();
		ad.setId(rs.getInt("ID"));
		ad.setCreateDate(rs.getDate("CREATE_DATE"));
		ad.setTitle(rs.getString("TITLE"));
		ad.setText(rs.getString("TEXT"));

		User user = new User();
		user.setId(rs.getInt("USER_ID"));
		user.setName(rs.getString("NAME"));
		user.setEmail(rs.getString("EMAIL"));
		ad.setUser(user);

		return ad;
	};

	/**
	 * Creates a new ad on the database.
	 */
	public void insert(Advertisement ad) {
		String sql = "INSERT INTO ADVERTISEMENT(CREATE_DATE, TITLE, TEXT, USER_ID) VALUES (?, ?, ?, ?)";
		KeyHolder holder = new GeneratedKeyHolder();

		this.jdbcTemplate.update((connection) -> {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setDate(1, new java.sql.Date(ad.getCreateDate().getTime()));
			pstmt.setString(2, ad.getTitle());
			pstmt.setString(3, ad.getText());
			pstmt.setInt(4, ad.getUser().getId());
			return pstmt;
		}, holder);

		ad.setId(holder.getKey().intValue());
	}

	/**
	 * @return All the ads ordered by date.
	 */
	public List<Advertisement> findAll() {
		return this.jdbcTemplate.query(getBaseQuery() + " ORDER BY ad.CREATE_DATE DESC", ROW_MAPPER);
	}

	/**
	 * @return All the ads with the matching keyword.
	 */
	public List<Advertisement> findByKeyword(String keyword) {
		String sql = getBaseQuery() + " WHERE ad.TITLE LIKE ? OR ad.TEXT LIKE ? ORDER BY ad.CREATE_DATE DESC";
		String wildcard = "%" + keyword + "%";
		return this.jdbcTemplate.query(sql, new Object[] { wildcard, wildcard }, ROW_MAPPER);
	}

	private String getBaseQuery() {
		return "SELECT ad.ID, ad.CREATE_DATE, ad.TITLE, ad.TEXT, ad.USER_ID, u.NAME, u.EMAIL FROM "
						+ "ADVERTISEMENT ad INNER JOIN USER u ON ad.USER_ID = u.ID";
	}
}
