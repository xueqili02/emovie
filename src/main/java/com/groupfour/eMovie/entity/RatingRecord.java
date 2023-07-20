package com.groupfour.eMovie.entity;

import java.util.Objects;

public class RatingRecord {
    private String originalTitle;
    private float rating;

    public RatingRecord(String originalTitle, float rating) {
        this.originalTitle = originalTitle;
        this.rating = rating;
    }
    @Override
    public int hashCode() {
        return Objects.hash(this.originalTitle, this.rating);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        RatingRecord record = (RatingRecord) obj;
        return Objects.equals(record.getOriginalTitle(), this.originalTitle)
                && record.getRating() == this.rating;
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
