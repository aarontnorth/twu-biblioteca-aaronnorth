package com.twu.biblioteca;

public class Movie {
    String title;
    String year;
    String director;
    String rating;

    Movie(String title,String year,String director,String rating){
        this.title = title;
        this.year = year;
        this.director = director;
        this.rating = rating;
    }

    String getMovieInfo(){
        return title + " | " + year + " | " + director + " | " + rating;
    }


}
