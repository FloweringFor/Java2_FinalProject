package com.example.java2finalproject.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.java2finalproject.dao.KeywordsMapper;
import com.example.java2finalproject.entity.Keywords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class KeywordsServiceImpl extends ServiceImpl<KeywordsMapper, Keywords> implements KeywordsService{

    @Autowired
    private KeywordsMapper keywordsMapper;

    @Override
    public List<Keywords> listByRepo(String s) {
        QueryWrapper<Keywords> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("repo", s).orderByDesc("score").last("limit 50");
        return keywordsMapper.selectList(queryWrapper);
    }
}
