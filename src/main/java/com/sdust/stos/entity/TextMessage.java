package com.sdust.stos.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

@Data
public class TextMessage implements Serializable {

    //书号
    @TableId(value = "isbn")
    private String isbn;

    //书名
    private String bookName;

    //作者
    private String author;

    //出版社
    private String publish;

    //单价
    private Integer price;
}
