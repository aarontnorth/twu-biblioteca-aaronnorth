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
            System.out.println("Please sign in with your user ID (xxx-xxxx)"); //Throws exception if userInput not in LibraryGuest data structure
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
        boolean userWantsToBrowse = true;
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
            System.out.println("Add new item (N)");
            System.out.println("Add new User (U) ");
        }
        System.out.println("Check out an item (C)");
        System.out.println("Return an item (R)");
        System.out.println("Quit (Q)");
    }

    static void pickMenuOption(String userInput) throws IncorrectOptionException, quitException {
        switch(userInput.charAt(0)){
            case 'Q':
                throw new quitException("User wants to quit");
            case 'B':
                myBiblioteca.printAvailableBooks();
                break;
            case 'M':
                myBiblioteca.printAvailableMovies();
                break;
            case 'C':
                startCheckOut();
                break;
            case 'R':
                startCheckIn();
                break;
            case 'G':
                if(currentUser.isLibrarian){
                    myBiblioteca.printAllUsers();
                    break;
                }
                else{throw new IncorrectOptionException(errorMessage);}
            case 'N':
                if(currentUser.isLibrarian){
                    addNewItem();
                    break;
                }
                else{throw new IncorrectOptionException(errorMessage);}
            case 'U':
                if(currentUser.isLibrarian){
                    addNewUser();
                }
                else{throw new IncorrectOptionException(errorMessage);}
            default:
                throw new IncorrectOptionException(errorMessage);
        }
    }

    private static void addNewItem() {
        char type = getItemTypeFromUser();
        switch(type){
            case 'M':
                addNewMovie();
                break;
            case 'B':
                addNewBook();
                break;
        }
    }

    private static void addNewBook() {
        System.out.println("Please input the following information. ");
        System.out.println("Title:");
        String title = getUserInput();
        System.out.println("Year:");
        String year = getUserInput();
        System.out.println("Author");
        String author = getUserInput();
        myBiblioteca.addOwnedBook(new Book(title,author,year));
        System.out.println(String.format("The book \"%s\" has been added",title));
    }

    private static void addNewMovie() {
        System.out.println("Please input the following information. ");
        System.out.println("Title:");
        String title = getUserInput();
        System.out.println("Year:");
        String year = getUserInput();
        System.out.println("Director:");
        String director = getUserInput();
        System.out.println("Rating:");
        String rating = getUserInput();
        myBiblioteca.addOwnedMovie(new Movie(title,year,director,rating));
        System.out.println(String.format("The movie \"%s\" has been added",title));
    }

    private static void addNewUser() {
        System.out.println("Please input the following information. ");
        System.out.println("Name:");
        String name = getUserInput();
        System.out.println("Email");
        String email = getUserInput();
        System.out.println("Phone Number:");
        String phoneNum = getUserInput();
        System.out.println("User ID:");
        String userID = getUserInput();
        System.out.println("Is this person an employee of the library? (Y/N)");
        boolean isLibrarian = false;
        if(getUserInput().equals("Y")){isLibrarian=true;}
        myBiblioteca.addNewLibraryGuest(new User(name,email,phoneNum,userID,isLibrarian));
        System.out.println(String.format("The User \"%s\" has been added to the system",name));
    }

    static void startCheckOut() {
        char type = getItemTypeFromUser();
        System.out.println("Type the exact title of the item you would like to checkout: ");
        String title = getUserInput();
        boolean successfulCheckout = myBiblioteca.checkOut(title,type,currentUser.userID);
        try {
            finishCheckOut(successfulCheckout);
        } catch (UnavailableException e) {
            System.out.println("Sorry, that " + type + " is not available");
        }

    }


    static void finishCheckOut(boolean successfulCheckout) throws UnavailableException{
        if(successfulCheckout){
            System.out.println("Thank you! Enjoy :) ");
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

    private static char getItemTypeFromUser() {
        System.out.println("What type of item?");
        System.out.println("Book (B)");
        System.out.println("Movie (M)");
        return getUserInput().charAt(0); //No input security
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
