package edu.lawrence.freecycle.Classes;

public class Transfer {

    //Member variables
    private int transferId;
    private int itemId;
    private int donorId;
    private int recipientId;
    private String siteName;
    private String transferTime; //I want this to be in ISO_LOCAL_DATE_TIME format, but it's a bonus project.

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

    public int getTransferItemId()
    {
        return this.itemId;
    }

    public void setTransferItemId(int itemId)
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

    public String getSiteName()
    {
        return this.siteName;
    }

    public void setSiteName(String siteName)
    {
        this.siteName = siteName;
    }

    public String getTransferTime() 
    {
        return transferTime;
    }

    public void setTransferTime(String transferTime)
    {
        this.transferTime = transferTime;
    }

}
