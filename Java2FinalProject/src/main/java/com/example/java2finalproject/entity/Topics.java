package com.example.java2finalproject.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@TableName("topics")
@Data
public class Topics {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String repo;
    private String title;
    private String description;
    private int commentCount;
}
