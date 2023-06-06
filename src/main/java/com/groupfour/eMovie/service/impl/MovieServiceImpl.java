package com.groupfour.eMovie.service.impl;

import com.github.pagehelper.PageHelper;
import com.groupfour.eMovie.dao.*;
import com.groupfour.eMovie.entity.Movie;
import com.groupfour.eMovie.entity.MovieGenre;
import com.groupfour.eMovie.entity.MovieKeyword;
import com.groupfour.eMovie.service.MovieService;
import com.groupfour.eMovie.utils.lucene.Indexer;
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

    public List<Movie> getMovies(int pageNum) {
        int pageSize = 16;
        PageHelper.startPage(pageNum, pageSize);
        return movieDao.getMovies();
    }

    public List<Movie> getMovieByOriginalTitle(String originalTitle) {
//        try {
//            Indexer indexer = new Indexer();
//            indexer.indexAdd(movieDao, "src/main/java/com/groupfour/eMovie/utils/lucene/data");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return movieDao.getMovieByOriginalTitle("%" + originalTitle + "%");
    }

    public List<Movie> getHotMovie() {
        return movieDao.getHotMovie();
    }

    public Movie getMovieById(int id) {
        List<Movie> tempList = new ArrayList<>();
        tempList.add(movieDao.getMovieById(id));
        return setMovieGenreAndKeyword(tempList).get(0);
    }

    public Movie insertMovie(Movie movie) {
        Movie newMovie = new Movie(movie);
        int id = movieDao.insertMovie(movie);
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

    public List<Movie> setMovieGenreAndKeyword(List<Movie> movieList) {
        for (Movie movie: movieList) {
            List<MovieGenre> movieGenres = movieGenreDao.getMovieGenreByMovieId(movie.getId()); // 1 sql
            List<String> movieGenresString = new ArrayList<>();
            for (MovieGenre mg: movieGenres) {
                movieGenresString.add(genreDao.getGenreById(mg.getGenreid()).getGenre()); // multiple sql
            }
            movie.setGenre(movieGenresString);

            List<MovieKeyword> movieKeywords = movieKeywordDao.getMovieKeywordByMovieId(movie.getId());
            List<String> movieKeywordsString = new ArrayList<>();
            for (MovieKeyword mk: movieKeywords) {
                movieKeywordsString.add(keywordDao.getKeywordById(mk.getKeywordid()).getKeyword());
            }
            movie.setKeyword(movieKeywordsString);
        }

        return movieList;
    }
}
