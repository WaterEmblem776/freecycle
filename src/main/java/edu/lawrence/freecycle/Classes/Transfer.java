package edu.lawrence.freecycle.Classes;

public class Transfer {

    //Member variables
    private int transferId;
    private int itemId;
    private int donorId;
    private int recipientId;
    private String site;
    private String time; //I want this to be in ISO_LOCAL_DATE_TIME format, but it's a bonus project.

    //Blank constructor
    public Transfer() {};

    //Getter and setter methods
    public int getTransferId()
    {
        return this.transferId;
    }

    public void setTransferId(int transferId)
    {
        this.transferId = transferId;
    }

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

    public int getRecipientId()
    {
        return this.recipientId;
    }

    public void setRecipientId(int recipientId)
    {
        this.recipientId = recipientId;
    }

    public String getSite()
    {
        return this.site;
    }

    public void setSite(String siteName)
    {
        this.site = siteName;
    }

    public String getTime() 
    {
        return time;
    }

    public void setTime(String transferTime)
    {
        this.time = transferTime;
    }

}
