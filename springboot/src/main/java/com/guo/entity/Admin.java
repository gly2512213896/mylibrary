package com.guo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("admin")
public class Admin {
    @TableId(value = "admin_id",type = IdType.AUTO)
    private Integer adminId;
    private String adminName;
    private String adminNickName;
    private String adminPassword;
}
