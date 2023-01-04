package com.example.java2finalproject.controller;

import com.example.java2finalproject.entity.Commits;
import com.example.java2finalproject.entity.Releases;
import com.example.java2finalproject.service.CommitsService;
import com.example.java2finalproject.service.RecordsService;
import com.example.java2finalproject.service.ReleasesService;
import com.example.java2finalproject.view.FrontData;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/releasesAndCommits")
public class ReleasesAndCommitsController {

    @Autowired
    private ReleasesService releasesService;

    @Autowired
    private CommitsService commitsService;

    @Autowired
    private RecordsService recordsService;

    private final String[] repoName = {"go-sql-driver/mysql", "jquery/jquery", "square/picasso"};

    private final Jedis jedis = new Jedis("localhost");

    @RequestMapping("/go")
    public String go(Model model) throws ParseException {
        try {
            jedis.ping();
            if (jedis.get("releasesCount_go") != null) {
                model.addAttribute("releasesCount", jedis.get("releasesCount_go"));

            } else {
                int temp = releasesService.sum1();
                model.addAttribute("releasesCount", temp);
                jedis.set("releasesCount_go", String.valueOf(temp));
            }

            if (jedis.get("commitsCount_go") != null) {
                model.addAttribute("commitsCount", jedis.get("commitsCount_go"));
            } else {
                int temp = commitsService.sum1();
                model.addAttribute("commitsCount", temp);
                jedis.set("commitsCount_go", String.valueOf(temp));
            }

            if (jedis.get("updateTime") != null) {
                model.addAttribute("updateTime", jedis.get("updateTime"));
            } else {
                Date date = recordsService.getUpdateTime();
                model.addAttribute("updateTime", date);
                jedis.set("updateTime", date.toString());
            }

        } catch (Exception e) {
            model.addAttribute("releasesCount", releasesService.sum1());
            model.addAttribute("commitsCount", commitsService.sum1());
            model.addAttribute("updateTime", recordsService.getUpdateTime());
        }
        return "releasesAndCommits/go";
    }

    @RequestMapping("/jquery")
    public String jquery(Model model) throws ParseException {
        try {
            jedis.ping();
            if (jedis.get("releasesCount_jquery") != null) {
                model.addAttribute("releasesCount", jedis.get("releasesCount_jquery"));

            } else {
                int temp = releasesService.sum2();
                model.addAttribute("releasesCount", temp);
                jedis.set("releasesCount_jquery", String.valueOf(temp));
            }

            if (jedis.get("commitsCount_jquery") != null) {
                model.addAttribute("commitsCount", jedis.get("commitsCount_jquery"));
            } else {
                int temp = commitsService.sum2();
                model.addAttribute("commitsCount", temp);
                jedis.set("commitsCount_jquery", String.valueOf(temp));
            }

            if (jedis.get("updateTime") != null) {
                model.addAttribute("updateTime", jedis.get("updateTime"));
            } else {
                Date date = recordsService.getUpdateTime();
                model.addAttribute("updateTime", date);
                jedis.set("updateTime", date.toString());
            }

        } catch (Exception e) {
            model.addAttribute("releasesCount", releasesService.sum2());
            model.addAttribute("commitsCount", commitsService.sum2());
            model.addAttribute("updateTime", recordsService.getUpdateTime());
        }
        return "releasesAndCommits/jquery";
    }

    @RequestMapping("/square")
    public String square(Model model) throws ParseException {
        try {
            jedis.ping();
            if(jedis.get("releasesCount_square") != null) {
                model.addAttribute("releasesCount", jedis.get("releasesCount_square"));

            } else {
                int temp = releasesService.sum3();
                model.addAttribute("releasesCount", temp);
                jedis.set("releasesCount_square", String.valueOf(temp));
            }

            if (jedis.get("commitsCount_square") != null) {
                model.addAttribute("commitsCount", jedis.get("commitsCount_square"));
            } else {
                int temp = commitsService.sum3();
                model.addAttribute("commitsCount", temp);
                jedis.set("commitsCount_square", String.valueOf(temp));
            }

            if (jedis.get("updateTime") != null) {
                model.addAttribute("updateTime", jedis.get("updateTime"));
            } else {
                Date date = recordsService.getUpdateTime();
                model.addAttribute("updateTime", date);
                jedis.set("updateTime", date.toString());
            }

        } catch (Exception e) {
            model.addAttribute("releasesCount", releasesService.sum3());
            model.addAttribute("commitsCount", commitsService.sum3());
            model.addAttribute("updateTime", recordsService.getUpdateTime());
        }
        return "releasesAndCommits/square";
    }

    @RequestMapping("/releases_go")
    @ResponseBody
    public List<FrontData> releases_go() {
        List<Releases> list = releasesService.listByRepo(repoName[0]);
        return res(list);
    }

    @RequestMapping("/releases_jquery")
    @ResponseBody
    public List<FrontData> releases_jquery() {
        List<Releases> list = releasesService.listByRepo(repoName[1]);
        return res(list);
    }

    @RequestMapping("/releases_square")
    @ResponseBody
    public List<FrontData> releases_square() {
        List<Releases> list = releasesService.listByRepo(repoName[2]);
        return res(list);
    }

    public List<FrontData> res(List<Releases> list) {
        List<FrontData> result = new ArrayList<>();
        int index = 0;
        for (Releases r : list) {
            index++;
            result.add(new FrontData(r.getTagName(), index));
        }
        return result;
    }

    @RequestMapping("/commits_go")
    @ResponseBody
    public List<FrontData> commits_go() {
        List<Commits> commits = commitsService.listByRepo(repoName[0]);
        List<Releases> releases = releasesService.listByRepo(repoName[0]);
        return between(commits, releases);
    }

    @RequestMapping("/commits_jquery")
    @ResponseBody
    public List<FrontData> commits_jquery() {
        List<Commits> commits = commitsService.listByRepo(repoName[1]);
        List<Releases> releases = releasesService.listByRepo(repoName[1]);
        return between(commits, releases);
    }

    @RequestMapping("/commits_square")
    @ResponseBody
    public List<FrontData> commits_square() {
        List<Commits> commits = commitsService.listByRepo(repoName[2]);
        List<Releases> releases = releasesService.listByRepo(repoName[2]);
        return between(commits, releases);
    }

    public List<FrontData> between(List<Commits> commits, List<Releases> releases){
        List<FrontData> frontDataList = new ArrayList<>();
        int index = 0;
        int i = 0;
        int[] countAll = new int[releases.size()+1];
        for (; i < commits.size(); i++) {
            if (index < releases.size()) {
                if (commits.get(i).getCommitAt().getTime() < releases.get(index).getPublishedAt().getTime()) {
                    countAll[index]++;
                } else {
                    index++;
                    countAll[index]++;
                }
            } else {
                break;
            }
        }
        countAll[releases.size()] += commits.size() - i;

        frontDataList.add(new FrontData("before " + releases.get(0).getTagName(), countAll[0]));

        for (int j = 1; j < releases.size(); j++) {
            frontDataList.add(new FrontData(releases.get(j - 1).getTagName() + "~" + releases.get(j).getTagName(), countAll[j]));
        }

        frontDataList.add(new FrontData("after" + releases.get(releases.size()-1).getTagName(), countAll[countAll.length-1]));

        return frontDataList;
    }


    @RequestMapping("/weekday_go")
    @ResponseBody
    public List<FrontData> weekday_go() {
        List<Commits> commits = commitsService.listByRepo(repoName[0]);
        return calculate_weekday(commits);
    }


    @RequestMapping("/weekday_jquery")
    @ResponseBody
    public List<FrontData> weekday_jquery() {
        List<Commits> commits = commitsService.listByRepo(repoName[1]);
        return calculate_weekday(commits);
    }

    @RequestMapping("/weekday_square")
    @ResponseBody
    public List<FrontData> weekday_square() {
        List<Commits> commits = commitsService.listByRepo(repoName[2]);
        return calculate_weekday(commits);
    }

    public List<FrontData> calculate_weekday(List<Commits> commits) {
        List<FrontData> frontDataList = new ArrayList<>();
        String[] weekdays = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        int[] countAll = new int[7];
        for (Commits c : commits) {
            if (c.getCommitAt().toString().contains("Mon")) {
                countAll[0]++;
            } else if (c.getCommitAt().toString().contains("Tue")) {
                countAll[1]++;
            } else if (c.getCommitAt().toString().contains("Wed")) {
                countAll[2]++;
            } else if (c.getCommitAt().toString().contains("Thu")) {
                countAll[3]++;
            } else if (c.getCommitAt().toString().contains("Fri")) {
                countAll[4]++;
            } else if (c.getCommitAt().toString().contains("Sat")) {
                countAll[5]++;
            } else if (c.getCommitAt().toString().contains("Sun")) {
                countAll[6]++;
            }
        }

        for (int i = 0; i < weekdays.length; i++) {
            frontDataList.add(new FrontData(weekdays[i], countAll[i]));
        }
        return frontDataList;
    }

    @RequestMapping("/time_go")
    @ResponseBody
    public List<FrontData> time_go() {
        List<Commits> commits = commitsService.listByRepo(repoName[0]);
        return calculate_time(commits);
    }

    @RequestMapping("/time_jquery")
    @ResponseBody
    public List<FrontData> time_jquery() {
        List<Commits> commits = commitsService.listByRepo(repoName[1]);
        return calculate_time(commits);
    }

    @RequestMapping("/time_square")
    @ResponseBody
    public List<FrontData> time_square() {
        List<Commits> commits = commitsService.listByRepo(repoName[2]);
        return calculate_time(commits);
    }

    public List<FrontData> calculate_time(List<Commits> commits) {
        List<FrontData> frontDataList = new ArrayList<>();
        // Fri Dec 09 11:51:20 CST 2022
        int[] countAll = new int[4];
        String[] time = {"00:00~06:00", "06:01~12:00", "12:01~18:00", "18:01~23:59"};
        for (Commits c : commits) {
            // System.out.println(c.getCommitAt() + " " + c.getCommitAt().toString().substring(11, 13));
            if (Integer.parseInt(c.getCommitAt().toString().substring(11, 13)) < 6) {
                countAll[0]++;
            } else if (Integer.parseInt(c.getCommitAt().toString().substring(11, 13)) < 12) {
                countAll[1]++;
            } else if (Integer.parseInt(c.getCommitAt().toString().substring(11, 13)) < 18) {
                countAll[2]++;
            } else {
                countAll[3]++;
            }
        }

        for (int i = 0; i < time.length; i++) {
            frontDataList.add(new FrontData(time[i], countAll[i]));
        }
        return frontDataList;
    }

    @RequestMapping("/excelReleases_go")
    @ResponseBody
    public void excelReleases_go(HttpServletResponse response) throws Exception {
        List<Releases> list = releasesService.listByRepo(repoName[0]);
        res(response, list);
    }

    @RequestMapping("/excelReleases_jquery")
    @ResponseBody
    public void excelReleases_jquery(HttpServletResponse response) throws Exception {
        List<Releases> list = releasesService.listByRepo(repoName[1]);
        res(response, list);
    }

    @RequestMapping("/excelReleases_square")
    @ResponseBody
    public void excelReleases_square(HttpServletResponse response) throws Exception {
        List<Releases> list = releasesService.listByRepo(repoName[2]);
        res(response, list);
    }

    public void res(HttpServletResponse response, List<Releases> list) throws Exception {
        response.setCharacterEncoding("UTF-8");
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("data of releases");
        // 创建表头
        HSSFRow hssfRow = sheet.createRow(0);
        hssfRow.createCell(0).setCellValue("tag_name");
        hssfRow.createCell(1).setCellValue("published_at");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (Releases data : list) {
            HSSFRow dataRow = sheet.createRow(sheet.getLastRowNum()+1);
            dataRow.createCell(0).setCellValue(data.getTagName());
            dataRow.createCell(1).setCellValue(format.format(data.getPublishedAt()));
        }
        // 建立输出流，输出浏览器文件
        OutputStream os = null;
        response.setContentType("application/octet-stream;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + new String("data of releases".getBytes(), StandardCharsets.ISO_8859_1) + ".xls");
        // 输出文件
        os = response.getOutputStream();
        wb.write(os);
        os.flush();
        os.close();
    }

    @RequestMapping("/excelCommits_go")
    @ResponseBody
    public void excelCommits_go(HttpServletResponse response) throws Exception {
        List<Commits> list = commitsService.listByRepo(repoName[0]);
        res1(response, list);
    }

    @RequestMapping("/excelCommits_jquery")
    @ResponseBody
    public void excelCommits_jquery(HttpServletResponse response) throws Exception {
        List<Commits> list = commitsService.listByRepo(repoName[1]);
        res1(response, list);
    }

    @RequestMapping("/excelCommits_square")
    @ResponseBody
    public void excelCommits_square(HttpServletResponse response) throws Exception {
        List<Commits> list = commitsService.listByRepo(repoName[2]);
        res1(response, list);
    }

    public void res1(HttpServletResponse response, List<Commits> list) throws Exception {
        response.setCharacterEncoding("UTF-8");
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("data of commits");
        // 创建表头
        HSSFRow hssfRow = sheet.createRow(0);
        hssfRow.createCell(0).setCellValue("author");
        hssfRow.createCell(1).setCellValue("commit_at");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (Commits data : list) {
            HSSFRow dataRow = sheet.createRow(sheet.getLastRowNum()+1);
            dataRow.createCell(0).setCellValue(data.getAuthor());
            dataRow.createCell(1).setCellValue(format.format(data.getCommitAt()));
        }
        // 建立输出流，输出浏览器文件
        OutputStream os = null;
        response.setContentType("application/octet-stream;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + new String("data of commits".getBytes(), StandardCharsets.ISO_8859_1) + ".xls");
        // 输出文件
        os = response.getOutputStream();
        wb.write(os);
        os.flush();
        os.close();
    }
}
