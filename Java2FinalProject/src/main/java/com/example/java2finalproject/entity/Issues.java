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
@TableName("issues")
@Data
public class Issues {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String repo;
    private Integer number;
    private String state;
    private Date createdAt;
    private Date closedAt;
}


