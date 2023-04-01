package com.groupfour.eMovie.dao;

import com.groupfour.eMovie.entity.Genre;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GenreDao {
    @Select("SELECT * FROM genres")
    List<Genre> getGenres();
}
