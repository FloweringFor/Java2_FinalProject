package com.example.java2finalproject.controller;

import com.example.java2finalproject.entity.Developers;
import com.example.java2finalproject.entity.Issues;
import com.example.java2finalproject.service.IssuesService;
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
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/issues")
public class IssuesController {
    @Autowired
    private IssuesService issuesService;

    @Autowired
    private RecordsService recordsService;


    private final String[] repoName = {"go-sql-driver/mysql", "jquery/jquery", "square/picasso"};

    private final Jedis jedis = new Jedis("localhost");


    @RequestMapping("/go")
    public String go(Model model) throws ParseException {
        try{
            jedis.ping();
            if (jedis.get("average_go") != null && jedis.get("range_go") != null && jedis.get("variance_go") != null){
                model.addAttribute("average", jedis.get("average_go"));
                model.addAttribute("range", jedis.get("range_go"));
                model.addAttribute("variance",jedis.get("variance_go"));
            } else {
                List<Long> list = issuesService.listDay1();
                long average = until_average(list);
                long range = until_range(list);
                long variance = until_variance(average, list);
                model.addAttribute("average", average);
                model.addAttribute("range", range);
                model.addAttribute("variance",variance);
                jedis.set("average_go", String.valueOf(average));
                jedis.set("range_go",String.valueOf(range));
                jedis.set("variance_go",String.valueOf(variance));
            }

            if (jedis.get("updateTime") != null){
                model.addAttribute("updateTime", jedis.get("updateTime"));
            } else {
                Date date = recordsService.getUpdateTime();
                model.addAttribute("updateTime", date);
                jedis.set("updateTime", date.toString());
            }

        }catch (Exception e){
            List<Long> list = issuesService.listDay1();
            long average = until_average(list);
            long range = until_range(list);
            long variance = until_variance(average, list);
            model.addAttribute("average", average);
            model.addAttribute("range", range);
            model.addAttribute("variance",variance);
            model.addAttribute("updateTime", recordsService.getUpdateTime());
        }
        return "issues/go";
    }

    @RequestMapping("/jquery")
    public String jquery(Model model) throws ParseException {
        try{
            jedis.ping();
            if (jedis.get("average_jquery") != null && jedis.get("range_jquery") != null && jedis.get("variance_jquery") != null){
                model.addAttribute("average", jedis.get("average_jquery"));
                model.addAttribute("range", jedis.get("range_jquery"));
                model.addAttribute("variance",jedis.get("variance_jquery"));
            } else {
                List<Long> list = issuesService.listDay2();
                long average = until_average(list);
                long range = until_range(list);
                long variance = until_variance(average, list);
                model.addAttribute("average", average);
                model.addAttribute("range", range);
                model.addAttribute("variance",variance);
                jedis.set("average_jquery", String.valueOf(average));
                jedis.set("range_jquery",String.valueOf(range));
                jedis.set("variance_jquery",String.valueOf(variance));
            }

            if (jedis.get("updateTime") != null){
                model.addAttribute("updateTime", jedis.get("updateTime"));
            } else {
                Date date = recordsService.getUpdateTime();
                model.addAttribute("updateTime", date);
                jedis.set("updateTime", date.toString());
            }

        }catch (Exception e){
            List<Long> list = issuesService.listDay2();
            long average = until_average(list);
            long range = until_range(list);
            long variance = until_variance(average, list);
            model.addAttribute("average", average);
            model.addAttribute("range", range);
            model.addAttribute("variance",variance);
            model.addAttribute("updateTime", recordsService.getUpdateTime());
        }
        return "issues/jquery";
    }


    @RequestMapping("/square")
    public String square(Model model) throws ParseException {
        try{
            jedis.ping();
            if (jedis.get("average_square") != null && jedis.get("range_square") != null && jedis.get("variance_square") != null){
                model.addAttribute("average", jedis.get("average_square"));
                model.addAttribute("range", jedis.get("range_square"));
                model.addAttribute("variance",jedis.get("variance_square"));
            } else {
                List<Long> list = issuesService.listDay3();
                long average = until_average(list);
                long range = until_range(list);
                long variance = until_variance(average, list);
                model.addAttribute("average", average);
                model.addAttribute("range", range);
                model.addAttribute("variance",variance);
                jedis.set("average_square", String.valueOf(average));
                jedis.set("range_square",String.valueOf(range));
                jedis.set("variance_square",String.valueOf(variance));
            }

            if (jedis.get("updateTime") != null){
                model.addAttribute("updateTime", jedis.get("updateTime"));
            } else {
                Date date = recordsService.getUpdateTime();
                model.addAttribute("updateTime", date);
                jedis.set("updateTime", date.toString());
            }

        }catch (Exception e){
            List<Long> list = issuesService.listDay3();
            long average = until_average(list);
            long range = until_range(list);
            long variance = until_variance(average, list);
            model.addAttribute("average", average);
            model.addAttribute("range", range);
            model.addAttribute("variance",variance);
            model.addAttribute("updateTime", recordsService.getUpdateTime());
        }
        return "issues/square";
    }


    @RequestMapping("/query_go")
    @ResponseBody
    public List<FrontData> query_go(){
        int open = -1;
        int closed = -1;
        try{
            if (jedis.get("open_go") != null){
                open = Integer.parseInt(jedis.get("open_go"));
            } else {
                open = issuesService.countOpen1();
                jedis.set("open_go",String.valueOf(open));
            }

            if (jedis.get("closed_go") != null){
                closed = Integer.parseInt(jedis.get("closed_go"));
            } else {
                closed = issuesService.countClosed1();
                jedis.set("closed_go", String.valueOf(closed));
            }
        } catch (Exception e){
            open = issuesService.countOpen1();
            closed = issuesService.countClosed1();
        }

        List<FrontData> list = new ArrayList<>();
        list.add(new FrontData("open", open));
        list.add(new FrontData("closed", closed));
        return list;
    }

    @RequestMapping("/query_jquery")
    @ResponseBody
    public List<FrontData> query_jquery(){
        int open = -1;
        int closed = -1;
        try{
            if (jedis.get("open_jquery") != null){
                open = Integer.parseInt(jedis.get("open_jquery"));
            } else {
                open = issuesService.countOpen2();
                jedis.set("open_jquery",String.valueOf(open));
            }

            if (jedis.get("closed_jquery") != null){
                closed = Integer.parseInt(jedis.get("closed_jquery"));
            } else {
                closed = issuesService.countClosed2();
                jedis.set("closed_jquery", String.valueOf(closed));
            }
        } catch (Exception e){
            open = issuesService.countOpen2();
            closed = issuesService.countClosed2();
        }

        List<FrontData> list = new ArrayList<>();
        list.add(new FrontData("open", open));
        list.add(new FrontData("closed", closed));
        return list;
    }

    @RequestMapping("/query_square")
    @ResponseBody
    public List<FrontData> query_square(){
        int open = -1;
        int closed = -1;
        try{
            if (jedis.get("open_square") != null){
                open = Integer.parseInt(jedis.get("open_square"));
            } else {
                open = issuesService.countOpen3();
                jedis.set("open_square",String.valueOf(open));
            }

            if (jedis.get("closed_square") != null){
                closed = Integer.parseInt(jedis.get("closed_square"));
            } else {
                closed = issuesService.countClosed3();
                jedis.set("closed_square", String.valueOf(closed));
            }
        } catch (Exception e){
            open = issuesService.countOpen3();
            closed = issuesService.countClosed3();
        }

        List<FrontData> list = new ArrayList<>();
        list.add(new FrontData("open", open));
        list.add(new FrontData("closed", closed));
        return list;
    }

    @RequestMapping("/queryDay_go")
    @ResponseBody
    public int[] queryDay_go(){
        List<Long> dates = issuesService.listDay1();
        // 0-10 10-50 50-100 100-500 500+
        return util_day(dates);
    }

    @RequestMapping("/queryDay_jquery")
    @ResponseBody
    public int[] queryDay_jquery(){
        List<Long> dates = issuesService.listDay2();
        // 0-10 10-50 50-100 100-500 500+
        return util_day(dates);
    }

    @RequestMapping("/queryDay_square")
    @ResponseBody
    public int[] queryDay_square(){
        List<Long> dates = issuesService.listDay3();
        // 0-10 10-50 50-100 100-500 500+
        return util_day(dates);
    }

    public int[] util_day(List<Long> dates){
        int[] stage = new int[5];
        for (Long l : dates){
            if (l <= 10){
                stage[0]++;
            } else if (l <= 50){
                stage[1]++;
            } else if (l <= 100){
                stage[2]++;
            } else if (l <= 500){
                stage[3]++;
            } else {
                stage[4]++;
            }
        }
        return stage;
    }

    public long until_average(List<Long> dates){
        long result = 0;
        for(long l : dates){
            result += l;
        }
        return (result/dates.size());
    }

    public long until_range(List<Long> dates){
        long min = Long.MAX_VALUE;
        long max = -1;
        for(long l : dates){
            if (l < min){
                min = l;
            }
            if(l > max){
                max = l;
            }
        }
        return (max-min);
    }

    public long until_variance(Long average, List<Long> dates){
        long result = 0;
        for (Long l : dates){
            result += (l-average)*(l-average);
        }
        return result/dates.size();
    }


    @RequestMapping("/excel_go")
    @ResponseBody
    public void excel_go(HttpServletResponse response) throws Exception {
        List<Issues> list = issuesService.listByRepo(repoName[0]);
        res(response, list);
    }

    @RequestMapping("/excel_jquery")
    @ResponseBody
    public void excel_jquery(HttpServletResponse response) throws Exception {
        List<Issues> list = issuesService.listByRepo(repoName[1]);
        res(response, list);
    }

    @RequestMapping("/excel_square")
    @ResponseBody
    public void excel_square(HttpServletResponse response) throws Exception {
        List<Issues> list = issuesService.listByRepo(repoName[2]);
        res(response, list);
    }

    public void res(HttpServletResponse response, List<Issues> list) throws Exception{
        response.setCharacterEncoding("UTF-8");
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("data of issues");
        // 创建表头
        HSSFRow hssfRow = sheet.createRow(0);
        hssfRow.createCell(0).setCellValue("number");
        hssfRow.createCell(1).setCellValue("state");
        hssfRow.createCell(2).setCellValue("created_at");
        hssfRow.createCell(3).setCellValue("closed_at");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (Issues data : list){
            HSSFRow dataRow = sheet.createRow(sheet.getLastRowNum()+1);
            dataRow.createCell(0).setCellValue(data.getNumber());
            dataRow.createCell(1).setCellValue(data.getState());
            dataRow.createCell(2).setCellValue(format.format(data.getCreatedAt()));
            if (data.getClosedAt() == null){
                dataRow.createCell(3).setCellValue("NULL");
            } else {
                dataRow.createCell(3).setCellValue(format.format(data.getClosedAt()));
            }
        }
        // 建立输出流，输出浏览器文件
        OutputStream os = null;
        response.setContentType("application/octet-stream;charset=utf-8");
        response.setHeader("Content-Disposition","attachment;filename=" + new String("data of issues".getBytes(), StandardCharsets.ISO_8859_1) + ".xls");
        // 输出文件
        os = response.getOutputStream();
        wb.write(os);
        os.flush();
        os.close();
    }

}
