package edu.lawrence.freecycle.Classes;
import java.util.ArrayList;
import java.util.List;

public class Item {

    //Member variables
    private int itemId;
    private int donorId;
    private String name;
    private String description;
    private char status; //a for available, s for selected recipient, d for donated
    private List<String> tags = new ArrayList<>();

    //Blank constructor
    public Item() {}

    //Getter and setter methods
    public int getItemId()
    {
        return this.itemId;
    }

    public void setItemId(int itemId)
    {
        this.itemId = itemId;
    }

    public int getDonorId()
    {
        return this.donorId;
    }

    public void setDonorId(int donorId)
    {
        this.donorId = donorId;
    }

    public String getItemName()
    {
        return this.name;
    }

    public void setItemName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return this.description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public char getStatus()
    {
        return this.status;
    }

    public void setStatus(char status)
    {
        this.status = status;
    }

    public List<String> getTags()
    {
        return this.tags;
    }

    public void setTags(List<String> tags)
    {
        this.tags = tags;
    }
}
