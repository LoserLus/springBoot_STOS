package com.sdust.stos.entity;

import lombok.Data;

@Data
public class Dgzuser {

    //编号(账号)
    private String dgzNumber;

    //姓名
    private String dgzName;

    //所属院系
    private String dgzDept;

    //联系方式
    private String dgzTel;

    //密码
    private String dgzPassword;

}
