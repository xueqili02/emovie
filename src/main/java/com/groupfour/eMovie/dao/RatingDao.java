package com.groupfour.eMovie.dao;

import com.groupfour.eMovie.entity.Rating;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RatingDao {

    @Select("SELECT * FROM movie_ratings WHERE uid = #{uid}")
    List<Rating> getLinkByUid(int uid);
}
