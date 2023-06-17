package com.groupfour.eMovie.entity;

import java.util.List;
import java.util.Objects;

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

    private List<String> genre;
    private List<String> keyword;

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

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.budget, this.originalLanguage,
                this.originalTitle, this.popularity, this.releaseDate, this.revenue,
                this.runtime, this.title, this.voteAverage, this.voteCount, this.overview,
                this.genre, this.keyword);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        Movie movie = (Movie) obj;
        return movie.getId() == this.id &&
                movie.getBudget() == this.budget &&
                Objects.equals(movie.getOriginalLanguage(), this.originalLanguage) &&
                Objects.equals(movie.getOriginalTitle(), this.originalTitle) &&
                movie.getPopularity() == this.popularity &&
                Objects.equals(movie.getReleaseDate(), this.releaseDate) &&
                movie.getRevenue() == this.revenue &&
                movie.getRuntime() == this.runtime &&
                Objects.equals(movie.getTitle(), this.title) &&
                movie.getVoteAverage() == this.voteAverage &&
                movie.getVoteCount() == this.voteCount &&
                Objects.equals(movie.getOverview(), this.overview) &&
                Objects.equals(movie.getGenre(), this.genre) &&
                Objects.equals(movie.getKeyword(), this.keyword);
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

    public List<String> getGenre() {
        return genre;
    }

    public void setGenre(List<String> genre) {
        this.genre = genre;
    }

    public List<String> getKeyword() {
        return keyword;
    }

    public void setKeyword(List<String> keyword) {
        this.keyword = keyword;
    }
}
