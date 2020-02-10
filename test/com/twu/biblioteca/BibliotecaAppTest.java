package com.twu.biblioteca;


import org.junit.Test;

public class BibliotecaAppTest {

    //static BibliotecaApp testBibliotecaApp;

    @Test(expected = BibliotecaApp.IncorrectOptionException.class)
    public void invalidUserInputShouldThrowErrorMessage()throws BibliotecaApp.IncorrectOptionException, BibliotecaApp.quitException {
        BibliotecaApp.pickMenuOption("This is invalid");
    }
    @Test(expected = BibliotecaApp.quitException.class)
    public void userInputqShouldQuit()throws BibliotecaApp.IncorrectOptionException, BibliotecaApp.quitException {
        BibliotecaApp.pickMenuOption("q");
    }

    @Test(expected= BibliotecaApp.UnavailableException.class)
    public void finishCheckOutShouldThrowErrorIfBookUnavailable() throws BibliotecaApp.UnavailableException {
        BibliotecaApp.finishCheckOut(false,"book");
    }

    @Test(expected=BibliotecaApp.BookDoesNotBelongException.class)
    public void checkInShouldThrowErrorIfBookDoesNotBelongToLibrary() throws BibliotecaApp.BookDoesNotBelongException {
        BibliotecaApp.finishCheckIn(false);
    }

    @Test(expected=BibliotecaApp.UnavailableException.class)
    public void finishCheckOutShouldThrowErrorIfMovieUnavailable() throws  BibliotecaApp.UnavailableException {
        BibliotecaApp.finishCheckOut(false,"movie");
    }

}
