package com.example.java2finalproject.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.java2finalproject.entity.Releases;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ReleasesMapper extends BaseMapper<Releases> {

    @Select("select count(*) from releases where repo = 'go-sql-driver/mysql' and prerelease = 'false'")
    int sum1();

    @Select("select count(*) from releases where repo = 'jquery/jquery' and prerelease = 'false'")
    int sum2();

    @Select("select count(*) from releases where repo = 'square/picasso' and prerelease = 'false'")
    int sum3();
}
