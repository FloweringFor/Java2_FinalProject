package com.example.java2finalproject.githubapi;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.java2finalproject.entity.*;
import com.example.java2finalproject.service.*;
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

/**
 * 数据爬取的工具类
 * 设置定时任务
 */
@Component
public class Util {
    @Autowired
    private DevelopersService developersService;

    @Autowired
    private IssuesService issuesService;

    @Autowired
    private ReleasesService releasesService;

    @Autowired
    private CommitsService commitsService;

    @Autowired
    private TopicsService topicsService;


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
                Developers d = new Developers();
                d.setRepo(repository);
                d.setLogin(login);
                d.setLoginId(id);
                d.setContribution(contribution);
                d.setRecordAt(new Date());
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

                Issues i = new Issues();
                i.setRepo(repository);
                i.setNumber(number);
                i.setState(state);
                i.setCreatedAt(createdAt);
                i.setClosedAt(closedAt);
                i.setRecordAt(new Date());
                list.add(i);
            }
            count++;
        }
        issuesService.saveBatch(list);
    }

    public void getReleasesData(String repository) throws IOException, ParseException {
        int count = 1;
        String root = "https://api.github.com/repos/" + repository + "/releases?per_page=100&page=";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        while (true){
            List<Releases> list = new ArrayList<>();
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
                String tag_name = each.getString("tag_name");
                String prerelease = each.getString("prerelease");
                Date published_at = format.parse(each.getString("published_at").replace("T", " ").replace("Z",""));
                Releases r = new Releases();
                r.setRepo(repository);
                r.setTagName(tag_name);
                r.setPrerelease(prerelease);
                r.setPublishedAt(published_at);
                r.setRecordAt(new Date());
                list.add(r);
            }
            count++;
            releasesService.saveBatch(list);
        }
    }

    public void getCommitsData(String repository) throws IOException, ParseException {
        int count = 1;
        String root = "https://api.github.com/repos/" + repository + "/commits?per_page=100&page=";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        while (true){
            List<Commits> list = new ArrayList<>();
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
                JSONObject commit = each.getJSONObject("commit");
                JSONObject author = commit.getJSONObject("author");
                String name = author.getString("name");
                // System.out.println(name);
                Date date = format.parse(author.getString("date").replace("T", " ").replace("Z",""));
                Commits c = new Commits();
                c.setRepo(repository);
                c.setAuthor(name);
                c.setCommitAt(date);
                c.setRecordAt(new Date());
                list.add(c);
            }
            count++;
            commitsService.saveBatch(list);
        }
    }


    public void getTopicsData(String repository) throws IOException, ParseException {

        int count = 1;
        String root = "https://api.github.com/repos/" + repository + "/issues?state=all&per_page=100&page=";
        while (true){
            List<Topics> list = new ArrayList<>();
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
                String title = each.getString("title");
                String description = each.getString("body");
                Topics t1 = new Topics();
                t1.setRepo(repository);
                t1.setContent(title);
                t1.setType("title");
                t1.setRecordAt(new Date());
                list.add(t1);

                Topics t2 = new Topics();
                t2.setRepo(repository);
                t2.setContent(description);
                t2.setType("description");
                t2.setRecordAt(new Date());
                list.add(t2);
            }
            count++;
            topicsService.saveBatch(list);
        } // https://api.github.com/repos/go-sql-driver/mysql/issues/comments
    }


    public void getCommentsData(String repository) throws IOException, ParseException {
        int count = 1;
        String root = "https://api.github.com/repos/" + repository + "/issues/comments?per_page=100&page=";
        while (true){
            List<Topics> list = new ArrayList<>();
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
                String content = each.getString("body");
                Topics t = new Topics();
                t.setRepo(repository);
                t.setContent(content);
                t.setType("comment");
                t.setRecordAt(new Date());
                list.add(t);
            }
            count++;
            topicsService.saveBatch(list);
        }
    }


    @Scheduled(fixedDelay = 86400000)   // 24h
    public void test() throws IOException, ParseException {
        // TODO
        // 这里改为先删除 再重新爬取数据 然后清空缓存



        /*
        String[] repo_name = {"go-sql-driver/mysql", "jquery/jquery", "square/picasso"};
        for (int i = 0; i < repo_name.length; i++){
            // getDevelopersData(repo_name[i]);
            // getIssuesData(repo_name[i]);
            getReleasesData(repo_name[i]);
            // getCommitsData(repo_name[i]);
        }
        System.out.println("finish-----------------");*/

        /*
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_WEEK, -7);
        Date date = c.getTime();

        QueryWrapper<Releases> queryWrapper = new QueryWrapper<>();
        queryWrapper.lt("record_at", date);
        releasesService.remove(queryWrapper);

        QueryWrapper<Developers> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.lt("record_at", date);
        developersService.remove(queryWrapper1);

        QueryWrapper<Issues> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.lt("record_at", date);
        issuesService.remove(queryWrapper2);

        QueryWrapper<Commits> queryWrapper3 = new QueryWrapper<>();
        queryWrapper3.lt("record_at", date);
        commitsService.remove(queryWrapper3);

        System.out.println("delete-----------------");
        */
    }
}
