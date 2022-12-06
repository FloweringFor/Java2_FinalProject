package com.example.java2finalproject.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@TableName("developers")
@Data
public class Developers {
    private String repo;
    private String login;
    private Integer id;
    private Integer contribution;

}
