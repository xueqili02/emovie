package com.groupfour.eMovie.entity;

import java.util.Objects;

public class Rating {
    private int uid;
    private int movieid;
    private float rating;

    public Rating() {

    }

    public Rating(int uid, int movieid, float rating) {
        this.uid = uid;
        this.movieid = movieid;
        this.rating = rating;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.uid, this.movieid, this.rating);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        Rating rating = (Rating) obj;
        return rating.getUid() == this.uid
                && rating.getMovieid() == this.movieid
                && rating.getRating() == this.rating;
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
