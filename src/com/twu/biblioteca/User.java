package com.twu.biblioteca;

public class User {
    String name;
    String email;
    String phoneNumber;
    String userID;

    User(String name,String email,String phoneNumber,String ID){
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.userID = ID;
    }

    String getUserInfo(){
        return name + " | " + email + " | " + phoneNumber;
    }
}
