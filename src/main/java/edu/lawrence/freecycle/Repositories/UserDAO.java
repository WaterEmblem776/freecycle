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
	    String sql = "SELECT * FROM users WHERE username = ?";
        RowMapper<User> rowMapper = new UserRowMapper();
        return jdbcTemplate.queryForObject(sql, rowMapper, userName);
    }

    public User findById(int id) 
    {
        String sql = "SELECT * FROM users WHERE userid = ?";
        RowMapper<User> rowMapper = new UserRowMapper();
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    //Method for creating a new user
    public void createUser (User user)
    {
        //We take in a username and password and create a new user entry in the table
        String sql = "INSERT INTO users(userid, username, password, fullname, email, phone, bio) VALUES (?, ?, ?, ?, ?, ?, ?)";

        //We need to make sure that no two ids are the same. Hence: randomness
        int random = (int)(Math.random()*20000);

        jdbcTemplate.update(sql,
            random,
            user.getUsername(),
            user.getPassword(),
            user.getFullName(),
            user.getEmail(),
            user.getPhone(),
            user.getBio()
        );
        
    }

    //Method for deleting a user
    public void deleteUser (String username, String password)
    {
        //Opposite of the above method. If a user matches the username and password, that user is deleted.
        String sql = "DELETE FROM users WHERE username=? AND password=?";
        jdbcTemplate.update(sql,
            username,
            password
        );
    }

}
