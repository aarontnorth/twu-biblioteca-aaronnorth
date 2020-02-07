package com.twu.biblioteca;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;

public class ExampleTest {
    static BibliotecaApp testBibliotecaApp;
    static Biblioteca testBiblioteca;
    static PrintStream userInput;

    @Before
    public void init(){
        testBiblioteca = new Biblioteca();
        testBiblioteca.availableBooks = BibliotecaApp.setUpAvailableBooks();
    }

    @Test
    public void booksShouldHaveAuthorAndYear(){
        Book myBook = new Book("Hello","John","1965");
        assertEquals(myBook.getBookInfo(),"Hello | John | 1965");
    }

    @Test(expected = BibliotecaApp.IncorrectOptionException.class)
    public void invalidUserInputShouldThrowErrorMessage()throws BibliotecaApp.IncorrectOptionException, BibliotecaApp.quitException {
        BibliotecaApp.pickMenuOption("This is invalid");
    }
    @Test(expected = BibliotecaApp.quitException.class)
    public void userInputqShouldQuit()throws BibliotecaApp.IncorrectOptionException, BibliotecaApp.quitException {
        BibliotecaApp.pickMenuOption("q");
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

    @Test(expected=BibliotecaApp.BookUnavailableException.class)
    public void finishCheckOutShouldThrowErrorIfBookUnavailable() throws BibliotecaApp.BookUnavailableException {
        BibliotecaApp.finishCheckOut(false);
    }

}
