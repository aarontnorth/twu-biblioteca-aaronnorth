package com.twu.biblioteca;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BookTest {

    @Test
    public void booksShouldHaveAuthorAndYear(){
        Book myBook = new Book("Hello","John","1965");
        assertEquals(myBook.getBookInfo(),"Hello | John | 1965");
    }


}
