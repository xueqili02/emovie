package com.groupfour.eMovie.dao;

import com.groupfour.eMovie.entity.Keyword;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface KeywordDao {

    @Select("SELECT id, keyword FROM keywords where id = #{id}")
    Keyword getKeywordById(@Param("id") int id);
}
