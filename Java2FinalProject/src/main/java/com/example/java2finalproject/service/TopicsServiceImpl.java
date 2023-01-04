package com.example.java2finalproject.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.java2finalproject.dao.TopicsMapper;
import com.example.java2finalproject.entity.Topics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TopicsServiceImpl extends ServiceImpl<TopicsMapper, Topics> implements TopicsService {
    @Autowired
    private TopicsMapper topicsMapper;

    @Override
    public List<Topics> listByRepo(String s) {
        QueryWrapper<Topics> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("repo", s);
        return topicsMapper.selectList(queryWrapper);
    }

    @Override
    public List<Topics> listByRepoLimit(String s) {
        QueryWrapper<Topics> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("repo", s).orderByDesc("comment_count").last("limit 5");
        return topicsMapper.selectList(queryWrapper);
    }
}
