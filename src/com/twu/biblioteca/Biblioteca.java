package com.twu.biblioteca;

import java.util.HashMap;

public class Biblioteca {
    static HashMap<String,User> libraryGuests;
    static HashMap<String,Book> availableBooks;
    static HashMap<String,Movie> availableMovies;
    static HashMap<String,Book> checkedOutBooks;
    String libraryID;

    Biblioteca(String ID){
        libraryID = ID;
    }

    void setUpBiblioteca(){
        this.availableBooks = new HashMap<String, Book>();
        this.availableMovies = new HashMap<String, Movie>();
        libraryGuests = new HashMap<String, User>();
        checkedOutBooks = new HashMap<String, Book>();
        this.availableBooks.put("Great Expectations",new Book("Great Expectations","Someone Important","1929 probably",this.libraryID));
        this.availableBooks.put("Treasure Island",new Book("Treasure Island","Robert Louis Stevenson","Like 1850",this.libraryID));
        this.availableBooks.put("Little Women",new Book("Little Women","An Icon","1865 I guess",this.libraryID));
        availableMovies.put("Little Women",new Movie("Little Women","2019","Greta Gerwig","10"));
        availableMovies.put("Lilo and Stitch",new Movie("Lilo and Stitch","2002","Alan Silvestri(not)","unrated"));
        availableMovies.put("National Treasure", new Movie("National Treasure","2004","Jon Turtletaub","10"));
        checkedOutBooks = new HashMap<String, Book>();
        libraryGuests.put("000-0000",new User("Jack","jack@jack.com","867-5309","000-0000"));
        libraryGuests.put("000-0001",new User("Jill","jill@jack.com","867-0000","000-0001"));
        libraryGuests.put("000-0002",new User("John","john@jack.com","867-1111","000-0002"));
        libraryGuests.put("123-4567",new User("Jack","jack@jack.com","867-5309","123-4567"));
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
            availableBooks.get(bookTitle).checkedOutTo = userID;
            checkedOutBooks.put(bookTitle, availableBooks.get(bookTitle));
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
