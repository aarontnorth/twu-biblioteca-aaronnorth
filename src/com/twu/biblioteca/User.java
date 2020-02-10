package com.twu.biblioteca;

import java.util.HashMap;

public class User {
    String name;
    String email;
    String phoneNumber;
    String userID;
    //HashMap<String,Book> checkedOutBooks;

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
