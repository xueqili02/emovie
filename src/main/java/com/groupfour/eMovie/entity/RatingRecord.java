package com.groupfour.eMovie.entity;

public class RatingRecord {
    private String originalTitle;
    private int rating;

    public RatingRecord(String originalTitle, int rating) {
        this.originalTitle = originalTitle;
        this.rating = rating;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
