package com.groupfour.eMovie.entity;

public class Rating {
    private int uid;
    private int movieid;
    private float rating;

    public Rating(int uid, int movieid, float rating) {
        this.uid = uid;
        this.movieid = movieid;
        this.rating = rating;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getMovieid() {
        return movieid;
    }

    public void setMovieid(int movieid) {
        this.movieid = movieid;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
