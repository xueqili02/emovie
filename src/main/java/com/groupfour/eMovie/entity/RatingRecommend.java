package com.groupfour.eMovie.entity;

public class RatingRecommend {
    private int id;
    private int rating;

    public RatingRecommend(Rating rating) {
        this.id = rating.getMovieid();
        this.rating = rating.getRating();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
