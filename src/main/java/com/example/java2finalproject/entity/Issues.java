package com.example.java2finalproject.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@AllArgsConstructor
@TableName("issues")
@Data
public class Issues {
    private String repo;
    private Integer number;
    private String state;
    private Date createdAt;
    private Date closedAt;
}
