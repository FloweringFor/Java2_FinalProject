package com.example.java2finalproject.controller;

import com.example.java2finalproject.entity.Commits;
import com.example.java2finalproject.entity.Developers;
import com.example.java2finalproject.entity.Issues;
import com.example.java2finalproject.entity.Releases;
import com.example.java2finalproject.service.CommitsService;
import com.example.java2finalproject.service.DevelopersService;
import com.example.java2finalproject.service.IssuesService;
import com.example.java2finalproject.service.ReleasesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@Controller
@RequestMapping("/api")
public class RestController {

    @Autowired
    private DevelopersService developersService;

    @Autowired
    private IssuesService issuesService;

    @Autowired
    private ReleasesService releasesService;

    @Autowired
    private CommitsService commitsService;

    private final String[] repoName = {"go-sql-driver/mysql", "jquery/jquery", "square/picasso"};


    @RequestMapping("/developers/go")
    @ResponseBody
    public List<Developers> developers_go() {
        return developersService.listByRepo(repoName[0]);
    }

    @RequestMapping("/developers/jquery")
    @ResponseBody
    public List<Developers> developers_jquery() {
        return developersService.listByRepo(repoName[1]);
    }

    @RequestMapping("/developers/square")
    @ResponseBody
    public List<Developers> developers_square() {
        return developersService.listByRepo(repoName[2]);
    }


    @RequestMapping("/issuess/go")
    @ResponseBody
    public List<Issues> issuess_go() {
        return issuesService.listByRepo(repoName[0]);
    }

    @RequestMapping("/issuess/jquery")
    @ResponseBody
    public List<Issues> issuess_jquery() {
        return issuesService.listByRepo(repoName[1]);
    }

    @RequestMapping("/issues/square")
    @ResponseBody
    public List<Issues> issuess_square() {
        return issuesService.listByRepo(repoName[2]);
    }

    @RequestMapping("/releases/go")
    @ResponseBody
    public List<Releases> releases_go() {
        return releasesService.listByRepo(repoName[0]);
    }

    @RequestMapping("/releases/jquery")
    @ResponseBody
    public List<Releases> releases_jquery() {
        return releasesService.listByRepo(repoName[1]);
    }

    @RequestMapping("/releases/square")
    @ResponseBody
    public List<Releases> releases_square() {
        return releasesService.listByRepo(repoName[2]);
    }

    @RequestMapping("/commits/go")
    @ResponseBody
    public List<Commits> commits_go() {
        return commitsService.listByRepo(repoName[0]);
    }

    @RequestMapping("/commits/jquery")
    @ResponseBody
    public List<Commits> commits_jquery() {
        return commitsService.listByRepo(repoName[1]);
    }

    @RequestMapping("/commits/square")
    @ResponseBody
    public List<Commits> commits_square() {
        return commitsService.listByRepo(repoName[2]);
    }
}
