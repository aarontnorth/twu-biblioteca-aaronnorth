package com.twu.biblioteca;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class ExampleTest {
    static ArrayList<Book> availableBooks;
    static PrintStream userInput;

    @Before
    public void init(){
        availableBooks = BibliotecaApp.setUpAvailableBooks();
    }

    @Test
    public void getListOfBooksTest() {
        String bookList = BibliotecaApp.getListOfAvailableBooks();
        assertEquals(bookList,("Great Expectations | Someone Important | 1929 probably\n" +
                "Treasure Island | Robert Louis Stevenson | Like 1850\n" +
                "Little Women | An Icon | 1865 I guess\n"));
    }

    @Test
    public void booksShouldHaveAuthorAndYear(){
        Book myBook = new Book("Hello","John","1965");
        assertEquals(myBook.toString(),"Hello | John | 1965");
    }

    @Test(expected = BibliotecaApp.IncorrectOptionException.class)
    public void invalidUserInputShouldThrowErrorMessage()throws BibliotecaApp.IncorrectOptionException, BibliotecaApp.quitException {
        BibliotecaApp.pickMenuOption("k");
    }
    @Test(expected = BibliotecaApp.quitException.class)
    public void userInputqShouldQuit()throws BibliotecaApp.IncorrectOptionException, BibliotecaApp.quitException {
        BibliotecaApp.pickMenuOption("q");
    }

    @After
    public void redirectStdOut(){
        PrintStream originalOut = System.out;
        System.setOut(originalOut);
    }
}
