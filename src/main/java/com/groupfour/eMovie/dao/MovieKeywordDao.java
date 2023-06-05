package com.groupfour.eMovie.dao;

import com.groupfour.eMovie.entity.MovieKeyword;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MovieKeywordDao {

    @Select("SELECT movieid, keywordid FROM movie_keywords WHERE movieid = #{movieid}")
    List<MovieKeyword> getMovieKeywordByMovieId(@Param("movieid") int movieid);
}
