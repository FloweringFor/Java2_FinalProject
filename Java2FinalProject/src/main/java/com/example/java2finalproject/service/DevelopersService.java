package com.example.java2finalproject.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.java2finalproject.entity.Developers;
import java.util.List;

public interface DevelopersService extends IService<Developers> {


    List<Developers> listByRepo(String s);


    int sumContributions1();

    int sumContributors1();

    Developers mostActive1();


    int sumContributions2();

    int sumContributors2();

    Developers mostActive2();

    int sumContributions3();

    int sumContributors3();

    Developers mostActive3();

    void deleteAll();
}
