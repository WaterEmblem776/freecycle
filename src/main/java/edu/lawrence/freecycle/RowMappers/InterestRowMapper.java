package edu.lawrence.freecycle.RowMappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import edu.lawrence.freecycle.Classes.Interest;

public class InterestRowMapper implements RowMapper<Interest>{
    @Override
    public Interest mapRow(ResultSet row, int rowNum) throws SQLException {
        Interest i = new Interest();
        i.setInterestId(row.getInt("id"));
        i.setInterestItemId(row.getInt("itemid"));
        i.setInterestUserId(row.getInt("userid"));
        i.setSelectionStatus(row.getBoolean("isselected"));
        return i;
    }
}
