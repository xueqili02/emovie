package com.groupfour.eMovie.service;

import com.groupfour.eMovie.entity.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> getGenres();

    Genre getGenreById(int genreid);
}
