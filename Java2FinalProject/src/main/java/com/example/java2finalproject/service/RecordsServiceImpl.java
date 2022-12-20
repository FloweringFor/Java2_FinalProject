package com.example.java2finalproject.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.java2finalproject.dao.RecordsMapper;
import com.example.java2finalproject.entity.Records;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class RecordsServiceImpl extends ServiceImpl<RecordsMapper, Records> implements RecordsService{

    @Autowired
    private RecordsMapper recordsMapper;

    @Override
    public Date getUpdateTime() {
        return recordsMapper.getUpdateTime();
    }

    @Override
    public void deleteAll() {
        QueryWrapper<Records> queryWrapper = new QueryWrapper<>();
        queryWrapper.select();
        recordsMapper.delete(queryWrapper);
    }

}
