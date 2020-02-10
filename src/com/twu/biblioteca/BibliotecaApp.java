package com.twu.biblioteca;

import java.util.Scanner;

public class BibliotecaApp {

    static String errorMessage = "Please select a valid option!";
    static Scanner myScanner;
    static Biblioteca myBiblioteca;
    static User currentUser;
    //static boolean isLibrarian;

    BibliotecaApp(Biblioteca biblioteca){
        myBiblioteca = biblioteca;
    }

    public static void main(String[] args) {
        myScanner = new Scanner(System.in);  // Create a Scanner object
        myBiblioteca = new Biblioteca("001");
        setupBiblioteca(myBiblioteca);
        System.out.println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");
        //isLibrarian = false;
        while(true){
            System.out.println("Please sign in with your user ID (xxx-xxxx)");
            try {
                currentUser = myBiblioteca.getUserByID(getUserInput());
                break;
            } catch (Exception e) {
                System.out.println("Please put a valid userID");
            }
        }
        runMainMenu();
    }

    public static void setupBiblioteca(Biblioteca myBiblioteca) {
        myBiblioteca.addOwnedBook(new Book("Great Expectations", "Someone Important", "1929 probably"));
        myBiblioteca.addOwnedBook(new Book("Treasure Island","Robert Louis Stevenson","Like 1850"));
        myBiblioteca.addOwnedBook(new Book("Little Women","An Icon","1865 I guess"));

        myBiblioteca.addOwnedMovie(new Movie("Little Women","2019","Greta Gerwig","10"));
        myBiblioteca.addOwnedMovie(new Movie("Lilo and Stitch","2002","Alan Silvestri(not)","unrated"));
        myBiblioteca.addOwnedMovie( new Movie("National Treasure","2004","Jon Turtletaub","10"));

        myBiblioteca.addNewLibraryGuest(new User("Jack","jack@jack.com","867-5309","000-0000",true));
        myBiblioteca.addNewLibraryGuest(new User("Jill","jill@jack.com","867-0000","000-0001",false));
        myBiblioteca.addNewLibraryGuest(new User("John","john@jack.com","867-1111","123-4567",false));
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
        if(currentUser.isLibrarian){
            System.out.println("See all library guests (G)");
            System.out.println("Add new book (NB)");
            System.out.println("Add new movie (M)");
        }
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
                break;
            case 'M':
                type = "movie";
                break;
        }
        System.out.println("Type the exact title of the " + type + " you would like to checkout: ");
        String title = getUserInput();
        boolean successfulCheckout = myBiblioteca.checkOut(title,input,currentUser.userID);
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
        System.out.println("Type the ID associated with your book");
        Integer ID = null;
        try {
            ID = Integer.parseInt(getUserInput());
        } catch (NumberFormatException e) {
            System.out.println("Please try again with a valid ID");
        }
        boolean success = myBiblioteca.checkIn(ID);
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
