package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BibliotecaTest {

    static Biblioteca testBiblioteca;

    @Before
    public void init(){
        testBiblioteca = new Biblioteca("001");
    }

    @Before
    public void setupBiblioteca() {
        testBiblioteca.addOwnedBook(new Book("Great Expectations", "Someone Important", "1929 probably"));
        testBiblioteca.addOwnedBook(new Book("Treasure Island","Robert Louis Stevenson","Like 1850"));
        testBiblioteca.addOwnedBook(new Book("Little Women","An Icon","1865 I guess"));

        testBiblioteca.addOwnedMovie(new Movie("Little Women","2019","Greta Gerwig","10"));
        testBiblioteca.addOwnedMovie(new Movie("Lilo and Stitch","2002","Alan Silvestri(not)","unrated"));
        testBiblioteca.addOwnedMovie(new Movie("National Treasure","2004","Jon Turtletaub","10"));

        testBiblioteca.addNewLibraryGuest(new User("Jack","jack@jack.com","867-5309","000-0000",true));
        testBiblioteca.addNewLibraryGuest(new User("Jill","jill@jack.com","867-0000","000-0001",false));
        testBiblioteca.addNewLibraryGuest(new User("John","john@jack.com","867-1111","123-4567",false));

    }

    @Test
    public void checkOutShouldRemoveBookFromList(){
        testBiblioteca.checkOutBook("Little Women","");
        for(Book book: testBiblioteca.availableBooks.values())
            assertNotEquals(book.toString(),"Little Women");
    }


    @Test
    public void checkOutShouldGiveConfirmation(){
        assertTrue(testBiblioteca.checkOutBook("Little Women",""));
    }

    @Test
    public void checkOutShouldReturnFalseIfBookNotAvailable(){
        assertFalse(testBiblioteca.checkOutBook("Not a book",""));
    }

    //@Test
    //public void checkOutShouldAssignBookToUser(){
     //   testBiblioteca.checkOutBook("Little Women","123-4567");
      //  assertTrue(testBiblioteca.checkedOutBooks.get("Little Women").checkedOutTo.equals("123-4567"));
    //}

    @Test
    public void myPhoneShouldBe8675309(){
        User me = testBiblioteca.getUserByID("000-0000");
        assertTrue(me.phoneNumber.equals("867-5309"));
    }

    @Test
    public void myInfoShouldBeRight(){
        User me = testBiblioteca.getUserByID(("000-0000"));
        assertTrue(me.getUserInfo().equals("Jack | jack@jack.com | 867-5309"));
    }
}
