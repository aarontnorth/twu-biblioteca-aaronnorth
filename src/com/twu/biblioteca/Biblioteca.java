package com.twu.biblioteca;

import java.util.HashMap;

public class Biblioteca {
    static HashMap<String,Book> availableBooks;

    void checkOutBook(String bookTitle) {
        if(availableBooks.containsKey(bookTitle)) {
            availableBooks.remove(bookTitle);
        }
        //System.out.println(getListOfAvailableBooks());
    }

    void printAvailableBooks() {
        System.out.println("Here are our currently available books:");
        for(Book book: availableBooks.values()){
            System.out.println(book.getBookInfo());
        }
    }

}
