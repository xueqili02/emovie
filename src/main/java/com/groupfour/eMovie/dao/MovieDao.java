package com.groupfour.eMovie.dao;

import com.groupfour.eMovie.entity.Link;
import com.groupfour.eMovie.entity.Movie;
//import com.groupfour.eMovie.entity.RecommendHybrid;
//import com.groupfour.eMovie.entity.RecommendOverview;
import com.groupfour.eMovie.entity.RecommendHybrid;
import com.groupfour.eMovie.entity.RecommendOverview;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MovieDao {

    @Select("SELECT * FROM movies")
    List<Movie> getMovies();

    @Select("SELECT * FROM movies WHERE id = #{id}")
    Movie getMovieById(@Param("id") int id);

    @Select("SELECT * FROM movies WHERE originalTitle like #{originalTitle} LIMIT 16")
    List<Movie> getMovieByOriginalTitle(@Param("originalTitle") String originalTitle);

    @Select("SELECT * FROM movies ORDER BY popularity DESC")
    List<Movie> getMovieByPopularityOrdered();

    @Insert("INSERT INTO movies(budget, originalLanguage, originalTitle, popularity, releaseDate, " +
            "revenue, runtime, title, voteAverage, voteCount, overview) " +
            "VALUES(#{budget}, #{originalLanguage}, #{originalTitle}, " +
            "#{popularity}, #{releaseDate}, #{revenue}, #{runtime}, " +
            "#{title}, #{voteAverage}, #{voteCount}, #{overview})")
    @Options(keyColumn = "id", keyProperty = "id", useGeneratedKeys = true)
    Integer insertMovie(Movie movie);

    @Select("SELECT * FROM movies WHERE originalTitle = 'Spider-Man 2' union all " +
            "SELECT * FROM movies WHERE originalTitle = 'The Shawshank Redemption' union all " +
            "SELECT * FROM movies WHERE originalTitle = 'Forrest Gump' union all " +
            "SELECT * FROM movies WHERE originalTitle = 'Avatar'")
    List<Movie> getHotMovie();

    @Select("SELECT * FROM recommend_overview WHERE movieid = #{id}")
    List<RecommendOverview> getMovieRecommendOverviewById(@Param("id") int id);

    @Select("SELECT * FROM recommend_hybrid WHERE movieid = #{id}")
    List<RecommendHybrid> getMovieRecommendHybridById(@Param("id") int id);

    @Select("SELECT * FROM links WHERE tmdbid = #{id}")
    Link getMovieLink(@Param("id")int id);

    @Select("SELECT * FROM movies WHERE id = (SELECT tmdbid FROM links WHERE id = #{unusedId})")
    Movie getMovieByUnusedId(@Param("unusedId") int unusedId);

    @Update("UPDATE movies SET budget=#{budget}, originalLanguage=#{originalLanguage}, originalTitle=#{originalTitle}, " +
            "popularity=#{popularity}, releaseDate=#{releaseDate}, revenue=#{revenue}, runtime=#{runtime}, " +
            "title=#{title}, voteAverage=#{voteAverage}, voteCount=#{voteCount}, overview=#{overview} " +
            "WHERE id=#{id}")
    void updateMovie(Movie movie);
}
