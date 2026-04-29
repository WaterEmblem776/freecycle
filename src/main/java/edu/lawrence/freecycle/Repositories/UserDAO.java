package edu.lawrence.freecycle.Repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import edu.lawrence.freecycle.Classes.User;
import edu.lawrence.freecycle.RowMappers.UserRowMapper;

@Repository
public class UserDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public User findByUsername(String userName) 
    {
	    String sql = "SELECT * FROM users where username=?";
        RowMapper<User> rowMapper = new UserRowMapper();
        return jdbcTemplate.queryForObject(sql, rowMapper, userName);
    }

    public User findById(int id) 
    {
        String sql = "SELECT * FROM users where id=?";
        RowMapper<User> rowMapper = new UserRowMapper();
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

}
