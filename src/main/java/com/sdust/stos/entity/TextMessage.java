package com.sdust.stos.entity;

import lombok.Data;

@Data
public class TextMessage {

    //书号
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