package com.example.java2finalproject.controller;

import com.example.java2finalproject.entity.Developers;
import com.example.java2finalproject.service.DevelopersService;
import com.example.java2finalproject.service.RecordsService;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/developers")
public class DevelopersController {
    @Autowired
    private DevelopersService developersService;

    @Autowired
    private RecordsService recordsService;

    private final String[] repoName = {"go-sql-driver/mysql", "jquery/jquery", "square/picasso"};

    private final Jedis jedis = new Jedis("localhost");


    @RequestMapping("/index")
    public String index(Model model) throws ParseException {
        return "index";
    }

    @RequestMapping("/query_go")
    @ResponseBody
    public List<FrontData> developers1(){
        return developView(developersService.listByRepo(repoName[0]));
    }

    @RequestMapping("/query_jquery")
    @ResponseBody
    public List<FrontData> developers2(){
        return developView(developersService.listByRepo(repoName[1]));
    }

    @RequestMapping("/query_square")
    @ResponseBody
    public List<FrontData> developers3(){
        return developView(developersService.listByRepo(repoName[2]));
    }

    public List<FrontData> developView(List<Developers> developersList){
        List<FrontData> res = new ArrayList<>();
        if (developersList.size() <= 10){
            for (Developers d : developersList){
                res.add(new FrontData(d.getLogin(), d.getContribution()));
            }
        } else {
            int other = 0;
            for (int i = 0; i < 10; i++){
                res.add(new FrontData(developersList.get(i).getLogin(), developersList.get(i).getContribution()));
            }

            for (int i = 10; i < developersList.size(); i++){
                other += developersList.get(i).getContribution();
            }

            res.add(new FrontData("other people", other));
        }
        return res;
    }


    @RequestMapping("/excel_go")
    @ResponseBody
    public void excel_go(HttpServletResponse response) throws Exception {
        List<Developers> list = developersService.listByRepo(repoName[0]);
        res(response, list);
    }

    @RequestMapping("/excel_jquery")
    @ResponseBody
    public void excel_jquery(HttpServletResponse response) throws Exception {
        List<Developers> list = developersService.listByRepo(repoName[1]);
        res(response, list);
    }

    @RequestMapping("/excel_square")
    @ResponseBody
    public void excel_square(HttpServletResponse response) throws Exception {
        List<Developers> list = developersService.listByRepo(repoName[2]);
        res(response, list);
    }

    public void res(HttpServletResponse response, List<Developers> list) throws Exception{
        response.setCharacterEncoding("UTF-8");
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("data of developers");
        // 创建表头
        HSSFRow hssfRow = sheet.createRow(0);
        hssfRow.createCell(0).setCellValue("username");
        hssfRow.createCell(1).setCellValue("contribution");
        for (Developers data : list){
            HSSFRow dataRow = sheet.createRow(sheet.getLastRowNum()+1);
            dataRow.createCell(0).setCellValue(data.getLogin());
            dataRow.createCell(1).setCellValue(data.getContribution());
        }
        // 建立输出流，输出浏览器文件
        OutputStream os = null;
        response.setContentType("application/octet-stream;charset=utf-8");
        response.setHeader("Content-Disposition","attachment;filename=" + new String("data of developers".getBytes(), StandardCharsets.ISO_8859_1) + ".xls");
        // 输出文件
        os = response.getOutputStream();
        wb.write(os);
        os.flush();
        os.close();
    }

    @RequestMapping("/go")
    public String go(Model model) throws ParseException {
        try{
            jedis.ping();
            if (jedis.get("contributions_go") != null){
                model.addAttribute("contributions", jedis.get("contributions_go"));
                // System.out.println("查询");
            } else {
                int temp = developersService.sumContributions1();
                model.addAttribute("contributions", temp);
                jedis.set("contributions_go", String.valueOf(temp));
                // System.out.println("添加");
            }

            if (jedis.get("contributors_go") != null){
                model.addAttribute("contributors", jedis.get("contributors_go"));
            } else {
                int temp = developersService.sumContributors1();
                model.addAttribute("contributors", temp);
                jedis.set("contributors_go", String.valueOf(temp));
            }

            if (jedis.get("updateTime") != null){
                model.addAttribute("updateTime", jedis.get("updateTime"));
            } else {
                Date date = recordsService.getUpdateTime();
                model.addAttribute("updateTime", date);
                jedis.set("updateTime", date.toString());
            }
        } catch (Exception e){
            // System.out.println("未连接");
            model.addAttribute("contributions", developersService.sumContributions1());
            model.addAttribute("contributors", developersService.sumContributors1());
            model.addAttribute("updateTime", recordsService.getUpdateTime());
        }

        Developers developer = developersService.mostActive1();
        FrontData frontData = new FrontData(developer.getLogin(), developer.getContribution());
        model.addAttribute("mostActive", frontData);

        return "developers/go";
    }

    @RequestMapping("/jquery")
    public String jquery(Model model) throws ParseException {
        try{
            jedis.ping();
            if (jedis.get("contributions_jquery") != null){
                model.addAttribute("contributions", jedis.get("contributions_jquery"));
                // System.out.println("查询");
            } else {
                int temp = developersService.sumContributions2();
                model.addAttribute("contributions", temp);
                jedis.set("contributions_jquery", String.valueOf(temp));
                // System.out.println("添加");
            }

            if (jedis.get("contributors_jquery") != null){
                model.addAttribute("contributors", jedis.get("contributors_jquery"));
            } else {
                int temp = developersService.sumContributors2();
                model.addAttribute("contributors", temp);
                jedis.set("contributors_jquery", String.valueOf(temp));
            }

            if (jedis.get("updateTime") != null){
                model.addAttribute("updateTime", jedis.get("updateTime"));
            } else {
                Date date = recordsService.getUpdateTime();
                model.addAttribute("updateTime", date);
                jedis.set("updateTime", date.toString());
            }
        } catch (Exception e){
            // System.out.println("未连接");
            model.addAttribute("contributions", developersService.sumContributions2());
            model.addAttribute("contributors", developersService.sumContributors2());
            model.addAttribute("updateTime", recordsService.getUpdateTime());
        }

        Developers developer = developersService.mostActive2();
        FrontData frontData = new FrontData(developer.getLogin(), developer.getContribution());
        model.addAttribute("mostActive", frontData);

        return "developers/jquery";
    }

    @RequestMapping("/square")
    public String square(Model model) throws ParseException {
        try{
            jedis.ping();
            if (jedis.get("contributions_square") != null){
                model.addAttribute("contributions", jedis.get("contributions_square"));
                // System.out.println("查询");
            } else {
                int temp = developersService.sumContributions3();
                model.addAttribute("contributions", temp);
                jedis.set("contributions_square", String.valueOf(temp));
                // System.out.println("添加");
            }

            if (jedis.get("contributors_square") != null){
                model.addAttribute("contributors", jedis.get("contributors_square"));
            } else {
                int temp = developersService.sumContributors3();
                model.addAttribute("contributors", temp);
                jedis.set("contributors_square", String.valueOf(temp));
            }

            if (jedis.get("updateTime") != null){
                model.addAttribute("updateTime", jedis.get("updateTime"));
            } else {
                Date date = recordsService.getUpdateTime();
                model.addAttribute("updateTime", date);
                jedis.set("updateTime", date.toString());
            }
        } catch (Exception e){
            // System.out.println("未连接");
            model.addAttribute("contributions", developersService.sumContributions1());
            model.addAttribute("contributors", developersService.sumContributors1());
            model.addAttribute("updateTime", recordsService.getUpdateTime());
        }

        Developers developer = developersService.mostActive3();
        FrontData frontData = new FrontData(developer.getLogin(), developer.getContribution());
        model.addAttribute("mostActive", frontData);

        return "developers/square";
    }
}