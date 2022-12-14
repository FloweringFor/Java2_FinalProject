package com.example.java2finalproject.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.java2finalproject.entity.Releases;

import java.util.List;

public interface ReleasesService extends IService<Releases> {

    int sum1();

    int sum2();

    int sum3();

    List<Releases> listByRepo(String s);

    void deleteAll();
}
