package edu.lawrence.freecycle.Repositories;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import edu.lawrence.freecycle.Classes.Item;
import edu.lawrence.freecycle.RowMappers.ItemRowMapper;

@Repository
public class ItemDAO {

    private final JdbcTemplate jdbcTemplate;

    //Constructor
    public ItemDAO(JdbcTemplate jdbcTemplate) 
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    //First we create a save method to let users create new items
    public void save(Item item)
    {
        String sql = "insert into items(id, donorid, name, description, status, tags) values (?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql,
            item.getItemId(),
            item.getDonorId(),
            item.getItemName(),
            item.getDescription(),
            item.getStatus(),
            constructJSONArrayFromList(item.getTags())
        );
    }

    //We also need a method to remove the offered item. This also removes all associated interests and/or transfers.
    public void cancel(int itemid)
    {
        String sql = "DELETE FROM items WHERE id=?";
        jdbcTemplate.update(sql,
            itemid
        );

        //Remove any interests
        sql = "DELETE FROM interests WHERE itemid=?";
        jdbcTemplate.update(sql,
            itemid
        );

        //and finally remove transfers
        sql = "DELETE FROM transfers WHERE itemid=?";
        jdbcTemplate.update(sql,
            itemid
        );
        
    }


    //This method pretty much just exists to convert lists of tags into a string that SQL will save as a JSON array.
    public String constructJSONArrayFromList (List<String> list) 
    {
        String tagList = "JSON_ARRAY(";

        //We start by iterating through the entire list of tags
        for (int i = 0; i < list.size(); i++)
        {
            //Append the next item onto the end of the string
            tagList = tagList + "'" + list.get(i) + "'";
            
            //If this is not the last item in the list, add a comma
            if (i != list.size())
                tagList = tagList + ",";
            
        }
        //And finally complete the JSON_ARRAY with a closed paranthesis
        tagList = tagList + ")";
        return tagList;
    }

    //There's a bunch of different methods below that let a user search for an item in different ways.
    //The first one just returns all available items.
    public List<Item> findItems()
    {
        String sql = "select * from items where status=a";
        return jdbcTemplate.query(sql, new ItemRowMapper());
    }

    //This method returns an item of a specific id.
    
    public Item findItem(int id)
    {
        String sql = "select * from items where id=?";
        return jdbcTemplate.queryForObject(sql, new ItemRowMapper(), id);
    }

    //This method returns all items put up by a specific user.
    public List<Item> findItemsByDonorId(int donorId)
    {
        String sql = "select * from items where donorid=? AND where status=a";
        return jdbcTemplate.query(sql, new ItemRowMapper(), donorId);
    }

    //This method returns all items with the tags in the query.
    public List<Item> findItemsByTags(List<String> tags)
    {
        String sql = "select * from items where tags=? AND where status=a";

        return jdbcTemplate.query(sql, new ItemRowMapper(), constructJSONArrayFromList(tags));
    }

}
