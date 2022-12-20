package com.example.java2finalproject.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@TableName("developers")
@Data
public class Developers {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String repo;
    private String login;
    private Integer loginId;
    private Integer contribution;


}
