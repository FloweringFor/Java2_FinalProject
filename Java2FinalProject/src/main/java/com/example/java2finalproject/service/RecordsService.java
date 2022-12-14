package com.example.java2finalproject.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.java2finalproject.entity.Records;

import java.util.Date;
import java.util.List;

public interface RecordsService extends IService<Records> {

    Date getUpdateTime();

    void deleteAll();
}
