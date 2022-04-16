package com.guo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("borrow")
public class Borrow {
    @TableId(value = "borrow_id",type= IdType.AUTO)
    private Integer borrowId;
    private String userName;
    private String bookName;
    private Date borrowDate;
    private Date returnDate;
}
