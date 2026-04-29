package edu.lawrence.freecycle.Classes;

public class User {

    //Member variables
    private int userId;
    private String username;
    private String password;
    private String fullName;
    private String email; //Perhaps unnecessary with our own messaging system?
    private String phone;
    //private String address; Originally used to find meeting spots. Not necessary with our own messaging.
    private String bio;

    //Blank constructor
    public User() {}

    //Getter and Setter Methods
    public int getUserId()
    {
        return this.userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    public String getUsername()
    {
        return this.username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return this.password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getFullName()
    {
        return this.fullName;
    }

    public void setFullName(String fullName)
    {
        this.fullName = fullName;
    }

    public String getEmail()
    {
        return this.email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPhone()
    {
        return this.phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getBio()
    {
        return this.bio;
    }

    public void setBio(String bio)
    {
        this.bio = bio;
    }
}
