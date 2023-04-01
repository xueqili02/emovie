package com.groupfour.eMovie.service;

import com.groupfour.eMovie.entity.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> getMovies(int pageNum);

    List<Movie> getMovieByOriginalTitle(String originalTitle);
}
