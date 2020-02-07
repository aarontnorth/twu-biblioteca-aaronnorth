package com.twu.biblioteca;

import java.util.HashMap;
import java.util.Scanner;

public class BibliotecaApp {

    static String errorMessage = "Please select a valid option!";
    static Scanner myScanner;
    static Biblioteca myBiblioteca;

    BibliotecaApp(Biblioteca biblioteca){
        myBiblioteca = biblioteca;
    }

    public static void main(String[] args) {
        myScanner = new Scanner(System.in);  // Create a Scanner object
        myBiblioteca = new Biblioteca("001");
        myBiblioteca.setUpAvailableBooks();
        System.out.println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");
        runMainMenu();
    }

    private static void runMainMenu() {
        Boolean userWantsToBrowse = true;
        while(userWantsToBrowse) {
            showMainMenu();
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
        System.out.println();
        System.out.println("What would you like to do? Type the command listed in parentheses");
        System.out.println("See available books (B)");
        System.out.println("Check out a book (CO)");
        System.out.println("Check in a book (CI)");
    }

    static void pickMenuOption(String userInput) throws IncorrectOptionException, quitException {
        if(userInput.equals("q")){
            throw new quitException("User wants to quit");
        }
        else if(userInput.equals("B")){
            myBiblioteca.printAvailableBooks();
        }
        else if(userInput.equals("CO")){
            startCheckOut();
        }
        else if(userInput.equals("CI")){
            startCheckIn();
        }
        else{
            throw new IncorrectOptionException(errorMessage);
        }
    }

    static void startCheckOut() {
        System.out.println("Type the exact title of the book you would like to checkout: ");
        String bookTitle = getUserInput();
        boolean successfulCheckout = myBiblioteca.checkOutBook(bookTitle);
        try {
            finishCheckOut(successfulCheckout);
        } catch (BookUnavailableException e) {
            System.out.println("Sorry, that book is not available");
        }

    }

    static void finishCheckOut(boolean successfulCheckout) throws BookUnavailableException{
        if(successfulCheckout){
            System.out.println("Thank you! Enjoy the book");
        }
        else{
            throw new BookUnavailableException(errorMessage);
        }

    }

    static void startCheckIn(){
        System.out.println("Type the exact title of the book you would like to check in: ");
        String title = getUserInput();
        System.out.println("Type the library ID associated with your book");
        String ID = getUserInput();
        boolean success = myBiblioteca.checkIn(title, ID);
        try {
            finishCheckIn(success);
        } catch (BookDoesNotBelongException e) {
            System.out.println("That is not a valid book to return.");
        }

    }

    static void finishCheckIn(boolean successfulCheckout) throws BookDoesNotBelongException{
        if(successfulCheckout){
            System.out.println("Thank you for returning the book");
        }
        else{
            throw new BookDoesNotBelongException(errorMessage);
        }

    }

    static void printErrorMessage() {
        System.out.println(errorMessage);
    }

    static String getUserInput(){
        System.out.println();
        String userInput = myScanner.nextLine();  // Read user input
        System.out.println();
        return userInput;
    }

    static class quitException extends Exception {
        public quitException(String errorMessage) {
            super(errorMessage);
        }
    }

    static class IncorrectOptionException extends Exception {
        public IncorrectOptionException(String errorMessage) {
            super(errorMessage);
        }
    }

    static class BookUnavailableException extends Exception {
        public BookUnavailableException(String errorMessage) {
            super(errorMessage);
        }
    }


    static class BookDoesNotBelongException extends Exception {
        public BookDoesNotBelongException(String errorMessage){
            super(errorMessage);
        }
    }
}
