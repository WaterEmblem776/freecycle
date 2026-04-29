package edu.lawrence.freecycle.Classes;

public class Interest {

    //Member variables
    private int interestId;
    private int itemId;
    private int userId;
    private boolean isSelected = false; //Will always be false at the start.

    //Blank constructor
    public Interest() {}

    //Getter and setter methods
    public int getInterestId()
    {
        return this.interestId;
    }

    public void setInterestId(int interestId)
    {
        this.interestId = interestId;
    }

    public int getInterestItemId()
    {
        return this.itemId;
    }

    public void setInterestItemId(int itemId)
    {
        this.itemId = itemId;
    }

    public int getInterestUserId()
    {
        return this.userId;
    }

    public void setInterestUserId(int userId)
    {
        this.userId = userId;
    }

    public boolean getSelectionStatus() 
    {
        return this.isSelected;
    }

    public void setSelectionStatus(boolean isSelected)
    {
        this.isSelected = isSelected;
    }

}
