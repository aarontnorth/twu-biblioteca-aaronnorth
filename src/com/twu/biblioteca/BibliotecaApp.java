package com.twu.biblioteca;

import java.util.Scanner;

public class BibliotecaApp {

    static String errorMessage = "Please select a valid option!";
    static Scanner myScanner;
    static Biblioteca myBiblioteca;
    static String userID = "123-4567";

    BibliotecaApp(Biblioteca biblioteca){
        myBiblioteca = biblioteca;
    }

    public static void main(String[] args) {
        myScanner = new Scanner(System.in);  // Create a Scanner object
        myBiblioteca = new Biblioteca("001");
        myBiblioteca.setUpBiblioteca();
        System.out.println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");
        System.out.println("Please sign in with your user ID (xxx-xxxx)");
        userID = getUserInput();
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
        System.out.println("See available movies (M)");
        System.out.println("Check out a book (COB)");
        System.out.println("Check in a book (CIB)");
        System.out.println("Check out a movie (COM)");
    }

    static void pickMenuOption(String userInput) throws IncorrectOptionException, quitException {
        if(userInput.equals("q")){
            throw new quitException("User wants to quit");
        }
        else if(userInput.equals("B")){
            myBiblioteca.printAvailableBooks();
        }
        else if(userInput.equals("M")){
            myBiblioteca.printAvailableMovies();
        }
        else if(userInput.equals("COB")){
            startCheckOut('B');
        }
        else if(userInput.equals("CIB")){
            startCheckIn();
        }
        else if(userInput.equals("COM")){
            startCheckOut('M');
        }
        else{
            throw new IncorrectOptionException(errorMessage);
        }
    }

    static void startCheckOut(char input) {
        String type = "";
        switch(input){
            case 'B':
                type = "book";
            case 'M':
                type = "movie";
        }
        System.out.println("Type the exact title of the " + type + " you would like to checkout: ");
        String title = getUserInput();
        boolean successfulCheckout = myBiblioteca.checkOut(title,input,userID);
        try {
            finishCheckOut(successfulCheckout,type);
        } catch (UnavailableException e) {
            System.out.println("Sorry, that " + type + " is not available");
        }

    }

    static void finishCheckOut(boolean successfulCheckout,String type) throws UnavailableException{
        if(successfulCheckout){
            System.out.println("Thank you! Enjoy the " + type);
        }
        else{
            throw new UnavailableException(errorMessage);
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

    static class UnavailableException extends Exception {
        public UnavailableException(String errorMessage) {
            super(errorMessage);
        }
    }


    static class BookDoesNotBelongException extends Exception {
        public BookDoesNotBelongException(String errorMessage){
            super(errorMessage);
        }
    }
}
