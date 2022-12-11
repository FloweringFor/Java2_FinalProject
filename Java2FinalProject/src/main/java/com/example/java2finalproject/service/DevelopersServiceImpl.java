package com.example.java2finalproject.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.java2finalproject.dao.DevelopersMapper;
import com.example.java2finalproject.entity.Developers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DevelopersServiceImpl extends ServiceImpl<DevelopersMapper, Developers> implements DevelopersService{

    @Autowired
    private DevelopersMapper developersMapper;

    @Override
    public List<Developers> listByRepo(String s) {
        QueryWrapper<Developers> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("repo", s);
        return developersMapper.selectList(queryWrapper);
    }

    @Override
    public int sum1() {
        return developersMapper.sum1();
    }

}
