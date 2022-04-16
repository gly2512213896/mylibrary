package com.guo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.junit.jupiter.api.Test;

@TableName("user")
@Data
public class User {
    @TableId(value = "user_id",type= IdType.AUTO)
    private Integer userId;
    private String userName;
    private Integer age;
    private String sex;
    private String address;
}
