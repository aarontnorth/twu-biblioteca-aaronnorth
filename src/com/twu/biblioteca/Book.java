package com.twu.biblioteca;

public class Book {
    String libraryIBelongTo;
    String title;
    String author;
    String yearPublished;

    Book(String title){
        this.title = title;
        this.author = "IDK";
        this.yearPublished = "IDK";
    }

    Book(String title,String author,String year,String bibliotecaID){
        this.title = title;
        this.author = author;
        this.yearPublished = year;
        this.libraryIBelongTo = bibliotecaID;
    }

    String getBookInfo(){
        return title + " | " + author + " | " + yearPublished;
    }

    @Override
    public String toString(){
        return title;
    }
}
