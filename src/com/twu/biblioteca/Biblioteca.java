package com.twu.biblioteca;

import java.util.HashMap;

public class Biblioteca {
    static HashMap<String,Book> availableBooks;
    String libraryID;

    Biblioteca(String ID){
        libraryID = ID;
    }

    void setUpAvailableBooks(){
        this.availableBooks = new HashMap<String, Book>();
        this.availableBooks.put("Great Expectations",new Book("Great Expectations","Someone Important","1929 probably",this.libraryID));
        this.availableBooks.put("Treasure Island",new Book("Treasure Island","Robert Louis Stevenson","Like 1850",this.libraryID));
        this.availableBooks.put("Little Women",new Book("Little Women","An Icon","1865 I guess",this.libraryID));
    }


    boolean checkIn(String bookTitle,String ID) {
        if(ID.equals(this.libraryID)){
            availableBooks.put(bookTitle, new Book(bookTitle));
            return true;
        }
        else{
            return false;
        }
    }

    boolean checkOutBook(String bookTitle) {
        if(availableBooks.containsKey(bookTitle)) {
            availableBooks.remove(bookTitle);
            return true;
        }
        return false;
        //System.out.println(getListOfAvailableBooks())
    }

    void printAvailableBooks() {
        System.out.println("Here are our currently available books:");
        for(Book book: availableBooks.values()){
            System.out.println(book.getBookInfo());
        }
    }


}
