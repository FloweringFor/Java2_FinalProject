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
@TableName("records")
@Data
public class Records {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Date recordAt;

}
