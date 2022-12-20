package com.example.java2finalproject.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@TableName("keywords")
@Data
public class Keywords {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String repo;
    private String content;
    private Double score;

}
