package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BibliotecaTest {

    static Biblioteca testBiblioteca;

    @Before
    public void init(){
        testBiblioteca = new Biblioteca();
        testBiblioteca.availableBooks = BibliotecaApp.setUpAvailableBooks();
    }

    @Test
    public void checkOutShouldRemoveBookFromList(){
        testBiblioteca.checkOutBook("Little Women");
        for(Book book: testBiblioteca.availableBooks.values())
            assertNotEquals(book.toString(),"Little Women");
    }


    @Test
    public void checkOutShouldGiveConfirmation(){
        assertTrue(testBiblioteca.checkOutBook("Little Women"));
    }

    @Test
    public void checkOutShouldReturnFalseIfBookNotAvailable(){
        assertFalse(testBiblioteca.checkOutBook("Not a book"));
    }


    @Test
    public void checkInShouldUpdateBookList(){
        testBiblioteca.checkIn("The Road");
        assertTrue(testBiblioteca.availableBooks.containsKey("The Road"));
    }


}
