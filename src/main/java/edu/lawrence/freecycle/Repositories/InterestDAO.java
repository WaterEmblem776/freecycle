package edu.lawrence.freecycle.Repositories;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import edu.lawrence.freecycle.Classes.Interest;
import edu.lawrence.freecycle.RowMappers.InterestRowMapper;

@Repository
public class InterestDAO {

    private final JdbcTemplate jdbcTemplate;

    //Constructor
    public InterestDAO(JdbcTemplate jdbcTemplate) 
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    //Method to save new interests to the database
    public void save(Interest interest)
    {
        String sql = "insert into interests(id, itemId, userId, isSelected) values (?, ?, ?, ?)";

        //We need to make sure that no two ids are the same. Hence: randomness
        int random = (int)(Math.random()*20000);

        jdbcTemplate.update(sql,
            random,
            interest.getInterestItemId(),
            interest.getInterestUserId(),
            interest.getSelectionStatus() //This is a boolean, hopefully it'll just return TRUE OR FALSE. I'm unsure though.
        );
    }

    //This method allows a potential recipient to withdraw their interest.
    public void withdraw(int interestId)
    {
        String sql = "DELETE FROM interests WHERE id=?";

        jdbcTemplate.update(sql,
            interestId
            );
    }

    //This method returns all interests in a specific item
    public List<Interest> findInterests(int itemId)
    {
        String sql = "select * from interests where itemid=?";
        return jdbcTemplate.query(sql, new InterestRowMapper(), itemId);
    }

}
