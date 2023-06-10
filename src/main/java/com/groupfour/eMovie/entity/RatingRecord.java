package com.groupfour.eMovie.entity;

public class RatingRecord {
    private String originalTitle;
    private float rating;

    public RatingRecord(String originalTitle, float rating) {
        this.originalTitle = originalTitle;
        this.rating = rating;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
