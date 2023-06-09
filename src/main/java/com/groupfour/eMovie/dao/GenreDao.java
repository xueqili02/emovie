package com.groupfour.eMovie.dao;

import com.groupfour.eMovie.entity.Genre;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface GenreDao {
    @Select("SELECT id, genre FROM genres")
    List<Genre> getGenres();

    @Select("SELECT id, genre FROM genres WHERE id = #{genreid}")
    Genre getGenreById(@Param("genreid") int genreid);

    @Insert("INSERT INTO genres(genre)" +
            "VALUES(#{genre.genre})")
    @Options(keyColumn = "id", keyProperty = "id", useGeneratedKeys = true)
    Integer insertGenre(@Param("genre") Genre genre);
}
