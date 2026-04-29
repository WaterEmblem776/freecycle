package edu.lawrence.freecycle.Classes;

public class Message {

    //member variables first
    private String message;
    private int senderId;
    private int recieverId;

    //blank constructor
    public Message() {};

    //getter and setter methods
    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public int getSenderId()
    {
        return senderId;
    }

    public void setSenderId(int senderId)
    {
        this.senderId = senderId;
    }

    public int getRecieverId()
    {
        return recieverId;
    }

    public void setRecieverId(int recieverId)
    {
        this.recieverId = recieverId;
    }


}
