package com.groupfour.eMovie.entity;

public class MovieGenre {
    private int movieid;
    private int genreid;

    public MovieGenre(int movieid, int genreid) {
        this.movieid = movieid;
        this.genreid = genreid;
    }

    public int getMovieid() {
        return movieid;
    }

    public void setMovieid(int movieid) {
        this.movieid = movieid;
    }

    public int getGenreid() {
        return genreid;
    }

    public void setGenreid(int genreid) {
        this.genreid = genreid;
    }
}
