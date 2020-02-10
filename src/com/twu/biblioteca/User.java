package com.twu.biblioteca;

import java.util.HashMap;

public class User {
    String name;
    String email;
    String phoneNumber;
    String userID;
    Boolean isLibrarian;
    //HashMap<String,Book> checkedOutBooks;

    User(String name,String email,String phoneNumber,String ID,Boolean isLibrarian){
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.userID = ID;
        this.isLibrarian = isLibrarian;
    }

    String getUserInfo(){
        return name + " | " + email + " | " + phoneNumber;
    }
}
