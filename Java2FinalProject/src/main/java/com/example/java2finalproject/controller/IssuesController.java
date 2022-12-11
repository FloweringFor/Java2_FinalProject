package com.example.java2finalproject.controller;

import com.example.java2finalproject.service.IssuesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class IssuesController {
    @Autowired
    private IssuesService issuesService;

}
