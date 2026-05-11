package edu.lawrence.freecycle.Classes;

public class Interest {

    //Member variables
    private int id;
    private int itemId;
    private int userId;
    private boolean isSelected = false; //Will always be false at the start.

    //Blank constructor
    public Interest() {}

    //Getter and setter methods
    public int getId()
    {
        return this.id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getItemId()
    {
        return this.itemId;
    }

    public void setItemId(int itemId)
    {
        this.itemId = itemId;
    }

    public int getUserId()
    {
        return this.userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    public boolean getIsSelected() 
    {
        return this.isSelected;
    }

    public void setIsSelected(boolean isSelected)
    {
        this.isSelected = isSelected;
    }

}
