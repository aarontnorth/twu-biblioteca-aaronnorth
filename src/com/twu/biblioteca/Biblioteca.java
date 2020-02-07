package com.twu.biblioteca;

import java.util.HashMap;

public class Biblioteca {
    static HashMap<String,Book> availableBooks;

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
