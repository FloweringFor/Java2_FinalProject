package com.example.java2finalproject.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.java2finalproject.entity.Commits;
import java.util.List;

public interface CommitsService extends IService<Commits>{

    List<Commits> listByRepo(String s);

    int sum1();

    int sum2();

    int sum3();

    void deleteAll();
}
