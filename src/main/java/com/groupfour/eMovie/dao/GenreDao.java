package com.groupfour.eMovie.dao;

import com.groupfour.eMovie.entity.Genre;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GenreDao {
    @Select("SELECT id, genre FROM genres")
    List<Genre> getGenres();

    @Select("SELECT id, genre FROM genres WHERE id = #{genreid}")
    Genre getGenreById(@Param("genreid") int genreid);

    // todo 新方法 在genres表中插入一条新数据
    // 逻辑：手写insert，主键设置为自增。传参类型、返回值类型和设置自增的方式参考userDao中的registerUser方法（也是insert）
}
