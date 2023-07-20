package com.groupfour.eMovie.service;

import com.groupfour.eMovie.dao.*;
import com.groupfour.eMovie.entity.Movie;
import com.groupfour.eMovie.entity.MovieGenre;
import com.groupfour.eMovie.entity.MovieKeyword;
import com.groupfour.eMovie.service.impl.MovieServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
@Transactional
public class MovieServiceTest {
    @Autowired
    private MovieDao movieDao;

    @Mock
    private Movie movie;

    @Autowired
    private MovieServiceImpl movieService;

    @Autowired
    private MovieGenreDao movieGenreDao;

    @Autowired
    private GenreDao genreDao;

    @Autowired
    private MovieKeywordDao movieKeywordDao;

    @Autowired
    private KeywordDao keywordDao;

    @BeforeEach()
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

//    @Test
//    public void callGetMovies() {
//        int[] pageNums = {1, 23, 456};
//        for (int pageNum : pageNums) {
//            List<Movie> list = movieService.getMovies(pageNum);
//            assertEquals(list, movieDao.getMovies());
//        }
//    }

    @Test
    public void callGetMovieByOriginalTitle() {
        String[] titles = {"The Shawshank Redemption", "123", "8 Mile"};
        for (String title : titles) {
            List<Movie> list = movieService.getMovieByOriginalTitle(title);
            assertEquals(list, movieDao.getMovieByOriginalTitle("%" + title + "%"));
        }
    }

    @Test
    public void callGetMovieById() {
        int[] ids = {2, 106};
        for (int i : ids) {
            movie = new Movie();
            movie = movieService.getMovieById(i);
            assertEquals(movie, movieService.setMovieGenreAndKeyword(movieDao.getMovieById(i)));
        }
    }

//    @Test
//    public void callInsertMovie() {
//        Movie movie = new Movie();
//        movie.setOriginalLanguage("en");
//        movie.setBudget(1145114);
//        movie.setOriginalTitle("Titan Fall");
//        movie.setOverview("Protocol III: protect pilot. Cooper, trust me!");
//        assertEquals(movieService.insertMovie(movie), movieDao.getMovieById(movie.getId()));
//    }

//    @Test
//    public void callGetMovieByPopularityOrdered() {
//        int[] pageNums = {1, 23, 456};
//        for (int pageNum : pageNums) {
//            List<Movie>  list;
//        }
//    }

    @Test
    public void callGetMovieByGenreId() {
        int[] genreIds = {12, 14};
        int[] pageNums = {2, 4};
        int pageSize = 16;
        for (int i = 0; i<genreIds.length; i++) {
            List<MovieGenre> genreList = movieGenreDao.getMovieByGenreId(genreIds[i]);
            List<Movie> movieList = new ArrayList<>();
            int startIndex = (pageNums[i] - 1) * pageSize;

            for (int j = startIndex; j < startIndex + pageSize && j <genreList.size(); j++) {
                movieList.add(movieDao.getMovieById(genreList.get(j).getMovieid()));
            }
            assertEquals(movieList, movieService.getMovieByGenreId(genreIds[i], pageNums[i]));
        }
    }

    @Test
    public void callInsertMovieAndSetMovieGenreAndKeyword() {
        Movie movie = new Movie();
        movie.setOriginalLanguage("en");
        movie.setBudget(1145114);
        movie.setOriginalTitle("Titan Fall");
        movie.setOverview("Protocol III: protect pilot. Cooper, trust me!");
        movieService.insertMovie(movie);
        assertEquals(movieService.insertMovie(movie), movieDao.getMovieById(movie.getId()));

        List<MovieGenre> genres = movieGenreDao.getMovieGenreByMovieId(movie.getId());
        List<String> genreStr = new ArrayList<>();
        for (MovieGenre mg : genres) {
            genreStr.add(genreDao.getGenreById(mg.getGenreid()).getGenre());
        }
        movie.setGenre(genreStr);

        List<MovieKeyword> keywords = movieKeywordDao.getMovieKeywordByMovieId(movie.getId());
        List<String> keywordStr = new ArrayList<>();
        for (MovieKeyword mk : keywords) {
            keywordStr.add(keywordDao.getKeywordById(mk.getKeywordid()).getKeyword());
        }
        movie.setKeyword(keywordStr);

        assertEquals(movie, movieService.setMovieGenreAndKeyword(movie));
    }

    @Test
    public void callGetMovieLink() {
        int[] links = {4470, 479};
        for (int link : links) {
            assertEquals(movieDao.getMovieLink(link), movieService.getMovieLink(link));
        }
    }
}
