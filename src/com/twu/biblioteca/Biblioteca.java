package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.HashMap;

public class Biblioteca {
    static HashMap<String,User> libraryGuests;
    static HashMap<String,Book> availableBooks;
    static HashMap<String,Movie> availableMovies;
    static HashMap<Integer,String> checkedOutBooks; //Integer key is bookID, String value is UserID
    static HashMap<Integer,Book> booksOwnedByLibrary;
    //static ArrayList<Boolean> isBookCheckedOut;
    String libraryID;
    int numOwnedBooks;

    Biblioteca(String ID){
        libraryID = ID;
        initBiblioteca();
    }

    void addOwnedBook(Book book){
        numOwnedBooks++;
        //isBookCheckedOut.add(numOwnedBooks,false);
        book.bookID = numOwnedBooks;
        booksOwnedByLibrary.put(book.bookID,book);
        availableBooks.put(book.title,book);
    }

    void addOwnedMovie(Movie movie){
        availableMovies.put(movie.title,movie);
    }

    void addNewLibraryGuest(User user){
        libraryGuests.put(user.userID,user);
    }

    private void initBiblioteca(){
        availableBooks = new HashMap<String, Book>();
        availableMovies = new HashMap<String, Movie>();
        booksOwnedByLibrary = new HashMap<Integer, Book>();
        libraryGuests = new HashMap<String, User>();
        checkedOutBooks = new HashMap<Integer, String>();
        numOwnedBooks = 0;
    }


    boolean checkIn(Integer bookID) {
        if(booksOwnedByLibrary.containsKey(bookID) && checkedOutBooks.containsKey(bookID)){
            availableBooks.put(booksOwnedByLibrary.get(bookID).title, booksOwnedByLibrary.get(bookID));
            return true;
        }
        return false;
    }

    boolean checkOut(String title, char type, String userID){
        switch(type){
            case 'B':
                return checkOutBook(title,userID);
            case 'M':
                return checkOutMovie(title);
        }
        return false;
    }

    boolean checkOutBook(String bookTitle, String userID) {
        if(availableBooks.containsKey(bookTitle)) {
            checkedOutBooks.put(availableBooks.get(bookTitle).bookID, userID);
            availableBooks.remove(bookTitle);
            return true;
        }
        return false;
        //System.out.println(getListOfAvailableBooks())
    }

    boolean checkOutMovie(String movieTitle) {
        if(availableMovies.containsKey(movieTitle)) {
            availableMovies.remove(movieTitle);
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

    void printAvailableMovies() {
        System.out.println("Here are our currently available movies:");
        for(Movie movie: availableMovies.values()){
            System.out.println(movie.getMovieInfo());
        }
    }

    public User getUserByID(String userID) {
        return libraryGuests.get(userID);
    }
}
