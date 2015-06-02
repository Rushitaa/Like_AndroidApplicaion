package com.example.rushitaa.photo_app;

/**
 * Created by Rushitaa on 3/7/2015.
 */
public class User {
    private String id;
    private String Username;
    private String Email;
    private String Location;

    private String Likes;


    User(String userid, String username, String useremail,String userlocation, String userlikes) {
        id = userid;
        Username = username;
        Email = useremail;
        Location = userlocation;
        Likes = userlikes;


    }
    User(String username, String useremail,String userlocation, String userlikes) {

        Username = username;
        Email = useremail;
        Location = userlocation;
        Likes = userlikes;


    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getLikes() {
        return Likes;
    }

    public void setLikes(String likes) {
        Likes = likes;
    }




    @Override
    public String toString() {
        return this.getUsername();
    }
}
