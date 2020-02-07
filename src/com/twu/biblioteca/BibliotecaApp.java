package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Scanner;

public class BibliotecaApp {

    static ArrayList<Book> availableBooks;
    static String errorMessage = "Please select a valid option!";
    static Scanner myScanner;

    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);  // Create a Scanner object
        setUpAvailableBooks();
        System.out.println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");
        runMainMenu();
    }

    static String getUserInput(){
        String userInput = myScanner.nextLine();  // Read user input
        return userInput;
    }

    private static void runMainMenu() {
        Boolean userWantsToBrowse = true;
        while(userWantsToBrowse) {
            String userInput = getUserInput();
            try {
                pickMenuOption(userInput);
            } catch (IncorrectOptionException e) {
                printErrorMessage();
            } catch (quitException e) {
                userWantsToBrowse = false;
            }
        }
    }

    static void showMainMenu() {
        System.out.println("What would you like to do? Type the command listed in parentheses");
        System.out.println("See available books (Books)");
    }

    static void pickMenuOption(String userInput) throws IncorrectOptionException, quitException {
        if(userInput.equals("q")){
            throw new quitException("User wants to quit");
        }
        else if(userInput.equals("Books")){
            printAvailableBooks();
        }
        else{
            throw new IncorrectOptionException(errorMessage);
        }
    }

    static void printErrorMessage() {
        System.out.println(errorMessage);
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

    public static class quitException extends Exception {
        public quitException(String errorMessage) {
            super(errorMessage);
        }
    }

    public static class IncorrectOptionException extends Exception {
        public IncorrectOptionException(String errorMessage) {
            super(errorMessage);
        }
    }


}
