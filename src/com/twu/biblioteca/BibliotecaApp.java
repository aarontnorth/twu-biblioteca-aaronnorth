package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Scanner;

public class BibliotecaApp {

    static ArrayList<Book> availableBooks;
    static String errorMessage = "Please select a valid option!";

    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        setUpAvailableBooks();
        System.out.println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");
        runMainMenu(myObj);
    }

    private static void runMainMenu(Scanner myObj) {
        showMainMenu(myObj);
        String userInput = myObj.nextLine();  // Read user input
        pickMenuOption(userInput);
    }

    static void showMainMenu(Scanner myObj) {
        System.out.println("What would you like to do? Type the command listed in parentheses");
        System.out.println("See available books (Books)");
    }

    static void pickMenuOption(String userInput) {
        if(userInput.equals("Books")){
            printAvailableBooks();
        }
        else{
            printErrorMessage();
        }
    }

    static String printErrorMessage() {
        System.out.println(errorMessage);
        return errorMessage;
    }

    private static void printAvailableBooks() {
        System.out.println("Here are our currently available books:");
        System.out.println(getListOfAvailableBooks());
    }

    public static ArrayList<Book> setUpAvailableBooks(){
        availableBooks = new ArrayList<Book>();
        availableBooks.add(new Book("Great Expectations","Someone Important","1929 probably"));
        availableBooks.add(new Book("Treasure Island","Robert Louis Stevenson","Like 1850"));
        availableBooks.add(new Book("Little Women","An Icon","1865 I guess"));
        return availableBooks;
    }

    static String getListOfAvailableBooks(){
        String bookList = "";
        for(Book book: availableBooks){
            bookList = bookList + book.toString() + "\n";
        }
        return bookList;
    }


}
