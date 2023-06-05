package com.groupfour.eMovie.entity;

public class MovieKeyword {
    private int movieid;
    private int keywordid;

    public MovieKeyword(int movieid, int keywordid) {
        this.movieid = movieid;
        this.keywordid = keywordid;
    }

    public int getMovieid() {
        return movieid;
    }

    public void setMovieid(int movieid) {
        this.movieid = movieid;
    }

    public int getKeywordid() {
        return keywordid;
    }

    public void setKeywordid(int keywordid) {
        this.keywordid = keywordid;
    }
}
