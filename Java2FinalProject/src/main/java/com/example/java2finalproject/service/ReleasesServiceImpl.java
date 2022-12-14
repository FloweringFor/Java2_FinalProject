package com.example.java2finalproject.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.java2finalproject.dao.ReleasesMapper;
import com.example.java2finalproject.entity.Releases;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReleasesServiceImpl extends ServiceImpl<ReleasesMapper, Releases> implements ReleasesService{

    @Autowired
    private ReleasesMapper releasesMapper;

    @Override
    public int sum1() {
        return releasesMapper.sum1();
    }

    @Override
    public int sum2() {
        return releasesMapper.sum2();
    }

    @Override
    public int sum3() {
        return releasesMapper.sum3();
    }

    @Override
    public List<Releases> listByRepo(String s) {
        QueryWrapper<Releases> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("repo",s).eq("prerelease","false").orderByAsc("published_at");
        return releasesMapper.selectList(queryWrapper);
    }

    @Override
    public void deleteAll() {
        QueryWrapper<Releases> queryWrapper = new QueryWrapper<>();
        queryWrapper.select();
        releasesMapper.delete(queryWrapper);
    }
}


// https://api.github.com/repos/go-sql-driver/mysql/contributors?per_page=100&page=3