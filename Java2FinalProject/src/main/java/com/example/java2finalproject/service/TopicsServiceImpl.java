package com.example.java2finalproject.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.java2finalproject.dao.TopicsMapper;
import com.example.java2finalproject.entity.Topics;
import org.springframework.stereotype.Service;

@Service
public class TopicsServiceImpl extends ServiceImpl<TopicsMapper, Topics> implements TopicsService{

}
