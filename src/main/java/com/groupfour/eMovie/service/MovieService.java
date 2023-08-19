package com.groupfour.eMovie.service;

import com.groupfour.eMovie.entity.Link;
import com.groupfour.eMovie.entity.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> getMovies(int pageNum);

    List<Movie> getMovieByOriginalTitle(String originalTitle);

    List<Movie> getHotMovie();

    Movie getMovieById(int id);

    Movie insertMovie(Movie movie);

    List<Movie> getMovieByPopularityOrdered(int pageNum);

    List<Movie> getMovieByGenreId(int genreid, int pageNum);

    Movie setMovieGenreAndKeyword(Movie movie);

    List<Movie> getMovieRecommendById(int id);

    Link getMovieLink(int id);

    List<Movie> getRecommendByRating(int uid);

    int updateMovie(Movie movie);
}
