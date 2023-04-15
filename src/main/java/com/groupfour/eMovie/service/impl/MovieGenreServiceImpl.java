package com.groupfour.eMovie.service.impl;

import com.groupfour.eMovie.dao.MovieGenreDao;
import com.groupfour.eMovie.entity.MovieGenre;
import com.groupfour.eMovie.service.MovieGenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("MovieGenreServiceImpl")
public class MovieGenreServiceImpl implements MovieGenreService {

    @Autowired
    private MovieGenreDao movieGenreDao;

    public List<MovieGenre> getMovieGenreByMovieId(int movieid) {
        return movieGenreDao.getMovieGenreByMovieId(movieid);
    }
}
