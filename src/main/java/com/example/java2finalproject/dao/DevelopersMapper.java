package com.example.java2finalproject.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.java2finalproject.entity.Developers;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DevelopersMapper extends BaseMapper<Developers> {

    @Select("select * from developers where repo = 'go-sql-driver/mysql' order by contribution desc")
    List<Developers> listByRepo();

    @Select("select sum(contribution) from developers where repo = 'go-sql-driver/mysql'")
    int sumAll();
}
