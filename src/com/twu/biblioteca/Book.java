package com.twu.biblioteca;

public class Book {
    int bookID;
    String title;
    String author;
    String yearPublished;

    Book(String title){
        this.title = title;
        this.author = "IDK";
        this.yearPublished = "IDK";
    }

    Book(String title,String author,String year){
        this.title = title;
        this.author = author;
        this.yearPublished = year;
    }

    void setBookID(int bookID){
        this.bookID = bookID;
    }

    String getBookInfo(){
        return title + " | " + author + " | " + yearPublished;
    }

    @Override
    public String toString(){
        return title;
    }
}
