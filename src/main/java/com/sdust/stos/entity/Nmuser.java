package com.sdust.stos.entity;

import lombok.Data;

@Data
public class Nmuser {

    //编号
    private String dgzNumber;

    //姓名
    private String dgzName;

    //所属院系
    private String dgzClass;

    //联系方式
    private String nmTel;

    //账号
    private String nmUser;

    //密码
    private String nmPassword;

}
