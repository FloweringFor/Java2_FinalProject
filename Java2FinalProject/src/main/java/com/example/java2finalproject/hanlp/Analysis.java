package com.example.java2finalproject.hanlp;

import com.example.java2finalproject.entity.Keywords;
import com.example.java2finalproject.entity.Topics;
import com.example.java2finalproject.service.KeywordsService;
import com.example.java2finalproject.service.TopicsService;
import com.hankcs.hanlp.HanLP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Component
public class Analysis {

    @Autowired
    private TopicsService topicsService;

    @Autowired
    private KeywordsService keywordsService;

    private final String[] repo = {"go-sql-driver/mysql", "jquery/jquery", "square/picasso"};

    public void createKeywords(String repo) {
        System.out.println("___________");
        List<Topics> topicsList = topicsService.listByRepo(repo);
        HashMap<String, Double> res = new HashMap<>();
        for (Topics topics : topicsList) {
            List<String> title = HanLP.extractKeyword(topics.getTitle(),2);
            for (String t : title) {
                if (res.containsKey(t.toLowerCase())){
                    res.put(t.toLowerCase(), res.get(t.toLowerCase()) + 0.35 * topics.getCommentCount());
                } else {
                    res.put(t.toLowerCase(), 0.35 * topics.getCommentCount());
                }
            }
            if (topics.getDescription() != null && topics.getDescription().split(" ").length > 0) {
                int size = Math.max(topics.getDescription().split(" ").length / 20, 3);
                List<String> des = HanLP.extractKeyword(topics.getDescription(), size);
                for (String d : des) {
                    if (res.containsKey(d.toLowerCase())) {
                        res.put(d.toLowerCase(), res.get(d.toLowerCase()) + 0.3 * topics.getCommentCount() / size);
                    } else {
                        res.put(d.toLowerCase(), 0.3 * topics.getCommentCount() / size);
                    }
                }
            }
        }

        Set<String> set = res.keySet();
        List<Keywords> keywordsList = new ArrayList<>();
        for (String s : set) {
            System.out.println(s + " " + res.get(s));
            Keywords k = new Keywords();
            k.setRepo(repo);
            k.setContent(s);
            k.setScore(res.get(s));
            keywordsList.add(k);
        }
        keywordsService.saveBatch(keywordsList);
    }


    @Scheduled(fixedDelay = 86400000)
    public void createAll() {
        /*
        for (int i = 0; i < repo.length; i++){
            createKeywords(repo[i]);
        }
        System.out.println("finish____________");
         */
    }
}

