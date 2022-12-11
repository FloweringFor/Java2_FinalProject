package com.example.java2finalproject.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.java2finalproject.entity.Developers;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DevelopersMapper extends BaseMapper<Developers> {

    @Select("select sum(contribution) from developers where repo = 'go-sql-driver/mysql'")
    int sum1();

}
