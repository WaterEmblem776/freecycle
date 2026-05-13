package edu.lawrence.freecycle.Repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import edu.lawrence.freecycle.Classes.Message;
import edu.lawrence.freecycle.RowMappers.MessageRowMapper;

@Repository
public class MessageDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(Message message) {
        String sql = "INSERT INTO messages (message, senderid, recieverid) VALUES (?, ?, ?)";

        jdbcTemplate.update(
            sql,
            message.getMessage(),
            message.getSenderId(),
            message.getReceiverId()
        );
    }

    public List<Message> findByReceiver(int receiverId) {
        String sql = "SELECT * FROM messages WHERE recieverid = ?";

        return jdbcTemplate.query(
            sql,
            new MessageRowMapper(),
            receiverId
        );
    }
}
