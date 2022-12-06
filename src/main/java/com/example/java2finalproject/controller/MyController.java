package com.example.java2finalproject.controller;


import com.example.java2finalproject.entity.Developers;
import com.example.java2finalproject.service.DevelopersService;
import com.example.java2finalproject.view.FrontData;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MyController {
    @Autowired
    private DevelopersService developersService;

    @RequestMapping("/query")
    @ResponseBody
    public List<FrontData> index(){

        List<Developers> developersList = developersService.listByRepo();
        List<FrontData> res = new ArrayList<>();
        if (developersList.size() <= 10){
            for (Developers d : developersList){
                res.add(new FrontData(d.getLogin(), d.getContribution()));
            }
        } else {
            int total = developersService.sumAll();
            int count = 0;
            for (int i = 0; i < 10; i++){
                count += developersList.get(i).getContribution();
                res.add(new FrontData(developersList.get(i).getLogin(), developersList.get(i).getContribution()));
            }
            res.add(new FrontData("other people", total-count));
        }
        return res;
    }

    @RequestMapping("/excel")
    @ResponseBody
    public void excel(HttpServletResponse response) throws Exception {
        List<Developers> list = developersService.listByRepo();
        response.setCharacterEncoding("UTF-8");
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("data of developer");
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
        response.setHeader("Content-Disposition","attachment;filename=" + new String("data of developer".getBytes(), StandardCharsets.ISO_8859_1) + ".xls");
        // 输出文件
        os = response.getOutputStream();
        wb.write(os);
        os.flush();
        os.close();
    }
}