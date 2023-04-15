package com.groupfour.eMovie.dao;

import com.groupfour.eMovie.entity.Movie;
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

    @Select("SELECT * FROM movies ORDER BY popularity DESC LIMIT 16")
    List<Movie> getMovieByPopularityOrdered();

    @Insert("INSERT INTO movies(budget, originalLanguage, originalTitle, popularity, releaseDate, " +
            "revenue, runtime, title, voteAverage, voteCount, overview) " +
            "VALUES(#{budget}, #{originalLanguage}, #{originalTitle}, " +
            "#{popularity}, #{releaseDate}, #{revenue}, #{runtime}, " +
            "#{title}, #{voteAverage}, #{voteCount}, #{overview})")
    @Options(keyColumn = "id", keyProperty = "id", useGeneratedKeys = true)
    Integer insertMovie(Movie movie);

    @Select("SELECT * FROM movies WHERE originalTitle = 'Spider-Man 2' or " +
            "originalTitle = 'The Shawshank Redemption' or " +
            "originalTitle = 'Forrest Gump' or " +
            "originalTitle = 'Avatar'")
    List<Movie> getHotMovie();
}
