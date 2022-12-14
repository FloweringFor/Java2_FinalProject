package com.example.java2finalproject.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@TableName("commits")
@Data
public class Commits {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String repo;
    private String author;
    private Date commitAt;

}
