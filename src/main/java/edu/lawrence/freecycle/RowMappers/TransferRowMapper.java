package edu.lawrence.freecycle.RowMappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import edu.lawrence.freecycle.Classes.Transfer;

public class TransferRowMapper implements RowMapper<Transfer> {
    @Override
    public Transfer mapRow(ResultSet row, int rowNum) throws SQLException {
        Transfer t = new Transfer();
        t.setTransferId(row.getInt("id"));
        t.setTransferItemId(row.getInt("itemid"));
        t.setDonorId(row.getInt("donorid"));
        t.setRecipientId(row.getInt("recipientid"));
        t.setSiteName(row.getString("sitename"));
        t.setTransferTime(row.getString("time"));
        return t;
    }
}
