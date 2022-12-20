package com.example.java2finalproject.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.java2finalproject.entity.Commits;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CommitsMapper extends BaseMapper<Commits> {

    @Select("select count(*) from commits where repo = 'go-sql-driver/mysql'")
    int sum1();

    @Select("select count(*) from commits where repo = 'jquery/jquery'")
    int sum2();

    @Select("select count(*) from commits where repo = 'square/picasso'")
    int sum3();
}
