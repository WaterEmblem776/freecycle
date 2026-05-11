package edu.lawrence.freecycle.RowMappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import edu.lawrence.freecycle.Classes.Interest;

public class InterestRowMapper implements RowMapper<Interest>{
    @Override
    public Interest mapRow(ResultSet row, int rowNum) throws SQLException {
        Interest i = new Interest();
        i.setId(row.getInt("id"));
        i.setItemId(row.getInt("itemid"));
        i.setUserId(row.getInt("userid"));
        i.setIsSelected(row.getBoolean("isselected"));
        return i;
    }
}
