package com.example.java2finalproject.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.java2finalproject.entity.Keywords;
import java.util.List;

public interface KeywordsService extends IService<Keywords> {

    List<Keywords> listByRepo(String s);
}
