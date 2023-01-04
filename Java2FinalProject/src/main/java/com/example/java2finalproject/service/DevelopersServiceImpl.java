package com.example.java2finalproject.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.java2finalproject.dao.DevelopersMapper;
import com.example.java2finalproject.entity.Developers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DevelopersServiceImpl extends ServiceImpl<DevelopersMapper, Developers> implements DevelopersService {

    @Autowired
    private DevelopersMapper developersMapper;

    @Override
    public List<Developers> listByRepo(String s) {
        QueryWrapper<Developers> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("repo", s);
        return developersMapper.selectList(queryWrapper);
    }

    @Override
    public int sumContributions1() {
        return developersMapper.sumContributions1();
    }

    @Override
    public int sumContributors1() {
        return developersMapper.sumContributors1();
    }

    @Override
    public Developers mostActive1() {
        return developersMapper.mostActive1();
    }

    @Override
    public int sumContributions2() {
        return developersMapper.sumContributions2();
    }

    @Override
    public int sumContributors2() {
        return developersMapper.sumContributors2();
    }

    @Override
    public Developers mostActive2() {
        return developersMapper.mostActive2();
    }

    @Override
    public int sumContributions3() {
        return developersMapper.sumContributions3();
    }

    @Override
    public int sumContributors3() {
        return developersMapper.sumContributors3();
    }

    @Override
    public Developers mostActive3() {
        return developersMapper.mostActive3();
    }

    @Override
    public void deleteAll() {
        QueryWrapper<Developers> queryWrapper = new QueryWrapper<>();
        queryWrapper.select();
        developersMapper.delete(queryWrapper);
    }

}
