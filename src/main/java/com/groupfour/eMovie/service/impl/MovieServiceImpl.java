package com.groupfour.eMovie.service.impl;

import com.github.pagehelper.PageHelper;
import com.groupfour.eMovie.dao.*;
import com.groupfour.eMovie.entity.*;
import com.groupfour.eMovie.service.MovieService;
import com.groupfour.eMovie.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.groupfour.eMovie.utils.ProjectConstants.REDIS_MOVIE_KEY_PREFIX;
import static com.groupfour.eMovie.utils.ProjectConstants.REDIS_MOVIE_TTL;


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

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public List<Movie> getMovies(int pageNum) {
        int pageSize = 16;
        PageHelper.startPage(pageNum, pageSize);
        return movieDao.getMovies();
    }

    public List<Movie> getMovieByOriginalTitle(String originalTitle) {
        return movieDao.getMovieByOriginalTitle("%" + originalTitle + "%");
    }

    /*
    * optimize query time from 74ms to 5ms
    * */
    public Movie getMovieById(int id) {
        String key = REDIS_MOVIE_KEY_PREFIX + id;
        // 从redis查询缓存
        String movieJson = stringRedisTemplate.opsForValue().get(key);
        // 命中，直接返回
        if (movieJson != null && !movieJson.equals("")) {
            return JsonUtils.fromJson(movieJson, Movie.class);
        }
        // 未命中，查询数据库
        Movie movie = movieDao.getMovieById(id);
        // 数据库未查询到，返回null
        if (movie == null) {
            return null;
        }
        // 数据库查询到，写入redis
        stringRedisTemplate.opsForValue().set(key, JsonUtils.toJson(movie), REDIS_MOVIE_TTL, TimeUnit.MINUTES);
        // 返回数据
        return setMovieGenreAndKeyword(movie);
//        return setMovieGenreAndKeyword(movieDao.getMovieById(id));
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
