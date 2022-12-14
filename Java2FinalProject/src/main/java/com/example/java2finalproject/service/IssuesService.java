package com.example.java2finalproject.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.java2finalproject.entity.Developers;
import com.example.java2finalproject.entity.Issues;

import java.util.Date;
import java.util.List;

public interface IssuesService extends IService<Issues> {

    int countOpen1();

    int countClosed1();

    int countOpen2();

    int countClosed2();

    int countOpen3();

    int countClosed3();


    List<Long> listDay1();

    List<Long> listDay2();

    List<Long> listDay3();

    List<Issues> listByRepo(String s);

    void deleteAll();
}
