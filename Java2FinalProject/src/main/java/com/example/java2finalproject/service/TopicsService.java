package com.example.java2finalproject.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.java2finalproject.entity.Topics;
import java.util.List;


public interface TopicsService extends IService<Topics> {

    List<Topics> listByRepo(String s);

    List<Topics> listByRepoLimit(String s);
}
