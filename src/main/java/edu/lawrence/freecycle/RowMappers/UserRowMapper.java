package edu.lawrence.freecycle.RowMappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import edu.lawrence.freecycle.Classes.User;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet row, int rowNum) throws SQLException {
        User p = new User();
        p.setUserId(row.getInt("id"));
        p.setUsername(row.getString("username"));
        p.setPassword(row.getString("password"));
        p.setFullName(row.getString("fullname"));
        p.setEmail(row.getString("email"));
        p.setPhone(row.getString("phone"));
        p.setBio(row.getString("bio"));
        return p;
    }

}
