package com.example.java2finalproject.controller;

import com.example.java2finalproject.service.CommitsService;
import com.example.java2finalproject.service.ReleasesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ReleasesAndCommitsController {
    @Autowired
    private ReleasesService releasesService;

    @Autowired
    private CommitsService commitsService;

}
