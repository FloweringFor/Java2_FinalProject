package com.example.java2finalproject.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.java2finalproject.dao.CommitsMapper;
import com.example.java2finalproject.entity.Commits;
import org.springframework.stereotype.Service;

@Service
public class CommitsServiceImpl extends ServiceImpl<CommitsMapper, Commits> implements CommitsService{

}
