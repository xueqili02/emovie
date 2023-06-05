package com.groupfour.eMovie.dao;

import com.groupfour.eMovie.entity.MovieGenre;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MovieGenreDao {

    @Select("SELECT * FROM movie_genres WHERE movieid = #{movieid}")
    List<MovieGenre> getMovieGenreByMovieId(@Param("movieid") int movieid);

    @Select("SELECT * FROM movie_genres WHERE genreid = #{genreid}")
    List<MovieGenre> getMovieByGenreId(@Param("genreid") int genreid);
}
