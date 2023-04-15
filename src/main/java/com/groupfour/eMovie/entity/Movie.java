package com.groupfour.eMovie.entity;

public class Movie {
    private int id;
    private int budget;
    private String originalLanguage;
    private String originalTitle;
    private float popularity;
    private String releaseDate;
    private long revenue;
    private int runtime;
    private String title;
    private float voteAverage;
    private int voteCount;
    private String overview;

    public Movie() {}

    public Movie(Movie movie) {
        id = movie.getId();
        budget = movie.getBudget();
        originalLanguage = movie.getOriginalLanguage();
        originalTitle = movie.getOriginalTitle();
        popularity = movie.getPopularity();
        releaseDate = movie.getReleaseDate();
        revenue = movie.getRevenue();
        runtime = movie.getRuntime();
        title = movie.getTitle();
        voteAverage = movie.getVoteAverage();
        voteCount = movie.getVoteCount();
        overview = movie.getOverview();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public float getPopularity() {
        return popularity;
    }

    public void setPopularity(float popularity) {
        this.popularity = popularity;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Long getRevenue() {
        return revenue;
    }

    public void setRevenue(long revenue) {
        this.revenue = revenue;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(float voteAverage) {
        this.voteAverage = voteAverage;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }
}
