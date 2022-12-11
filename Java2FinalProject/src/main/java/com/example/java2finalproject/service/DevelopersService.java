package com.example.java2finalproject.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.java2finalproject.entity.Developers;

import java.util.List;

public interface DevelopersService extends IService<Developers> {


    List<Developers> listByRepo(String s);

    int sum1();
}
