package com.sdust.stos.entity;

import lombok.Data;

@Data
public class User {
    //编号
    private Integer number;
    //姓名
    private String name;
    //所属院系
    private String dept;
    //联系方式
    private String tel;

}
