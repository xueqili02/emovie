package com.groupfour.eMovie.service.impl;

import com.github.pagehelper.PageHelper;
import com.groupfour.eMovie.dao.MovieDao;
import com.groupfour.eMovie.entity.Movie;
import com.groupfour.eMovie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("MovieServiceImpl")
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieDao movieDao;

    public List<Movie> getMovies(int pageNum) {
        int pageSize = 10;
        PageHelper.startPage(pageNum, pageSize);
        return movieDao.getMovies();
    }
}
