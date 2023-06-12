package com.groupfour.eMovie.service.impl;

import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;
import com.groupfour.eMovie.dao.*;
import com.groupfour.eMovie.entity.*;
import com.groupfour.eMovie.service.MovieService;
import com.groupfour.eMovie.utils.RunPy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
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

//    public List<Movie> getMovieByFuzzyQuery(String originalTitle) {
////        try {
////            Indexer indexer = new Indexer();
////            indexer.indexAdd(movieDao, "src/main/java/com/groupfour/eMovie/utils/lucene/data");
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
//        return movieDao.getMovieByOriginalTitle("%" + originalTitle + "%");
//    }

    public List<Movie> getMovieByOriginalTitle(String originalTitle) {
        return movieDao.getMovieByOriginalTitle("%" + originalTitle + "%");
    }

//    public List<Movie> getHotMovie() {
//        return movieDao.getHotMovie();
//    }

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

//    public List<Movie> getMovieRecommendById(int id) {
//        List<RecommendOverview> movieOverviewList = movieDao.getMovieRecommendOverviewById(id);
//        List<RecommendHybrid> movieHybridList = movieDao.getMovieRecommendHybridById(id);
//        List<Movie> movieList = new ArrayList<>();
//
//        if (movieHybridList.size() != 0) {
//            movieList.add(movieDao.getMovieById(movieHybridList.get(0).getMovie1()));
//            movieList.add(movieDao.getMovieById(movieHybridList.get(0).getMovie2()));
//            movieList.add(movieDao.getMovieById(movieHybridList.get(0).getMovie3()));
//            movieList.add(movieDao.getMovieById(movieHybridList.get(0).getMovie4()));
//        }
//
//        if (movieOverviewList.size() != 0) {
//            movieList.add(movieDao.getMovieById(movieOverviewList.get(0).getMovie9()));
//            movieList.add(movieDao.getMovieById(movieOverviewList.get(0).getMovie8()));
//            movieList.add(movieDao.getMovieById(movieOverviewList.get(0).getMovie7()));
//            movieList.add(movieDao.getMovieById(movieOverviewList.get(0).getMovie6()));
//        }
//
//        return movieList;
//    }

    public Link getMovieLink(int id){
        Link link = movieDao.getMovieLink(id);
        return link;
    }

//    public List<Movie> getRecommendByRating(int uid) {
//        List<Rating> userRatingList = ratingDao.getLinkByUid(uid);
//        List<RatingRecommend> ratingRecommendList = new ArrayList<>();
//
//        for (Rating r: userRatingList) {
//            ratingRecommendList.add(new RatingRecommend(r));
//        }
//
//        Gson gson = new Gson();
//        String json = gson.toJson(ratingRecommendList).replace("\"", "'");
//        List<Integer> movieIdList = RunPy.getRecommendIdByPy(json);
//
//        List<Movie> movieList = new ArrayList<>();
//        for (Integer id: movieIdList) {
//            movieList.add(movieDao.getMovieByUnusedId(id));
//        }
//
//        return movieList;
//    }
}
