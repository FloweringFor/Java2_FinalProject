package com.example.java2finalproject.controller;

import com.example.java2finalproject.entity.Keywords;
import com.example.java2finalproject.entity.Topics;
import com.example.java2finalproject.service.KeywordsService;
import com.example.java2finalproject.service.TopicsService;
import com.example.java2finalproject.view.FrontData;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/topics")
public class TopicController {

    @Autowired
    private TopicsService topicsService;

    @Autowired
    private KeywordsService keywordsService;

    private final String[] repoName = {"go-sql-driver/mysql", "jquery/jquery", "square/picasso"};


    @RequestMapping("/go")
    public String go(Model model) {
        List<Topics> topicsList = topicsService.listByRepoLimit(repoName[0]);
        model.addAttribute("problem1", topicsList.get(0).getTitle());
        model.addAttribute("problem2", topicsList.get(1).getTitle());
        model.addAttribute("problem3", topicsList.get(2).getTitle());
        model.addAttribute("problem4", topicsList.get(3).getTitle());
        model.addAttribute("problem5", topicsList.get(4).getTitle());
        return "topics/go";
    }

    @RequestMapping("/jquery")
    public String jquery(Model model) {
        List<Topics> topicsList = topicsService.listByRepoLimit(repoName[1]);
        model.addAttribute("problem1", topicsList.get(0).getTitle());
        model.addAttribute("problem2", topicsList.get(1).getTitle());
        model.addAttribute("problem3", topicsList.get(2).getTitle());
        model.addAttribute("problem4", topicsList.get(3).getTitle());
        model.addAttribute("problem5", topicsList.get(4).getTitle());
        return "topics/jquery";
    }


    @RequestMapping("/square")
    public String square(Model model) {
        List<Topics> topicsList = topicsService.listByRepoLimit(repoName[2]);
        model.addAttribute("problem1", topicsList.get(0).getTitle());
        model.addAttribute("problem2", topicsList.get(1).getTitle());
        model.addAttribute("problem3", topicsList.get(2).getTitle());
        model.addAttribute("problem4", topicsList.get(3).getTitle());
        model.addAttribute("problem5", topicsList.get(4).getTitle());
        return "topics/square";
    }

    @RequestMapping("/query_go")
    @ResponseBody
    public List<FrontData> query_go() {
        List<Keywords> keywordsList = keywordsService.listByRepo(repoName[0]);
        return res(keywordsList);
    }

    @RequestMapping("/query_jquery")
    @ResponseBody
    public List<FrontData> query_jquery() {
        List<Keywords> keywordsList = keywordsService.listByRepo(repoName[1]);
        return res(keywordsList);
    }

    @RequestMapping("/query_square")
    @ResponseBody
    public List<FrontData> query_square() {
        List<Keywords> keywordsList = keywordsService.listByRepo(repoName[2]);
        return res(keywordsList);
    }


    public List<FrontData> res(List<Keywords> keywordsList) {
        List<FrontData> frontDataList = new ArrayList<>();
        for (Keywords k : keywordsList) {
            FrontData frontData = new FrontData(k.getContent(), (int) k.getScore().longValue());
            frontDataList.add(frontData);
        }
        return frontDataList;
    }


}
