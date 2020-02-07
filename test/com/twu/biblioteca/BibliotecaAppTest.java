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

public class BibliotecaAppTest {

    static BibliotecaApp testBibliotecaApp;

    @Test(expected = BibliotecaApp.IncorrectOptionException.class)
    public void invalidUserInputShouldThrowErrorMessage()throws BibliotecaApp.IncorrectOptionException, BibliotecaApp.quitException {
        BibliotecaApp.pickMenuOption("This is invalid");
    }
    @Test(expected = BibliotecaApp.quitException.class)
    public void userInputqShouldQuit()throws BibliotecaApp.IncorrectOptionException, BibliotecaApp.quitException {
        BibliotecaApp.pickMenuOption("q");
    }

    @Test(expected=BibliotecaApp.BookUnavailableException.class)
    public void finishCheckOutShouldThrowErrorIfBookUnavailable() throws BibliotecaApp.BookUnavailableException {
        BibliotecaApp.finishCheckOut(false);
    }

}
