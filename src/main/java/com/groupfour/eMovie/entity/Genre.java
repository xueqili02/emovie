package com.groupfour.eMovie.entity;

import java.util.Objects;

public class Genre {
    private int id;
    private String genre;

    public Genre(int id, String genre) {
        this.id = id;
        this.genre = genre;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.genre);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        Genre genre = (Genre) obj;
        return genre.getId() == this.id &&
                Objects.equals(genre.getGenre(), this.genre);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
