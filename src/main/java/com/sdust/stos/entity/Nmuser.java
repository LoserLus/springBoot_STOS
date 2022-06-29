package com.sdust.stos.entity;

import lombok.Data;

@Data
public class Nmuser {

    //编号
    private String DGZnumber;

    //姓名
    private String DGZname;

    //所属院系
    private String DGZclass;

    //联系方式
    private String NMtel;

    //账号
    private String NMUser;

    //密码
    private String NMPassword;

}
