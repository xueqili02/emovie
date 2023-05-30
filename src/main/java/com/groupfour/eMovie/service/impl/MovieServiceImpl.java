package com.groupfour.eMovie.service.impl;

import com.github.pagehelper.PageHelper;
import com.groupfour.eMovie.dao.MovieDao;
import com.groupfour.eMovie.entity.Movie;
import com.groupfour.eMovie.service.MovieService;
import com.groupfour.eMovie.utils.lucene.Indexer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("MovieServiceImpl")
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieDao movieDao;

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
        return movieDao.getMovieById(id);
    }

    public Movie insertMovie(Movie movie) {
        Movie newMovie = new Movie(movie);
        int id = movieDao.insertMovie(movie);
        newMovie.setId(id);
        return newMovie;
    }

    public List<Movie> getMovieByPopularityOrdered() {
        return movieDao.getMovieByPopularityOrdered();
    }
}
