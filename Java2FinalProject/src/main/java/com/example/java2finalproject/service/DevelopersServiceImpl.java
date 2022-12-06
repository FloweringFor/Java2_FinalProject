package com.example.java2finalproject.service;

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
    public List<Developers> listByRepo() {
        return developersMapper.listByRepo();
    }

    @Override
    public int sumAll() {
        return developersMapper.sumAll();
    }
}
