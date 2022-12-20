package com.example.java2finalproject.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.java2finalproject.dao.IssuesMapper;
import com.example.java2finalproject.entity.Issues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class IssuesServiceImpl extends ServiceImpl<IssuesMapper, Issues> implements IssuesService{

    @Autowired
    private IssuesMapper issuesMapper;

    @Override
    public int countOpen1() {
        return issuesMapper.countOpen1();
    }

    @Override
    public int countClosed1() {
        return issuesMapper.countClosed1();
    }

    @Override
    public int countOpen2() {
        return issuesMapper.countOpen2();
    }

    @Override
    public int countClosed2() {
        return issuesMapper.countClosed2();
    }

    @Override
    public int countOpen3() {
        return issuesMapper.countOpen3();
    }

    @Override
    public int countClosed3() {
        return issuesMapper.countClosed3();
    }

    @Override
    public List<Long> listDay1() {
        return issuesMapper.listDay1();
    }

    @Override
    public List<Long> listDay2() {
        return issuesMapper.listDay2();
    }

    @Override
    public List<Long> listDay3() {
        return issuesMapper.listDay3();
    }

    @Override
    public List<Issues> listByRepo(String s) {
        QueryWrapper<Issues> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("repo", s);
        return issuesMapper.selectList(queryWrapper);
    }

    @Override
    public void deleteAll() {
        QueryWrapper<Issues> queryWrapper = new QueryWrapper<>();
        queryWrapper.select();
        issuesMapper.delete(queryWrapper);
    }


}
