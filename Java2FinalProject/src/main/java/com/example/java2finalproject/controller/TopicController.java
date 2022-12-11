package com.example.java2finalproject.controller;

import com.example.java2finalproject.service.TopicsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class TopicController {

    @Autowired
    private TopicsService topicsService;

}
