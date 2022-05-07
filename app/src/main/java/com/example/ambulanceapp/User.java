package com.example.ambulanceapp;

/**
 * To indicate the type of data we are storing into the database
 */
public class User {
    private String email;
    private String name;
    private String phone_number;

    public User(){

    }
    public User(String name,String phone_number,String email){
        this.name=name;
        this.phone_number=phone_number;
        this.email=email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
}
