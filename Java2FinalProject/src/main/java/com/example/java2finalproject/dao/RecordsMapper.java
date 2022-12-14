package com.example.java2finalproject.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.example.java2finalproject.entity.Records;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Date;

@Mapper
public interface RecordsMapper extends BaseMapper<Records> {
    @Select("select record_at from records order by record_at desc limit 1")
    Date getUpdateTime();

}
