package edu.lawrence.freecycle.RowMappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import edu.lawrence.freecycle.Classes.Message;

public class MessageRowMapper implements RowMapper<Message> {

    @Override
    public Message mapRow(ResultSet rs, int rowNum) throws SQLException {
        Message message = new Message();

        message.setMessage(rs.getString("message"));
        message.setSenderId(rs.getInt("senderid"));
        message.setReceiverId(rs.getInt("recieverid"));

        return message;
    }
}