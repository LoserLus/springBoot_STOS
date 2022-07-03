package com.sdust.stos.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class DgzUser implements Serializable {

    //编号(账号)
    private String dgzUsername;

    //密码
    private String dgzPassword;

    //姓名
    private String dgzName;

    //所属院系
    private String dgzDept;

    //联系方式
    private String dgzTel;



}
