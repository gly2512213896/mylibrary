package com.guo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("book")
public class Book {
    @TableId(value = "book_id",type = IdType.AUTO)
    private Integer bookId;
    private String bookName;
    private String author;
    @TableField("`describe`")
    private String describe;
}
