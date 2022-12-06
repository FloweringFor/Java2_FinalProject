package com.example.java2finalproject.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@AllArgsConstructor
@TableName("commits")
@Data
public class Commits {
    private String repo;
    private String author;
    private Date commitAt;
}
