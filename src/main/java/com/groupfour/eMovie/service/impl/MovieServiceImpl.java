package com.groupfour.eMovie.service.impl;

import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;
import com.groupfour.eMovie.dao.*;
import com.groupfour.eMovie.entity.*;
import com.groupfour.eMovie.service.MovieService;
import com.groupfour.eMovie.utils.JsonUtils;
import com.groupfour.eMovie.utils.RunPy;
import com.groupfour.eMovie.utils.lucene.Indexer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.groupfour.eMovie.utils.ProjectConstants.*;


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

    /*
    * optimize query time from 74ms to 5ms
    * resolve 缓存穿透 problem
    * */
    public Movie getMovieById(int id) {
        // cache penetration
//        return getMovieByIdWithCachePenetration(id);

        // hotspot invalid mutex
        return getMovieByIdWithHotspotInvalidMutex(id);

        // hotspot invalid logical expiration
//        return getMovieByIdWithHotspotInvalidLogicalExpiration(id);
    }

    private Movie getMovieByIdWithCachePenetration(int id) {
        String key = REDIS_MOVIE_KEY_PREFIX + id;
        // 从redis查询缓存
        String movieJson = stringRedisTemplate.opsForValue().get(key);
        // 命中，直接返回 ---> 命中，判断查到的是否为空，不是空则返回
        if (movieJson != null) { // 查到缓存
            if (!movieJson.equals("")) { // 缓存不是空
                return JsonUtils.fromJson(movieJson, Movie.class);
            } else { // 缓存是空值，返回null，防止缓存穿透
                return null;
            }
        }
        // 未命中，查询数据库
        Movie movie = movieDao.getMovieById(id);
        // 数据库未查询到，返回null ----> 数据库未查询到，将空值写入redis
        if (movie == null) {
            stringRedisTemplate.opsForValue().set(key, "", REDIS_NULL_TTL, TimeUnit.MINUTES);
            return null;
        }
        // 数据库查询到，写入redis
        stringRedisTemplate.opsForValue().set(key, JsonUtils.toJson(movie), REDIS_MOVIE_TTL, TimeUnit.MINUTES);
        // 返回数据
        return setMovieGenreAndKeyword(movie);
//        return setMovieGenreAndKeyword(movieDao.getMovieById(id));
    }

    /*
    * 互斥锁解决缓存击穿
    * */
    private Movie getMovieByIdWithHotspotInvalidMutex(int id) {
        String key = REDIS_MOVIE_KEY_PREFIX + id;
        // 从redis查询缓存
        String movieJson = stringRedisTemplate.opsForValue().get(key);
        // 命中，直接返回 ---> 命中，判断查到的是否为空，不是空则返回
        if (movieJson != null) { // 查到缓存
            if (!movieJson.equals("")) { // 缓存不是空
                return JsonUtils.fromJson(movieJson, Movie.class);
            } else { // 缓存是空值，返回null，防止缓存穿透
                return null;
            }
        }
        // 实现缓存重建
        // 尝试获取互斥锁
        String lockKey = REDIS_LOCK_KEY_PREFIX + id;
        Movie movie = null;
        try {
            // 不成功，休眠并重试
            if (!tryLock(lockKey)) {
                Thread.sleep(50);
                getMovieByIdWithHotspotInvalidMutex(id);
            }
            // 成功，根据id查询数据库
            movie = movieDao.getMovieById(id);
            // 数据库未查询到，返回null ----> 数据库未查询到，将空值写入redis
            if (movie == null) {
                stringRedisTemplate.opsForValue().set(key, "", REDIS_NULL_TTL, TimeUnit.MINUTES);
                return null;
            }
            // 数据库查询到，写入redis
            stringRedisTemplate.opsForValue().set(key, JsonUtils.toJson(movie), REDIS_MOVIE_TTL, TimeUnit.MINUTES);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            // 释放互斥锁
            unlock(lockKey);
        }
        // 返回数据
        return setMovieGenreAndKeyword(movie);
    }

    private boolean tryLock(String key) {
        Boolean flag = stringRedisTemplate.opsForValue().setIfAbsent(key, "1", REDIS_LOCK_TTL, TimeUnit.SECONDS);
        if (flag == null) {
            return false;
        }
        return flag;
    }

    private void unlock(String key) {
        stringRedisTemplate.delete(key);
    }

    private Movie getMovieByIdWithHotspotInvalidLogicalExpiration(int id) {
        // TODO
        return null;
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

    public List<Movie> getMovieRecommendById(int id) {
        List<RecommendOverview> movieOverviewList = movieDao.getMovieRecommendOverviewById(id);
        List<RecommendHybrid> movieHybridList = movieDao.getMovieRecommendHybridById(id);
        List<Movie> movieList = new ArrayList<>();

        if (movieHybridList.size() != 0) {
            movieList.add(movieDao.getMovieById(movieHybridList.get(0).getMovie1()));
            movieList.add(movieDao.getMovieById(movieHybridList.get(0).getMovie2()));
            movieList.add(movieDao.getMovieById(movieHybridList.get(0).getMovie3()));
            movieList.add(movieDao.getMovieById(movieHybridList.get(0).getMovie4()));
        }

        if (movieOverviewList.size() != 0) {
            movieList.add(movieDao.getMovieById(movieOverviewList.get(0).getMovie9()));
            movieList.add(movieDao.getMovieById(movieOverviewList.get(0).getMovie8()));
            movieList.add(movieDao.getMovieById(movieOverviewList.get(0).getMovie7()));
            movieList.add(movieDao.getMovieById(movieOverviewList.get(0).getMovie6()));
        }

        return movieList;
    }

    public List<Movie> getRecommendByRating(int uid) {
        List<Rating> userRatingList = ratingDao.getLinkByUid(uid);
        List<RatingRecommend> ratingRecommendList = new ArrayList<>();

        for (Rating r: userRatingList) {
            ratingRecommendList.add(new RatingRecommend(r));
        }

        Gson gson = new Gson();
        String json = gson.toJson(ratingRecommendList).replace("\"", "'");
        List<Integer> movieIdList = RunPy.getRecommendIdByPy(json);

        List<Movie> movieList = new ArrayList<>();
        for (Integer id: movieIdList) {
            movieList.add(movieDao.getMovieByUnusedId(id));
        }

        return movieList;
    }

    @Transactional  // 事务，同步成功或同步失败
    public int updateMovie(Movie movie) {
        // 更新操作
        Integer id = movie.getId();
        if (id == null) {
            return FAILURE;
        }
        // 先更新数据库
        movieDao.updateMovie(movie);
        // 删除缓存
        stringRedisTemplate.delete(REDIS_MOVIE_KEY_PREFIX + id);
        return SUCCESS;
    }
}
