package com.twu.biblioteca;

public class Book {
    String title;
    String author;
    String yearPublished;

    Book(String title,String author,String year){
        this.title = title;
        this.author = author;
        this.yearPublished = year;
    }

    @Override
    public String toString(){
        return title + " | " + author + " | " + yearPublished;
    }
}
