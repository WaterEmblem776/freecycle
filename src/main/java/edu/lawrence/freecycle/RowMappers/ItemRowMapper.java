package edu.lawrence.freecycle.RowMappers;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.lawrence.freecycle.Classes.Item;

public class ItemRowMapper implements RowMapper<Item> {
    @Override
    public Item mapRow(ResultSet row, int rowNum) throws SQLException {
        Item i = new Item();
        i.setItemId(row.getInt("id"));
        i.setDonorId(row.getInt("donorid"));
        i.setItemName(row.getString("name"));
        i.setDescription(row.getString("description"));
        i.setTags(convertArrayToList(row.getString("tags")));
        return i;
    }

    //Because we have the list of tags stored as a JSON array, we have to use Jackson to convert it to a List of strings.
    List<String> convertArrayToList(String json)
    {
        ObjectMapper objectMapper = new ObjectMapper();
        List<String> itemList = new ArrayList<>();
        
        try {
            itemList = objectMapper.readValue(json, new TypeReference<List<String>>(){});

        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return itemList;
    }
}
