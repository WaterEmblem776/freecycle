package edu.lawrence.freecycle.Repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import edu.lawrence.freecycle.Classes.Transfer;
import edu.lawrence.freecycle.RowMappers.TransferRowMapper;

@Repository
public class TransferDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    //First we create a save method to create new transfers once an interest is made 
    public void save(Transfer transfer)
    {
        //We intentionally don't fill in Site or Time. These will be updated by one of the two users later.
        String sql = "insert into transfers(id, itemid, donorid, recipientid) values (?, ?, ?, ?)";

        //We need to make sure that no two ids are the same. Hence: randomness
        int random = (int)(Math.random()*20000);

        jdbcTemplate.update(sql,
            random,
            transfer.getItemId(),
            transfer.getDonorId(),
            transfer.getRecipientId()
        );

        //We then update the item's status so that it no longer shows up when searched.
        //We first need to find the itemid from the transfer
        int itemId = transfer.getItemId();

        //Then we can go to the item in the database and change its status
        sql = "UPDATE items SET status='t' WHERE id=?";
        jdbcTemplate.update(sql, itemId);
    }

    //We then need to add a way for users to update the transfer once it shows up
    public void addSetting(String site, String time, int transferId)
    {
        String sql = "UPDATE transfers SET site=?, time=? WHERE id=?";

        jdbcTemplate.update(sql,
            site,
            time,
            transferId
        );
    }

    //This deletes all transfers and the original item
    public void completeTransfer(int transferId)
    {
        //First get the item id from the transferid (again)
        String sql = "SELECT itemid FROM transfers where id=?";
        int itemId = jdbcTemplate.queryForObject(sql, Integer.class, transferId);

        //Then delete the transfer
        sql = "DELETE FROM transfers WHERE id=?";
        jdbcTemplate.update(sql,
            transferId
        );

        //And finally remove the item
        sql = "DELETE FROM items where id=?";
        jdbcTemplate.update(sql,
            itemId
        );

    }

    //and this serves as a way to delete a transfer (deselected recipient)
    public void deselectRecipient (int transferId)
    {
        //First get the item id from the transferid
        String sql = "SELECT itemid FROM transfers where id=?";
        int itemId = jdbcTemplate.queryForObject(sql, Integer.class, transferId);

        //Then we can delete the transfer
        sql = "DELETE FROM transfers WHERE id=?";
        jdbcTemplate.update(sql,
            transferId
        );
        
        //And finally use the itemId from earlier to make its parent Item available again.
        sql = "UPDATE items SET status='a' WHERE id=?";
        jdbcTemplate.update(sql, itemId);
    }

    //Find all transfers associated with a user.
    public Transfer findById(int userId) 
    {
	    String sql = "SELECT * FROM transfers where donorid=? OR recipientid=?";
        RowMapper<Transfer> rowMapper = new TransferRowMapper();
        return jdbcTemplate.queryForObject(sql, rowMapper, userId, userId);
    }

    //Find all transfers associated with a site.
    public Transfer findBySite(String site) 
    {
	    String sql = "SELECT * FROM transfers where site=?";
        RowMapper<Transfer> rowMapper = new TransferRowMapper();
        return jdbcTemplate.queryForObject(sql, rowMapper, site);
    }

    //Find all transfers associated with a time.
    public Transfer findByTime(String time) 
    {
	    String sql = "SELECT * FROM transfers where time=?";
        RowMapper<Transfer> rowMapper = new TransferRowMapper();
        return jdbcTemplate.queryForObject(sql, rowMapper, time);
    }

    
    
    


}
