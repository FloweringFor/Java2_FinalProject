package com.example.java2finalproject.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.java2finalproject.dao.CommitsMapper;
import com.example.java2finalproject.entity.Commits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CommitsServiceImpl extends ServiceImpl<CommitsMapper, Commits> implements CommitsService {

    @Autowired
    private CommitsMapper commitsMapper;

    @Override
    public List<Commits> listByRepo(String s) {
        QueryWrapper<Commits> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("repo", s).orderByAsc("commit_at ");
        return commitsMapper.selectList(queryWrapper);
    }

    @Override
    public int sum1() {
        return commitsMapper.sum1();
    }

    @Override
    public int sum2() {
        return commitsMapper.sum2();
    }

    @Override
    public int sum3() {
        return commitsMapper.sum3();
    }

    @Override
    public void deleteAll() {
        QueryWrapper<Commits> queryWrapper = new QueryWrapper<>();
        queryWrapper.select();
        commitsMapper.delete(queryWrapper);
    }
}
