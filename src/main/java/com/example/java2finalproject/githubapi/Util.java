package com.example.java2finalproject.githubapi;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.java2finalproject.entity.Developers;
import com.example.java2finalproject.entity.Issues;
import com.example.java2finalproject.service.DevelopersService;
import com.example.java2finalproject.service.IssuesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class Util {
    @Autowired
    private DevelopersService developersService;

    @Autowired
    private IssuesService issuesService;

    // https://api.github.com/repos/go-sql-driver/mysql/contributors?per_page=100&page=3
    public void getDevelopersData(String repository) throws IOException {
        int count = 1;
        String root = "https://api.github.com/repos/" + repository + "/contributors?per_page=100&page=";
        while (true){
            List<Developers> list = new ArrayList<>();
            String url = root + count;
            URL restURL = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) restURL.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Authorization", "token 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");

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while((line = br.readLine()) != null ){
                stringBuilder.append(line);
            }
            // System.out.println(stringBuilder);
            String data = stringBuilder.toString();
            if (data.equals("[]")){
                break;
            }

            JSONArray array = JSONArray.parseArray(data);
            for (Object object : array){
                JSONObject each = (JSONObject) object;
                String login = each.getString("login");
                int id = each.getInteger("id");
                int contribution = each.getInteger("contributions");
                Developers d = new Developers(repository, login, id, contribution);
                list.add(d);
            }
            count++;
            developersService.saveBatch(list);
        }
    }


    // https://api.github.com/repos/go-sql-driver/mysql/issues?state=closed&per_page=100&page=1
    public void getIssuesData(String repository) throws IOException, ParseException {
        List<Issues> list = new ArrayList<>();
        int count = 1;
        String root = "https://api.github.com/repos/" + repository + "/issues?state=all&per_page=100&page=";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        while (true){
            String url = root + count;
            URL restURL = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) restURL.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Authorization", "token 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");

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while((line = br.readLine()) != null ){
                stringBuilder.append(line);
            }
            // System.out.println(stringBuilder);
            String data = stringBuilder.toString();
            if (data.equals("[]")){
                break;
            }

            JSONArray array = JSONArray.parseArray(data);
            for (Object object : array){
                JSONObject each = (JSONObject) object;
                Integer number = each.getInteger("number");
                String state = each.getString("state");
                Date createdAt = format.parse(each.getString("created_at").replace("T", " ").replace("Z",""));
                String cdd = each.getString("closed_at");
                Date closedAt = null;
                if (cdd != null){
                    closedAt = format.parse(cdd.replace("T", " ").replace("Z",""));
                }

                Issues i = new Issues(repository, number, state, createdAt, closedAt);
                list.add(i);
            }
            count++;
        }
        issuesService.saveBatch(list);

    }

    @Scheduled(fixedDelay = 10000000000L)
    public void test() throws IOException, ParseException {
        // getDevelopersData("go-sql-driver/mysql");
        // getDevelopersData("jquery/jquery");
        // getDevelopersData("square/picasso");
        // getIssuesData("go-sql-driver/mysql");
        // getIssuesData("jquery/jquery");
        // getIssuesData("square/picasso");
        // System.out.println("*************");


    }
}
