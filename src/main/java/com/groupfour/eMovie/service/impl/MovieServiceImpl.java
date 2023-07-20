package com.groupfour.eMovie.service.impl;

import com.github.pagehelper.PageHelper;
import com.groupfour.eMovie.dao.*;
import com.groupfour.eMovie.entity.*;
import com.groupfour.eMovie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("MovieServiceImpl")
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieDao movieDao;

    @Autowired
    private MovieGenreDao movieGenreDao;

    @Autowired
    private GenreDao genreDao;

    @Autowired
    private MovieKeywordDao movieKeywordDao;

    @Autowired
    private KeywordDao keywordDao;

    @Autowired
    private RatingDao ratingDao;

    public List<Movie> getMovies(int pageNum) {
        int pageSize = 16;
        PageHelper.startPage(pageNum, pageSize);
        return movieDao.getMovies();
    }

    public List<Movie> getMovieByOriginalTitle(String originalTitle) {
        return movieDao.getMovieByOriginalTitle("%" + originalTitle + "%");
    }

    public Movie getMovieById(int id) {
        return setMovieGenreAndKeyword(movieDao.getMovieById(id));
    }

    public Movie insertMovie(Movie movie) {
        Movie newMovie = new Movie(movie);
        movieDao.insertMovie(movie);
        int id = movie.getId();
        newMovie.setId(id);
        return newMovie;
    }

    public List<Movie> getMovieByPopularityOrdered(int pageNum) {
        int pageSize = 16;
        PageHelper.startPage(pageNum, pageSize);
        return movieDao.getMovieByPopularityOrdered();
    }

    public List<Movie> getMovieByGenreId(int genreid, int pageNum) {
        List<MovieGenre> movieGenreList = movieGenreDao.getMovieByGenreId(genreid);
        List<Movie> movieList = new ArrayList<>();

        int pageSize = 16;
        int startIndex = (pageNum - 1) * pageSize;

        for (int i = startIndex; i < startIndex + pageSize && i < movieGenreList.size(); i++) {
            movieList.add(movieDao.getMovieById(movieGenreList.get(i).getMovieid()));
        }
        return movieList;
    }

    public Movie setMovieGenreAndKeyword(Movie movie) {
        List<MovieGenre> movieGenres = movieGenreDao.getMovieGenreByMovieId(movie.getId()); // 1 sql
        List<String> movieGenresString = new ArrayList<>();
        for (MovieGenre mg : movieGenres) {
            movieGenresString.add(genreDao.getGenreById(mg.getGenreid()).getGenre()); // multiple sql
        }
        movie.setGenre(movieGenresString);

        List<MovieKeyword> movieKeywords = movieKeywordDao.getMovieKeywordByMovieId(movie.getId());
        List<String> movieKeywordsString = new ArrayList<>();
        for (MovieKeyword mk : movieKeywords) {
            movieKeywordsString.add(keywordDao.getKeywordById(mk.getKeywordid()).getKeyword());
        }
        movie.setKeyword(movieKeywordsString);

        return movie;
    }

    public Link getMovieLink(int id){
        Link link = movieDao.getMovieLink(id);
        return link;
    }
}
