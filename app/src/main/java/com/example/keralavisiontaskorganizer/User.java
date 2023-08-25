package com.example.keralavisiontaskorganizer;

public class User {
    private String uid;
    private String phoneNumber;
    private String username;
    private String role;

    private String waiting;

    // Add other fields as needed

    public User() {
        // Default constructor required for Firebase Realtime Database
    }


    public User(String uid, String phoneNumber, String username, String role, String waiting) {
        this.uid = uid;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.role = role;
        this.waiting = waiting;


    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public String setPhonenumber(String phoneNumber) {
       return this.phoneNumber = phoneNumber;
    }

    public String getUid() {
        return uid;
    }
    public String setUid(String uid) {
        return this.uid = uid;
    }

    public String getWaiting() {return waiting;}
    public void setWaiting(String waiting) {this.waiting = waiting;}


    // Add getters and setters for the fields
}
