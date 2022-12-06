package com.example.java2finalproject.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.java2finalproject.dao.IssuesMapper;
import com.example.java2finalproject.entity.Issues;
import org.springframework.stereotype.Service;

@Service
public class IssuesServiceImpl extends ServiceImpl<IssuesMapper, Issues> implements IssuesService{
}
