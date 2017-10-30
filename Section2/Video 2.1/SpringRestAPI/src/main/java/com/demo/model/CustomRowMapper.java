package com.demo.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class CustomRowMapper implements RowMapper
{
	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		user.setUserId(rs.getInt("userId"));
		user.setUserName(rs.getString("username"));
		user.setPhone(rs.getString("phone"));
		return user;
	}
}
