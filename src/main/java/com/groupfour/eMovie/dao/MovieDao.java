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

    @Select("SELECT * FROM movies WHERE originalTitle like '%#{originalTitle}%'")
    List<Movie> getMovieByOriginalTitle(@Param("originalTitle") String originalTitle);

    @Select("SELECT * FROM movies SORT BY popularity")
    List<Movie> getMovieByPopularitySorted();

    @Insert("INSERT INTO movies VALUES(#{id}, #{budget}, #{originalLanguage}, #{originalTitle}, " +
            "#{popularity}, #{releaseDate}, #{revenue}, #{runtime}, #{title}, #{voteAverage}, #{voteCount})")
    @Options(keyColumn = "id", keyProperty = "id", useGeneratedKeys = true)
    Integer addMovie(Movie movie);
}
