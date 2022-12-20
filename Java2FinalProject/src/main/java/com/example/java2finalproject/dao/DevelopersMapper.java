package com.example.java2finalproject.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.java2finalproject.entity.Developers;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DevelopersMapper extends BaseMapper<Developers> {

    @Select("select sum(contribution) from developers where repo = 'go-sql-driver/mysql'")
    int sumContributions1();

    @Select("select sum(contribution) from developers where repo = 'jquery/jquery'")
    int sumContributions2();

    @Select("select sum(contribution) from developers where repo = 'square/picasso'")
    int sumContributions3();

    @Select("select count(*) from developers where repo = 'go-sql-driver/mysql'")
    int sumContributors1();

    @Select("select * from developers where repo = 'go-sql-driver/mysql' order by contribution desc limit 1")
    Developers mostActive1();

    @Select("select count(*) from developers where repo = 'jquery/jquery'")
    int sumContributors2();

    @Select("select * from developers where repo = 'jquery/jquery' order by contribution desc limit 1")
    Developers mostActive2();

    @Select("select count(*) from developers where repo = 'square/picasso'")
    int sumContributors3();

    @Select("select * from developers where repo = 'square/picasso' order by contribution desc limit 1")
    Developers mostActive3();
}

