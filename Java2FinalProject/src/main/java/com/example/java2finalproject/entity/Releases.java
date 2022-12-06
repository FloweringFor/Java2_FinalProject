package com.example.java2finalproject.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@AllArgsConstructor
@TableName("releases")
@Data
public class Releases {
    private String repo;
    private String tagName;
    private String prerelease;
    private Date publishedAt;
}
