package com.example.java2finalproject.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.java2finalproject.entity.Issues;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface IssuesMapper extends BaseMapper<Issues> {

    @Select("select count(*) from issues where repo = 'go-sql-driver/mysql' and state = 'open'")
    int countOpen1();

    @Select("select count(*) from issues where repo = 'go-sql-driver/mysql' and state = 'closed'")
    int countClosed1();

    @Select("select count(*) from issues where repo = 'jquery/jquery' and state = 'open'")
    int countOpen2();

    @Select("select count(*) from issues where repo = 'jquery/jquery' and state = 'closed'")
    int countClosed2();

    @Select("select count(*) from issues where repo = 'square/picasso' and state = 'open'")
    int countOpen3();

    @Select("select count(*) from issues where repo = 'square/picasso' and state = 'closed'")
    int countClosed3();

    // 转天
    @Select("select datediff(closed_at, created_at) from issues where repo = 'go-sql-driver/mysql' and state = 'closed'")
    List<Long> listDay1();

    @Select("select datediff(closed_at, created_at) from issues where repo = 'jquery/jquery' and state = 'closed'")
    List<Long> listDay2();

    @Select("select datediff(closed_at, created_at) from issues where repo = 'square/picasso' and state = 'closed'")
    List<Long> listDay3();

    // @Select("select timestampdiff(second,  created_at, closed_at) from issues where repo = 'go-sql-driver/mysql' and state = 'closed'")
}
